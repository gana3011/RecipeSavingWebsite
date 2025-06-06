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

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @PostConstruct
    public void debugMailProperties() {
        System.out.println("=== MAIL CONFIGURATION DEBUG ===");
        System.out.println("Mail Host: " + mailHost);
        System.out.println("Mail Port: " + mailPort);
        System.out.println("Mail Username: " + (mailUsername != null && !mailUsername.isEmpty() ? "SET" : "NOT SET"));
        System.out.println("Mail Password: " + (mailPassword != null && !mailPassword.isEmpty() ? "SET" : "NOT SET"));
        System.out.println("================================");
    }

    @Bean
    public JavaMailSender javaMailSender() {
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