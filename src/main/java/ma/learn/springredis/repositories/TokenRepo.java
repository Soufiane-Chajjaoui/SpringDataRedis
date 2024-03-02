package ma.learn.springredis.repositories;


import lombok.AllArgsConstructor;
import ma.learn.springredis.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepo extends JpaRepository<Token , Long> {
    void deleteByToken(String token);
    Token findByToken(String token);
    List<Token> findByUser(Long id);
}

