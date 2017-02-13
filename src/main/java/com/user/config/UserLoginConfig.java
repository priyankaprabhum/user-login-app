package com.user.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan({ "com.user.*" })
public class UserLoginConfig extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginConfig.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UserLoginConfig.class);
	}

}
