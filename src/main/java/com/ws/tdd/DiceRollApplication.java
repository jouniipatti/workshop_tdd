package com.ws.tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiceRollApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DiceRollApplication.class);
		app.run(args);
	}
}
