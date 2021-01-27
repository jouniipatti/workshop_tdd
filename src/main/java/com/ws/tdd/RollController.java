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
	public RollResults rollDice(@RequestParam(name="dice")String nDice){
		return rollNDice(Integer.parseInt(nDice));
	}

	public RollResults rollNDice(int nDice) {
		if (nDice < 0) throw new IllegalArgumentException();
		int[] result = new int[nDice];
		int sum = 0;
		for (int i=0; i<nDice; i++){
			int roll = rollADie();
			sum += roll;
			result[i] = roll;
		}
		return new RollResults(result, sum);
	}


}
