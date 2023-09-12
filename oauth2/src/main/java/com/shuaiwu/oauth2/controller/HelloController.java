package com.shuaiwu.oauth2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "非OAuth验证接口")
@RestController
@RequestMapping("hello")
public class HelloController {

    @Operation(summary = "index请求")
    @RequestMapping("index")
    public String hello(){
        return "hello, index";
    }
}
