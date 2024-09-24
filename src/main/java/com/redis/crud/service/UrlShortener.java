package com.redis.crud.service;

import com.google.common.hash.Hashing;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class UrlShortener {

    StringRedisTemplate redisTemplate;

    public UrlShortener(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getUrl(String id){
        String url = redisTemplate.opsForValue().get(id);
        return url;
    }

    public String createUrl(String url){
        String id = Hashing.sha256().hashString(url, StandardCharsets.UTF_8).toString();
        redisTemplate.opsForValue().set(id, url);
        return id;
    }
}