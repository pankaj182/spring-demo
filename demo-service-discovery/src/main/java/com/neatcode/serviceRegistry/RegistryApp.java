package com.neatcode.serviceRegistry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(scanBasePackages = "com.neatcode")
@EnableEurekaServer
public class RegistryApp {
    public static void main(String[] args) {
        SpringApplication.run(RegistryApp.class, args);
    }
}
