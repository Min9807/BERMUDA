package com.example.bermuda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BermudaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BermudaApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
