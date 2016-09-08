package com.rafaels.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.rafaels.game.main.GameMain;

public class GameOverState extends State {
	private String playerScore;
	private Font font;
	
	//Constructor parametrizado con playerScore desde PlayState
	public GameOverState(int playerScore){
		//Convertimos un int en String
		this.playerScore = playerScore + ""; 
		font = new Font ("SansSerif", Font.BOLD, 50);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	//Pintamos en pantalla el fondo, Game over, la puntuaicon y texto.
	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		g.drawString("GAME OVER", 257, 175);
		g.drawString(playerScore, 385, 250);
		g.drawString("Presion una tecla", 240, 350);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Hace q al pulsar cualquier tecla vayamos al MenuState
	@Override
	public void onKeyPress(KeyEvent e) {
		setCurrentState(new MenuState());
		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
