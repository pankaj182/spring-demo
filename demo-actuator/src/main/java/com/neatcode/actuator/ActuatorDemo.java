package com.neatcode.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.neatcode")
public class ActuatorDemo {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorDemo.class, args);
	}
}
