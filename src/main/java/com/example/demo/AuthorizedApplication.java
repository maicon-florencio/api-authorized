package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AuthorizedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizedApplication.class, args);

/*
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder("pbkdf2",5,5, null));
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(passwordEncoder);

		String result = passwordEncoder.encode("admin234");
		System.out.println("My hash " + result);*/

	}

}
