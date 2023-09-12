package com.shuaiwu.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("swagger")
public class SwaggerController {

    @RequestMapping("index")
    public String index(){
        return "redirect:/swagger-ui/index.html";
    }

    @RequestMapping("knife")
    public String knife() {
        return "redirect:/doc.html";
    }
}
