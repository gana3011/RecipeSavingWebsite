package com.project.recipe.Configuration;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Value("${spring.mail.host:smtp.gmail.com}")
    private String mailHost;

    @Value("${spring.mail.port:587}")
    private int mailPort;

    @Value("${spring.mail.username:NOT_SET}")
    private String mailUsername;

    @Value("${spring.mail.password:NOT_SET}")
    private String mailPassword;

    @PostConstruct
    public void debugMailProperties() {
        System.out.println("=== MAIL CONFIGURATION DEBUG ===");
        System.out.println("Mail Host: " + mailHost);
        System.out.println("Mail Port: " + mailPort);
        System.out.println("Mail Username: " + mailUsername);
        System.out.println("Mail Password: " + (mailPassword.equals("NOT_SET") ? "NOT_SET" : "SET"));

        // Print all environment variables
        System.out.println("=== ALL ENVIRONMENT VARIABLES ===");
        System.getenv().forEach((key, value) -> {
            if (key.contains("MAIL") || key.contains("SPRING")) {
                System.out.println(key + " = " + (key.contains("PASSWORD") ? "***" : value));
            }
        });
        System.out.println("================================");
    }

    @Bean
    public JavaMailSender javaMailSender() {
        if (mailUsername.equals("NOT_SET") || mailPassword.equals("NOT_SET")) {
            System.out.println("WARNING: Mail credentials not properly set, creating mock JavaMailSender");
            return new JavaMailSenderImpl(); // This will create a basic sender that won't work but won't crash
        }

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        System.out.println("JavaMailSender bean created successfully");
        return mailSender;
    }
}