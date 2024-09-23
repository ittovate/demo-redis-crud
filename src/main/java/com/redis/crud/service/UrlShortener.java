package com.redis.crud.service;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

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

    public Boolean validUrl (String url){
        UrlValidator validator = new UrlValidator();
//        return validator.isValid(url);
        return true;
    }

    public String createUrl(String url){
        if(validUrl(url)){
            String id = Hashing.sha256().hashString(url, StandardCharsets.UTF_8).toString();
            redisTemplate.opsForValue().set(id, url);
            return id;
        }
        throw new RuntimeException("URL Invalid: " + url);
    }
}