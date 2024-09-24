package com.redis.crud.dto;

import org.hibernate.validator.constraints.URL;

public class RequestUrlDto {
    @URL
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
