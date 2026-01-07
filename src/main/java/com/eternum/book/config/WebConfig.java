package com.eternum.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 1. CONFIGURACIÓN DE CORS (Para que React lea el JSON)
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    // 2. CONFIGURACIÓN DE IMÁGENES (Para que React cargue los .avif/.jpg)
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // Esto expone la carpeta local como una URL web
        registry.addResourceHandler("/static/image/**")
                // Asegúrate de que esta ruta sea accesible por el microservicio Book
                .addResourceLocations("file:Y:/ETERNUM/APLICACION/static/image/");
    }
}
