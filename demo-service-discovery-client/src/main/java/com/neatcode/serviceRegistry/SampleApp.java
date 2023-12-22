package com.neatcode.serviceRegistry;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.neatcode")
@EnableDiscoveryClient
//@EnableSwagger2
public class SampleApp {
    public static void main(String[] args) {
        SpringApplication.run(SampleApp.class, args);
    }
}
