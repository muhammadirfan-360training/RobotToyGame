package com.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/** 
 * The Game class is the entry point of Spring Boot
 * 
 */

@SpringBootApplication
public class Game extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Game.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Game.class, args);
	}

}
