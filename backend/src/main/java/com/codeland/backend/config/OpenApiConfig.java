package com.codeland.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI carManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Car Management API")
                        .description("API for managing cars and fuel entries")
                        .version("v0.0.1"));
    }
}
