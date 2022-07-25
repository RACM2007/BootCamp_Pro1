package com.bootcamp.MS_Credits.config;

import com.bootcamp.MS_Credits.model.Credits;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class cacheConfig {

    @Bean
    ReactiveRedisOperations<String, Credits> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Credits> serializer = new Jackson2JsonRedisSerializer<>(Credits.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Credits> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Credits> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

}
