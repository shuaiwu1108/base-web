package com.shuaiwu.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceServerConfigurerAdapter的优先级，默认高于WebSecurityConfigurerAdapter
 * 所以需要授权，但不需要做登录验证的URL资源，配置在此，多配置OpenAPI接口在此
 */
@Configuration
@EnableResourceServer
public class OauthResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/api/**") //只有api开头的请求，才会被resource处理器拦截处理，其他的URL请求，交由其他的逻辑处理
            .authorizeRequests()
            .anyRequest().authenticated();
    }
}
