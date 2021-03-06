package com.rafaels.game.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.rafaels.game.main.GameMain;
import com.rafaels.game.main.Resources;
import com.rafaels.game.model.Block;
import com.rafaels.game.model.Cloud;
import com.rafaels.game.model.Player;

public class PlayState extends State{
	private Player player;
	private ArrayList<Block> blocks;
	private Cloud cloud, cloud2;
	
	private Font scoreFont;
	private int playerScore = 0;
	
	private static final int BLOCK_HEIGHT = 50;
	private static final int BLOCK_WIDTH = 40;	
	private int blockSpeed = -200;
	
	private static final int PLAYER_WIDTH = 66;
	private static final int PLAYER_HEIGHT = 92;

	@Override
	public void init() {
		player = new Player(160, GameMain.GAME_HEIGHT - 45 - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		blocks = new ArrayList<Block>();
		cloud = new Cloud(100, 100);
		cloud2 = new Cloud(500, 50);
		scoreFont = new Font("SansSerif", Font.BOLD, 25);
		
		//Creamos 5 bloques con separacion 200px y las metemos en el array blocks
		for (int i = 0; i < 5; i++){
			Block b = new Block (i * 200, GameMain.GAME_HEIGHT - 95, BLOCK_WIDTH, BLOCK_HEIGHT);
			blocks.add(b);
		}
		
	}

	@Override
	public void update(float delta) {
		if (!player.isAlive())
			setCurrentState(new GameOverState(playerScore/100));
		
		playerScore += 1;
		
		if (playerScore % 500 == 0 && blockSpeed > -280)
			blockSpeed -= 10;
		
		cloud.update(delta);
		cloud2.update(delta);
		Resources.runAnim.update(delta);
		Resources.runAnimGargola.update(delta);
		player.update(delta);
		updateBlocks(delta);		
	}
	//Usamos bucle foreach para recorrer ArrayList blocks de objetos Block
	//haciendo una iteracion por cada elemento de la lista y actualizando los bloques
	//Pasamos tb la misma blockSpeed a cada objeto Block
	//Comprobamos las colisions siempre q el bloque sea visible
	//Si esta agachado y su bounding box secundario intersecta con el del bloque
	//Si NO esta agachado y su bounding box secundario intersecta con el del bloque
	//si se detecta colision se llama a onCollide
	private void updateBlocks(float delta){
		for (Block b : blocks){
			b.update(delta, blockSpeed);
			
			if (b.isVisible()){
				if (player.isDucked() && b.getRect().intersects(player.getDuckRect())){
					b.onCollide(player);
				}
				else if (!player.isDucked() && b.getRect().intersects(player.getRect())){
					b.onCollide(player);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Resources.skyBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		renderPlayer(g);
		renderBlocks(g);
		renderSun(g);
		renderClouds(g);
		g.drawImage(Resources.grass, 0, 405, null);
		renderScore(g);		
	}
	
	private void renderScore(Graphics g){
		g.setFont(scoreFont);
		g.setColor(Color.GRAY);
		g.drawString("" + playerScore / 100, 20, 30);		
	}
	
	private void renderPlayer(Graphics g){
		if(player.isGrounded()){
			if(player.isDucked()){
				g.drawImage(Resources.duck, (int) player.getX(), (int) player.getY(), null);
			} else {
				Resources.runAnim.render(g, (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
			}
		} else {
			g.drawImage(Resources.jump, (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight(), null);		
		}
	}
	
	private void renderBlocks(Graphics g){
		for (Block b : blocks){
			if(b.isVisible()){
				Resources.runAnimGargola.render(g, (int) b.getX(), (int) b.getY(), BLOCK_WIDTH, BLOCK_HEIGHT);
				//g.drawImage(Resources.gargolaOriginal, (int) b.getX(), (int) b.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
			}
		}
	}
	
	private void renderSun(Graphics g){
		g.setColor(Color.ORANGE);
		g.fillOval(715, -85, 170, 170);
		g.setColor(Color.YELLOW);
		g.fillOval(725, -75, 150, 150);
	}
	
	private void renderClouds(Graphics g){
		g.drawImage(Resources.cloud1, (int) cloud.getX(), (int) cloud.getY(), 100, 60, null);
		g.drawImage(Resources.cloud2, (int) cloud.getX(), (int) cloud.getY(), 100, 60, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//Control de teclas, interaccion con el usuario
	@Override
	public void onKeyPress(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			player.jump();
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			player.duck();
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
