package com.bootcamp.MS_Credits.service;

import com.bootcamp.MS_Credits.model.Credits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;

@Service
public class MSCreditKafRedis {

    @Autowired
    private ReactiveRedisOperations<String, Credits> msClientKafReactiveRedisOperations;

    public void Save_Credit_Redis(String key, Credits msClientKaf){
        ReactiveValueOperations<String, Credits> reactiveValueOperations = msClientKafReactiveRedisOperations.opsForValue();
        reactiveValueOperations.set(key,msClientKaf).subscribe();
    }

}
