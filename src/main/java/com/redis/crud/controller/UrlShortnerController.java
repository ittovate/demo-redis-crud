package com.redis.crud.controller;

import com.redis.crud.dto.RequestUrlDto;
import com.redis.crud.service.UrlShortener;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/public/api/url")
public class UrlShortnerController {

    private UrlShortener urlShortener;
    private final Logger logger = Logger.getLogger(UrlShortnerController.class.getName());

    public UrlShortnerController(UrlShortener urlShortener) {
        this.urlShortener = urlShortener;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable String id){
        logger.info("Sending {" + id + "} to shortener service");
        return urlShortener.getUrl(id);
    }

    @PostMapping("/")
    public String addUrl(@RequestBody @Valid RequestUrlDto requestUrlDto){
        logger.info("Sending {" + requestUrlDto.getUrl() + "to shortener service");
        return urlShortener.createUrl(requestUrlDto.getUrl());
    }

}