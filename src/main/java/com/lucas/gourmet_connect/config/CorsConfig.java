package com.lucas.gourmet_connect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**") // Endpoint que deseja liberar para CORS
                            .allowedOrigins("http://localhost:3000") // Origem permitida
                            .allowedMethods("GET", "POST", "PUT", "DELETE"); // Métodos HTTP permitidos
                }
            };
        }

}
