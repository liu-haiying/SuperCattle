package com.black.cattle.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> void setValue(String key, T value, Long timeout) {

        redisTemplate.opsForValue().set(key, value);

        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public <T> T getValue(String key) {

        return (T) redisTemplate.opsForValue().get(key);
    }

    public Long decrNum(String key) {

        return redisTemplate.opsForValue().decrement(key);
    }
}
