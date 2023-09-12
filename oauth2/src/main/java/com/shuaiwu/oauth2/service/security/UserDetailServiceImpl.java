package com.shuaiwu.oauth2.service.security;

import com.shuaiwu.oauth2.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("登录用户：[{}]", s);

        if (!redisUtil.hHasKey("user", s)){
            throw new UsernameNotFoundException("未知用户");
        }
        User user = new User(s, passwordEncoder.encode(redisUtil.hget("user", s).toString()),
            AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        return user;
    }
}
