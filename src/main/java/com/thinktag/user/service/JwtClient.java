package com.thinktag.user.service;


import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

public interface JwtClient {

    @RequestLine("POST /api/token/create")
    //@Headers("Content-Type: application/json")
    String createToken(@QueryMap Map<String, String> params);
}
