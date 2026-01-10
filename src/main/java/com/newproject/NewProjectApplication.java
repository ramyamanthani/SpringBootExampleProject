package com.newproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Users Management Project",
				description = "User Management Project with CRUD Operations",
				version = "1.0.0",
				contact = @Contact(
						name = "Ramya",
						email = "ramya@gmail.com"
						)
				)
		)//Related to swagger implementation
public class NewProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewProjectApplication.class, args);
	}

}
