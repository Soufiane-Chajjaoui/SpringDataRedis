package ma.learn.springredis.services;

import lombok.AllArgsConstructor;

import ma.learn.springredis.entities.Token;
import ma.learn.springredis.repositories.TokenRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {

    private TokenRepo tokenRepo;

    @CacheEvict( value = {"tokens" , "findbytoken"} , key = "#root.methodName" , allEntries = true  )
    public Token saveToken(Token token) {
        return  this.tokenRepo.save(token);
    }
    @Cacheable(value = "getByUser" , key = "#root.methodName")
    public List<Token> getAllTokenByUser(Long userId){
        return this.tokenRepo.findByUser(userId);
    }

    @Cacheable( value = "tokens" , key = "'allTokens'" )
    public List<Token> AllToken(){
        return this.tokenRepo.findAll();
    }


    @Cacheable(value = "findbytoken" , key = "#token")
    public Token getByToken(String token) {
        return this.tokenRepo.findByToken(token);
    }

    public void deleteToken(String token) {
        this.tokenRepo.deleteByToken(token);
    }
}
