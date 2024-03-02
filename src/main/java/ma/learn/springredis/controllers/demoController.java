package ma.learn.springredis.controllers;

import lombok.AllArgsConstructor;
import ma.learn.springredis.entities.Token;
import ma.learn.springredis.services.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class demoController {

    private TokenService tokenService;


    @PostMapping
    public Token saveToken(){
        Random random = new Random();
        Long id = random.nextLong();
        Token token = Token.builder().id(id).token("token"+id).loggedout(false).user(id).build();
        return tokenService.saveToken(token);
    }
    @GetMapping
    public List<Token> getAllToken(@RequestParam Long id){
        return this.tokenService.getAllTokenByUser(id);
    }

    @GetMapping("/getByToken/{token}")
    public Token findbyToken(@PathVariable String token){
        return this.tokenService.getByToken(token);
    }
    @GetMapping("/alltokens")
    public List<Token> getAllToken(){
        return this.tokenService.AllToken();
    }


}
