package com.rafaels.game.model;

import java.awt.Rectangle;

import com.rafaels.framework.util.RandomNumberGenerator;

public class Block {
	private float x, y;
	private int width, height;
	private Rectangle rect;
	private boolean visible;
	
	private static final int UPPER_Y = 275;
	private static final int LOWER_Y = 355;
	
	//Constructor 
	public Block(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle((int) x, (int) y, width, height);
		visible = false;
	}

	//La velocidad viene de PlayState y un delta para el mov independiente de los frames
	//Si x es menor de 50 el bloque desaparecera y se reiniciara
	//Se actualiza el boounding box
	public void update(float delta, float velX){
		x += velX * delta;
		if (x <= -50) {
			reset();
		}
		updateRect();
	}
	
	public void updateRect(){
		rect.setBounds((int) x, (int) y, width, height);
	}
	
	//Se pone visible y 1 entre 3 de q el bloque sea alto
	//Se mueve 1000px a la derecha
	public void reset(){
		visible = true;
		
		if (RandomNumberGenerator.getRandInt(3) == 0) {
			y = UPPER_Y;
		} else {
			y = LOWER_Y;
		}
		x += 1000;
	}
	//Cuando el jugador colisiona con el bloque retrocede 30px y desaparece el bloque
	public void onCollide(Player p){
		visible = false;
		//Viene de Player
		p.pushBack(30);
	}
	
	//Getters
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;		
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public Rectangle getRect(){
		return rect;
	}
}
