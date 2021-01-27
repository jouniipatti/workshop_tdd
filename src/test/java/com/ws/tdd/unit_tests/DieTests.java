package com.ws.tdd.unit_tests;

import com.ws.tdd.Die;
import com.ws.tdd.IDie;
import com.ws.tdd.RollController;
import com.ws.tdd.RollResults;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DieTests {
	private Die sut;

	@BeforeEach
	public void setup(){
		sut = new Die();
	}
	
	@Test
	public void testMeanIsCloseTo_3_5(){
		int result = 0;
		for (int i=0; i<1000; i++){
			result += sut.roll();
		}
		float mean = ((float)result)/1000.0f;
		assertThat(mean).isBetween(3.4f, 3.6f);
	}

}
