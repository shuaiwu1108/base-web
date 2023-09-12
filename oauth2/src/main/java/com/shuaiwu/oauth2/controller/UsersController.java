package com.shuaiwu.oauth2.controller;

import com.shuaiwu.oauth2.entity.Authorities;
import com.shuaiwu.oauth2.entity.Users;
import com.shuaiwu.oauth2.service.IAuthoritiesService;
import com.shuaiwu.oauth2.service.IUsersService;
import com.shuaiwu.oauth2.utils.SecretUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shuaiwu
 * @since 2023-09-12
 */
@Tag(name = "用户相关操作接口")
@RestController
@RequestMapping("/oauth/users")
public class UsersController {

    @Autowired
    private IUsersService iUsersService;

    @Autowired
    private IAuthoritiesService iAuthoritiesService;

    @Operation(summary = "获取当前User信息")
    @GetMapping("current")
    public Object getUser(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Operation(summary = "注册用户User")
    @PostMapping("register")
    public Object register(@RequestBody Users users){
        users.setPassword(SecretUtil.encode(users.getPassword()));
        iUsersService.save(users);
        Authorities authorities = new Authorities();
        authorities.setUsername(users.getUsername());
        authorities.setAuthority("ROLE_ADMIN");
        iAuthoritiesService.save(authorities);
        return "true";
    }
}
