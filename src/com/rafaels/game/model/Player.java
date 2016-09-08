package com.rafaels.game.model;

import java.awt.Rectangle;

import com.rafaels.game.main.Resources;

public class Player {
	private float x, y;
	private int width, height, velY;
	private Rectangle rect, duckRect, ground;
	
	private boolean isAlive;
	private boolean isDucked;
	private float duckDuration = .6f;
	
	private static final int JUMP_VELOCITY = -600;
	private static final int ACCEL_GRAVITY = 1800;
	
	public Player(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		ground = new Rectangle(0, 405, 800, 45);
		rect = new Rectangle();
		duckRect = new Rectangle();
		isAlive = true;
		isDucked = false;
		updateRects();
	}
	
	//Se recibe delta de la clase PlayState
	//Comprobamos si el jugador esta agachado y bajamos duckDuration lo necesario
	//Comprobamos si se esta en un salto, isGrounded sera false,  y aplicamos gravedad
	//Al final escalamos la velY segun delta para obtener la verdadera
	//velocidad independiente de la tasa de frames
	//Actualizamos los bounding box
	public void update(float delta){
		if (duckDuration > 0 && isDucked) {
			duckDuration -= delta;
		} else {
			isDucked = false;
			duckDuration = .6f;
		}
		if (!isGrounded()) {
			velY += ACCEL_GRAVITY * delta;
		} else {
			y = 406 - height;
			velY = 0;
		}
		y += velY * delta;
		updateRects();
	}
	
	//Actualiza los Bounding box a las posiciones haciendo casting de int
	public void updateRects(){
		rect.setBounds((int) x + 10, (int) y, width - 20, height);
		duckRect.setBounds((int) x, (int) y + 20, width, height - 20);
	}
	
	//Recibira llamadas cuando se teclee el salto
	//Comprueba si el jugador esta en el suelo
	//Suena el sonido de salto
	//En el salto se pone q ya no esta agachado y la duracion de agachado
	//Se aplica la velocidades y se actualizan los bounding box
	public void jump(){
		if (isGrounded()){
			Resources.onjump.play();
			isDucked = false;
			duckDuration = .6f;
			y -= 10;
			velY = JUMP_VELOCITY;
			updateRects();
		}
	}
	
	//Comprueba si el jugador esta sobre el suelo
	public void duck(){
		if (isGrounded())
			isDucked = true;
	}
	
	//Se invoca cuando el jugador colisiona con un bloque
	//Recibe dX -> cantidad de pixeles que retrocede
	//Si mas dela mitad del cuerpo del jugador esta fuera de la pantalla muere
	//Actualizamos el bounding box
	public void pushBack(int dX){
		Resources.hit.play();
		x -= dX;
		if (x < -width / 2) {
			isAlive = false;
		}
		rect.setBounds((int) x, (int) y, width, height);
	}

	//Devuelve true si el rectangulo primario rect colisiona con el suelo
	public boolean isGrounded(){
		return rect.intersects(ground);
	}
	
	//getters
	public boolean isDucked(){
		return isDucked;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getVelY(){
		return velY;
	}
	
	public Rectangle getRect(){
		return rect;
	}
	
	public Rectangle getDuckRect(){
		return duckRect;
	}
	
	public Rectangle getGround(){
		return ground;
	}
	
	public boolean isAlive(){
		return isAlive;		
	}
	
	public float getDuckDuration(){
		return duckDuration;
	}
}
