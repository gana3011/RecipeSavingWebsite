package com.project.recipe.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailDebugConfig {

    @Value("${spring.mail.username:NOT_SET}")
    private String mailUser;

    @PostConstruct
    public void logMailUser() {
        System.out.println(">>> Mail user loaded: " + mailUser);
    }
}
