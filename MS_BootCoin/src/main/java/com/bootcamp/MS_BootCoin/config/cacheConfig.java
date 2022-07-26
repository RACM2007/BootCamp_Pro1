package com.bootcamp.MS_BootCoin.config;

import com.bootcamp.MS_BootCoin.Entity.MSClientKaf;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class cacheConfig {

    @Bean
    ReactiveRedisOperations<String, MSClientKaf> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<MSClientKaf> serializer = new Jackson2JsonRedisSerializer<>(MSClientKaf.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, MSClientKaf> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, MSClientKaf> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

}
