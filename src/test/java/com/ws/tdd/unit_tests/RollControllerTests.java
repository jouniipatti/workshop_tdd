package com.ws.tdd.unit_tests;

import com.ws.tdd.RollController;
import com.ws.tdd.RollResults;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RollControllerTests {
	private RollController sut;

	@BeforeEach
	public void setup(){
		sut = new RollController();
		sut.useSeed(0);
	}

	@Test
	public void sutCreated(){
		assertThat(sut).isNotNull();
	}

	//DONE: heittää noppia
	//DONE:	-heittää 1 noppa
	//summa
	//JSON

	@Test
	public void sutThrowsADie(){
		assertThat(sut.rollADie()).isEqualTo(5);
	}

	@Test
	public void rollADieBetweenLimits(){
		assertThat(sut.rollADie()).isBetween(1, 6);
	}

	@Test
	public void testMeanIsCloseTo_3_5(){
		int result = 0;
		for (int i=0; i<1000; i++){
			result += sut.rollADie();
		}
		float mean = ((float)result)/1000.0f;
		assertThat(mean).isBetween(3.4f, 3.6f);
	}

	@Test
	public void testRollNDice(){
		sut.useSeed(0);
		int[] result = sut.rollNDice(5).rolls;
		assertThat(result).isEqualTo(new int[]{5,5,2,4,4});
	}

	@Test
	public void rollNDiceReturns_RollResults(){
		RollResults results = sut.rollNDice(3);
		assertThat(results.rolls).isEqualTo(new int[]{5,5,2});
	}

	@Test
	public void sumInRollResults(){
		RollResults results = sut.rollNDice(1000);
		assertThat(results.sum).isEqualTo(3000);
	}

	@Test
	public void sumWithManyValues(){
		assertThat(sut.rollNDice(10000).sum).isEqualTo(50000);
	}
}
