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
import com.ws.tdd.RollResults;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EndToEndTests {
	@Autowired
	private TestRestTemplate client;
	
	@LocalServerPort
	private int port;

	@Test
	public void diceRollReturnsOk(){
		ResponseEntity<String> result = client.getForEntity(url(3), String.class);        
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void diceRollsInLimits(){
		RollResults results = client.getForObject(url(2), RollResults.class);
		assertThat(results.rolls.length).isEqualTo(2);
		assertThat(results.rolls[0]).isBetween(1, 6);
		assertThat(results.rolls[1]).isBetween(1, 6);
	}

	private String url(int nDice){
		return "http://localhost:"+port+"/roll?dice="+nDice;
	}
}
