package com.ws.tdd.integration_tests;

import static org.mockito.Mockito.mock;
import com.ws.tdd.IDie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Profile("test")
@Configuration
public class TestConfiguration {
	@Bean
	@Scope("sigleton")
	@Primary
	public IDie iDice(){
		return mock(IDie.class);
	}
}
