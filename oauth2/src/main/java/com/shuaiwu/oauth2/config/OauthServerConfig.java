package com.shuaiwu.oauth2.config;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DefaultTokenServices defaultTokenServices;


    @Bean
    public TokenStore tokenStore(RedisConnectionFactory connectionFactory){
        return new RedisTokenStore(connectionFactory);
    }

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource){
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setClientDetailsService(jdbcClientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        return tokenServices;
    }

    /**
     * 此处通过配置ClientDetailService，来配置注册到此授权服务器的客户端Clients信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService);
    }

    /**
     * 定义token安全策略
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
            //oauth/token_key是公开
            .tokenKeyAccess("permitAll()")
            //oauth/check_token公开
            .checkTokenAccess("permitAll()")
            //表单认证（申请令牌）
            .allowFormAuthenticationForClients();
    }

    /**
     * 配置授权服务器端点特性（Authorization Server endpoint），主要是一些非安全的特性
     * 例如token存储，token自定义，授权类型等
     * 默认不需要任何配置
     * 如果是密码授权则需要提供一个AuthenticationManager
     * 如果需要返回refresh_token，则需要设置tokenGranter，默认的clientCredentials授权不返回refresh_token
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        endpoints.tokenStore(tokenStore)
            .tokenServices(defaultTokenServices)
            .tokenGranter(new CompositeTokenGranter(getCustomizedTokenGranters()))
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    private List<TokenGranter> getCustomizedTokenGranters() {
        DefaultOAuth2RequestFactory requestFactory = new DefaultOAuth2RequestFactory(jdbcClientDetailsService);
        RefreshTokenGranter refreshTokenGranter = new RefreshTokenGranter(defaultTokenServices, jdbcClientDetailsService, requestFactory);
        ClientCredentialsTokenGranter clientCredentialsTokenGranter = new ClientCredentialsTokenGranter(defaultTokenServices, jdbcClientDetailsService, requestFactory);
        // 设置返回refresh_code
        clientCredentialsTokenGranter.setAllowRefresh(true);
        List<TokenGranter> tokenGranters = new ArrayList<>();
        tokenGranters.add(refreshTokenGranter);
        tokenGranters.add(clientCredentialsTokenGranter);

        return tokenGranters;
    }
}
