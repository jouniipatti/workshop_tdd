package com.ws.tdd;

import java.util.Random;
import org.springframework.web.bind.annotation.*;

@RestController
public class RollController {
	private Random randomizer;

	public RollController(){
		this.randomizer = new Random();
	}

	@RequestMapping("/roll")
	public String rollDice(){
		return "TODO: TDD-workshop!";
	}

	public int rollADie() {
		float rnd = randomizer.nextFloat();
		return 1+(int)(rnd*6);
	}

	public RollResults rollNDice(int nDice) {
		int[] result = new int[nDice];
		for (int i=0; i<nDice; i++){
			result[i] = rollADie();
		}
		return new RollResults(result);
	}

	public void useSeed(int seed) {
		this.randomizer = new Random(seed);
	}


}
