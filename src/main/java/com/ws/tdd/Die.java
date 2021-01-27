package com.ws.tdd;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Die implements IDie{

	@Override
	public int roll(int size) {
		float rnd = new Random().nextFloat();
		return 1+(int)(rnd*6);
	}
	
}
