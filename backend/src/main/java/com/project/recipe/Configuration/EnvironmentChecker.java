package com.project.recipe.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;



@Component
public class EnvironmentChecker {

    @PostConstruct
    public void checkEnvironment() {
        System.out.println("=== ENVIRONMENT VARIABLES CHECK ===");

        // Check all environment variables
        System.out.println("Spring-related environment variables:");
        System.getenv().entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("SPRING_"))
                .forEach(entry -> {
                    String key = entry.getKey();
                    String value = key.contains("PASSWORD") ? "***HIDDEN***" : entry.getValue();
                    System.out.println(key + " = " + value);
                });

        // Check specific variables
        String mailUsername = System.getenv("SPRING_MAIL_USERNAME");
        String mailPassword = System.getenv("SPRING_MAIL_PASSWORD");
        String datasourceUrl = System.getenv("SPRING_DATASOURCE_URL");
        String jwtSecret = System.getenv("JWT_SECRET");

        System.out.println("\nSpecific variable check:");
        System.out.println("SPRING_MAIL_USERNAME: " + (mailUsername != null ? "SET" : "NOT SET"));
        System.out.println("SPRING_MAIL_PASSWORD: " + (mailPassword != null ? "SET" : "NOT SET"));
        System.out.println("SPRING_DATASOURCE_URL: " + (datasourceUrl != null ? "SET" : "NOT SET"));
        System.out.println("JWT_SECRET: " + (jwtSecret != null ? "SET" : "NOT SET"));
        System.out.println("===================================");
    }
}