package com.shuaiwu.oauth2.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select * from users WHERE username=?")
            .authoritiesByUsernameQuery("select * from authorities where username=?")
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.logout().logoutSuccessUrl("/");//配置登出功能，登出成功后，跳转到登录页面
        http
//            .formLogin().successForwardUrl("/swagger/index")
            .formLogin().successForwardUrl("/swagger/knife")
            .and()
            .authorizeRequests()
            .anyRequest().authenticated(); //其他的URL资源的请求，交由OauthResource拦截链处理
//        http.formLogin().loginPage("/login"); //配置默认的登录页
        http.rememberMe();//配置记住密码
        http.csrf().disable();//跨域请求校验关闭
    }
}
