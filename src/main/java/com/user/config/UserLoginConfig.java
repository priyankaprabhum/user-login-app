package com.user.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan({ "com.user.*"})
public class UserLoginConfig {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(UserLoginConfig.class, args);
	}
}
