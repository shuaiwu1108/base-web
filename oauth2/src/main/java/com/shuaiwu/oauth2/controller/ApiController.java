package com.shuaiwu.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("hello")
    public String hello(){
        return "hello world, oauth2";
    }
}
