package com.example.desafio.api.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI configOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .description("API para gerenciamento de carros")
                        .version("1.0")
                        .title("API - Desafio Tecnico")
        );
    }
}
