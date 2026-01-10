package com.newproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.newproject.entity.ErrorMessage;
import com.newproject.entity.Users;

@Configuration
public class UsersConfig {

	@Bean
	Users users() {
		return new Users();
	}
	
	@Bean
	ErrorMessage errorMessage() {
		return new ErrorMessage();
	}
}
