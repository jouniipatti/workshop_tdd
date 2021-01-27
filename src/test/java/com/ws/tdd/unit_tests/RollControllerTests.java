package com.ws.tdd.unit_tests;

import com.ws.tdd.IDie;
import com.ws.tdd.RollController;
import com.ws.tdd.RollResults;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RollControllerTests {
	private RollController sut;
	private IDie die;

	@BeforeEach
	public void setup(){
		die = mock(IDie.class);
		sut = new RollController(die);
	}

	@Test
	public void sutCreated(){
		assertThat(sut).isNotNull();
	}

	@Test
	public void sutThrowsADie(){
		when(die.roll()).thenReturn(5);
		assertThat(sut.rollADie()).isEqualTo(5);
	}


	@Test
	public void testRollNDice(){
		when(die.roll()).thenReturn(5,5,2,4,4);
		int[] result = sut.rollNDice(5).rolls;
		assertThat(result).isEqualTo(new int[]{5,5,2,4,4});
	}

	@Test
	public void rollNDiceReturns_RollResults(){
		when(die.roll()).thenReturn(5,5,2);
		RollResults results = sut.rollNDice(3);
		assertThat(results.rolls).isEqualTo(new int[]{5,5,2});
	}

	@Test
	public void sumInRollResults(){
		when(die.roll()).thenReturn(3);
		RollResults results = sut.rollNDice(1000);
		assertThat(results.sum).isEqualTo(3000);
	}

	@Test
	public void sumWithManyValues(){
		when(die.roll()).thenReturn(5);
		assertThat(sut.rollNDice(10000).sum).isEqualTo(50000);
	}

	@Test
	public void negativeNumberOfDiceThrows(){
		assertThatThrownBy(() -> sut.rollNDice(-1)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void testRollEndpoint(){
		when(die.roll()).thenReturn(1,2,6,1,1);
		RollResults result = sut.rollDice("5");
		assertResults(result, 1,2,6,1,1);
	}

	private void assertResults(RollResults res, int...rolls){
		int sum = 0;
		for (int i=0; i<rolls.length; i++){
			sum += rolls[i];
		}
		assertThat(res.sum).isEqualTo(sum);
		assertThat(res.rolls).isEqualTo(rolls);		
	}
}
