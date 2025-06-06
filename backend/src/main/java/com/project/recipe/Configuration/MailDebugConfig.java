package com.project.recipe.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MailDebugConfig {

    @Component
    public class MailConfigDebug {

        @Value("${spring.mail.username:NOT_SET}")
        private String mailUsername;

        @Value("${spring.mail.password:NOT_SET}")
        private String mailPassword;

        @PostConstruct
        public void MailConfigDebug() {
            System.out.println("=== MAIL CONFIG DEBUG ===");
            System.out.println("Mail Username: " + (mailUsername.equals("NOT_SET") ? "NOT_SET" : "SET"));
            System.out.println("Mail Password: " + (mailPassword.equals("NOT_SET") ? "NOT_SET" : "SET"));
            System.out.println("========================");
        }
    }
}
