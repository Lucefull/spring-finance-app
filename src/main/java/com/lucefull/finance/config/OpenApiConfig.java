package com.lucefull.finance.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Value("${springdoc.title}")
    private String title;

    @Value("${springdoc.version}")
    private String version;

    @Value("${springdoc.description}")
    private String description;

    @Bean
    public OpenAPI customOpenAPI() {
    return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title(title)
                        .version(version)
                        .description(description));
    }


}
