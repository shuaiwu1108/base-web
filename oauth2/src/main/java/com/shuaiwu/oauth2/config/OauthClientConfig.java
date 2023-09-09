package com.shuaiwu.oauth2.config;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableOAuth2Client
public class OauthClientConfig {

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails(){
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
//        details.setAccessTokenUri("http://127.0.0.1:8080/oauth/token");
        details.setClientId("client");
        details.setClientSecret("secret");
        details.setGrantType("client_redentials");
        details.setScope(Arrays.asList("read", "write"));
        return details;
    }

    @Bean
    public RestTemplate restTemplate(OAuth2ClientContext oAuth2ClientContext){
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), oAuth2ClientContext);
    }
}
