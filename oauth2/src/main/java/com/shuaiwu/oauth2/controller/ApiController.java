package com.shuaiwu.oauth2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "OAuth验证的接口")
@RestController
@RequestMapping("api")
public class ApiController {

    @Operation(summary = "hello请求")
    @RequestMapping("hello")
    public String hello(){
        return "hello world, oauth2";
    }
}
