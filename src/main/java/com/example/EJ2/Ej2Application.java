package com.example.EJ2;

import com.example.EJ2.Persona.Application.Services.PersonaService;
import com.example.EJ2.Persona.Domain.Entities.Persona;
import com.example.EJ2.Role.application.interfaces.RoleService;
import com.example.EJ2.Role.infraestructure.dtos.RoleInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories
@EnableWebSecurity
@Slf4j

public class Ej2Application {

	public static void main(String[] args) {
		SpringApplication.run(Ej2Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/*@Bean
		CommandLineRunner run (RoleService roleService){
			return args -> {
				roleService.saveRole(new RoleInput(null, "ROLE_USER"));
				roleService.saveRole(new RoleInput(null, "ROLE_MANAGER"));
				roleService.saveRole(new RoleInput(null, "ROLE_ADMIN"));
				roleService.saveRole(new RoleInput(null, "ROLE_SUPER_ADMIN"));

			};
		}*/

}
