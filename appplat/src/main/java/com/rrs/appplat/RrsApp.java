package com.rrs.appplat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rrs"})
@Configuration
public class RrsApp {
	public static void main(String[] args) {
		SpringApplication.run(RrsApp.class, args);
	}
}

