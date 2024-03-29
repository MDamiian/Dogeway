package com.dogeway.dw.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOrigins("http://127.0.0.1:5500", "http://127.0.0.1:8080", "http://localhost:5500", "http://127.0.0.1:5502").
                allowedMethods("POST", "GET","PUT","DELETE");
    }
}