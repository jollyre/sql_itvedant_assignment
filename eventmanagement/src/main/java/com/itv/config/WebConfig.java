package com.itv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This maps http://localhost:8080/uploads/filename.jpg to local uploads/filename.jpg
        registry
            .addResourceHandler("/uploads/**")
            .addResourceLocations("file:" + Paths.get("uploads").toAbsolutePath().toUri());
    }
}
