package com.Ltech.LibraryTech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiDbApplication.class, args);
        System.out.println("API iniciada em: http://localhost:8080");
        System.out.println("Health check: http://localhost:8080/v1/api/logon/health");
    }
}