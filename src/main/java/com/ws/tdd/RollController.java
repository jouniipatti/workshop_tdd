package com.ws.tdd;

import org.springframework.web.bind.annotation.*;

@RestController
public class RollController {

	@RequestMapping("/roll")
	public String rollDice(){
		return "TODO: TDD-workshop!";
	}

}
