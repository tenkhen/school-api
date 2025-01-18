package io.khenrab.school;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentCheck {
    @Value("${DB_URL}")
    private String dbUrl;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @PostConstruct
    public void checkEnvironmentVariables() {
        System.out.println("DB_URL: " + dbUrl);
        System.out.println("DB_USERNAME: " + dbUsername);
        System.out.println("DB_PASSWORD: " + dbPassword);
    }
}
