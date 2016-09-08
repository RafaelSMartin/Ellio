package com.rafaels.game.model;

import com.rafaels.framework.util.RandomNumberGenerator;

public class Cloud {
	private float x, y;
	private static final int VEL_X = -15;
	
	//Constructor parametrizado
	public Cloud(float x, float y){
		this.x = x;
		this.y = y;
	}

	//Acepta un delta para q el movimiento sea independiente de los frames
	//Cuando la nube deja de verse se le posiciona 1000px a la derecha con y aleatorio
	public void update(float delta){
		x += VEL_X * delta;
		
		if (x <= -200){
			//Reiniciar a la derecha
			x += 1000;
			y = RandomNumberGenerator.getRandIntBetween(20, 100);
		}
	}
	
	//Getters
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
}
