package com.rafaels.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.rafaels.game.state.PlayState;
import com.rafaels.game.main.GameMain;
import com.rafaels.game.main.Resources;

public class MenuState extends State{
	//El valor de la flecha selectora, 0->Play 1->Exit
	private int currentSelection = 0;

	@Override
	public void init() {
		//System.out.println("dentro de MenuState");		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		//Dibuja en pantalla welcome.png en (0,0)
		g.drawImage(Resources.welcome, 0, 0, null);	
		
		//Dibujo la flecha en play si es 0 y en exit si es 1
		if (currentSelection == 0)
			g.drawImage(Resources.selector, 335, 241, null);
		else
			g.drawImage(Resources.selector, 335, 291, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		//System.out.println("on Click!! de MenuState");
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		//System.out.println("on KeyPress!! de MenuState");		
		//Recojo en key la tecla pulsada
		int key = e.getKeyCode();
		
		//Si se pulsa enter o espacio y el selector esta en play(0), creo el juego
		//Si no me salgo con exit
		//Si pulso flecha arriba el selector va a play y 1 a exit
		if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
			if (currentSelection == 0) {
				setCurrentState(new PlayState());
			} else if (currentSelection == 1) {
				GameMain.sGame.exit();
			}
		} else if (key == KeyEvent.VK_UP) {
			currentSelection = 0;
		} else if (key == KeyEvent.VK_DOWN) {
			currentSelection = 1;
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		//System.out.println("on KeyRelease!! de MenuState");
		
	}

}
