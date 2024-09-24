package com.redis.crud.service;

import com.google.common.hash.Hashing;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Service
public class UrlShortener {

    StringRedisTemplate redisTemplate;

    private final Logger logger = Logger.getLogger(UrlShortener.class.getName());

    public UrlShortener(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getUrl(String id){
        logger.info("Searching for the url by its key in redis");
        String url = redisTemplate.opsForValue().get(id);
        return url;
    }

    public String createUrl(String url){
        logger.info("Creating new hashing for {" + url + "}");
        String id = Hashing.sha256().hashString(url, StandardCharsets.UTF_8).toString();
        logger.info("Saving the hashed url in redis");
        redisTemplate.opsForValue().set(id, url);
        return id;
    }
}