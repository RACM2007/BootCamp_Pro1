package com.bootcamp.MS_BootCoin.service;

import com.bootcamp.MS_BootCoin.Entity.MSClientKaf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Service;

@Service
public class MSClientKafRedis {

    @Autowired
    private ReactiveRedisOperations<String, MSClientKaf> msClientKafReactiveRedisOperations;

    public void Save_Client_Redis(String key, MSClientKaf msClientKaf){
        ReactiveValueOperations<String, MSClientKaf> reactiveValueOperations = msClientKafReactiveRedisOperations.opsForValue();
        reactiveValueOperations.set(key,msClientKaf).subscribe();
    }


}
