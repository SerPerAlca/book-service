package com.eternum.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        String resourcePath;

        try {
            File imagesDirectory = new File("static/image");
            if (!imagesDirectory.exists()) {
                imagesDirectory = new File("../static/image");
            }
            resourcePath = imagesDirectory.getCanonicalFile().toURI().toString();
        } catch (final Exception e) {
            resourcePath = "file:./static/image/";
        }

        registry.addResourceHandler("/static/image/**")
                .addResourceLocations(resourcePath);
    }
}
