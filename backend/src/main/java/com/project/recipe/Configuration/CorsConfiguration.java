package com.project.recipe.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
           @Override
            public void addCorsMappings(CorsRegistry registry){
               registry.addMapping("/**")
                       .allowedOrigins("https://flavour-file-frontend.vercel.app/")
                       .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                       .allowedHeaders("*")
                       .allowCredentials(true);
           }
        };
    }
}
