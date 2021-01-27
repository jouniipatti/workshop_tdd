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
import static org.mockito.Mockito.when;

import com.ws.tdd.IDie;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DiceRollApplicationTests {
	@Autowired
	private TestRestTemplate client;
	
	@Autowired
	private IDie die;

	@LocalServerPort
	private int port;

	@Test
	public void diceRollReturnsOk(){
		ResponseEntity<String> result = client.getForEntity("http://localhost:"+port+"/roll?dice=3", String.class);        
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void diceRollResults(){
		when(die.roll(6)).thenReturn(4,3,6);
		String result = client.getForObject("http://localhost:"+port+"/roll?dice=3", String.class);        
		assertThat(result).isEqualTo("{\"sum\":13,\"rolls\":[4,3,6]}");
	}

	@Test
	public void diceRollMalformedQuery(){
		ResponseEntity<String> result = client.getForEntity("http://localhost:"+port+"/roll?dice=asd", String.class);        
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void diceRollResultsD20(){
		when(die.roll(20)).thenReturn(20,1,11);
		String result = client.getForObject("http://localhost:"+port+"/roll?dice=3d20", String.class);        
		assertThat(result).isEqualTo("{\"sum\":13,\"rolls\":[20,1,11]}");
	}

}
