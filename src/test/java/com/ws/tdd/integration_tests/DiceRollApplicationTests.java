package com.ws.tdd.integration_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DiceRollApplicationTests {
	@Autowired
	private TestRestTemplate client;
	
	@LocalServerPort
	private int port;

	@Test
	public void diceRollReturnsOk(){
		ResponseEntity<String> result = client.getForEntity("http://localhost:"+port+"/roll?dice=3", String.class);        
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void diceRollResults(){
		String result = client.getForObject("http://localhost:"+port+"/roll?dice=3", String.class);        
		assertThat(result).isEqualTo("{\"sum\":13,\"rolls\":[4,3,6]}");
	}
}
