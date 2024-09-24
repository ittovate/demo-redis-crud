package com.redis.crud.controller;

import com.redis.crud.dto.RequestUrlDto;
import com.redis.crud.service.UrlShortener;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api/url")
public class UrlShortnerController {

    private UrlShortener urlShortener;

    public UrlShortnerController(UrlShortener urlShortener) {
        this.urlShortener = urlShortener;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable String id){
        return urlShortener.getUrl(id);
    }

    @PostMapping("/")
    public String addUrl(@RequestBody @Valid RequestUrlDto requestUrlDto){
        return urlShortener.createUrl(requestUrlDto.getUrl());
    }

}