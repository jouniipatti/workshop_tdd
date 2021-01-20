package com.ws.tdd.unit_tests;

import com.ws.tdd.RollController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RollControllerTests {
	private RollController sut;

	@BeforeEach
	public void setup(){
		
	}

	@Test
	public void sutCreated(){
		assertThat(sut).isNotNull();
	}
}
