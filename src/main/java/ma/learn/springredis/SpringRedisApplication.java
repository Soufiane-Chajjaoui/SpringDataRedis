package ma.learn.springredis;

import ma.learn.springredis.entities.Token;
import ma.learn.springredis.services.TokenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableCaching
public class SpringRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisApplication.class, args);
	}

	@Bean
	public CommandLineRunner lineRunner(TokenService tokenService){
		Random random = new Random();
		Long id = random.nextLong();
		AtomicInteger i = new AtomicInteger();
		return args -> {
			while ( i.get() < 1000){
				Token token = Token.builder().id(id).token("token"+id+i).loggedout(false).user(6L).build();
				tokenService.saveToken(token);
				i.getAndIncrement();
			}
		};
	}

}
