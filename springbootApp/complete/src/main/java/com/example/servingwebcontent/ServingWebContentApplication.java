package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.servingwebcontent.database.DatabaseTest;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner databaseTestRunner() {
        return args -> {
            System.out.println("🚀 Application starting - Testing database connection...");
            DatabaseTest.testConnection();
        };
    }
}
