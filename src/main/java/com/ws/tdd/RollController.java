package com.ws.tdd;

import org.springframework.web.bind.annotation.*;

@RestController
public class RollController {
	private IDie die;

	public RollController(IDie die){
		this.die = die;
	}

	public int rollADie(){
		return this.die.roll();
	}

	@RequestMapping("/roll")
	public String rollDice(){
		return "TODO: TDD-workshop!";
	}

	public RollResults rollNDice(int nDice) {
		int[] result = new int[nDice];
		for (int i=0; i<nDice; i++){
			result[i] = rollADie();
		}
		return new RollResults(result, 3000);
	}


}
