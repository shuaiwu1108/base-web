package com.shuaiwu.oauth2.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    private String title = "security+oauth2服务";
    private String description = "springboot集成security+oauth2";
    private String version = "v1.0.0";
    private String websiteName = "OAuth服务";
    private String websiteUrl = "http://127.0.0.1:8080";

    @Bean
    public OpenAPI heroOpenAPI() {
        return new OpenAPI()
            .info(new Info().title(title)
                .description(description)
                .version(version))
            .externalDocs(new ExternalDocumentation().description(websiteName)
                .url(websiteUrl));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi(){
        return GroupedOpenApi.builder()
            .group(title)
            .pathsToMatch("/**")
            .build();
    }
}
