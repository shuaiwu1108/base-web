package com.shuaiwu.oauth.controller;

import com.shuaiwu.oauth.utils.RedisUtil;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("get")
    public String getUser(String name){
        String pass = redisUtil.hget("user", name).toString();
        log.info("用户[{}], 密码[{}]", name, pass);
        return "hello world!," + name;
    }
}
