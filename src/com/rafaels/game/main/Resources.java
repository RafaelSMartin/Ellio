package com.rafaels.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import com.rafaels.framework.animation.Animation;
import com.rafaels.framework.animation.Frame;



public class Resources {

	public static BufferedImage welcome, iconimage, block, cloud1, cloud2, duck, grass, gargolaOriginal;
	public static BufferedImage jump, run1, run2, run3, run4, run5, selector;
	public static BufferedImage gar1, gar2, gar3, gar4, gar5, gar6, gar7, gar8, gar9, gar10, gar11, gar12, gar13, gar14, gar15, gar16;
	public static AudioClip hit, onjump;
	public static Color skyBlue;
	public static Animation runAnim, runAnimGargola;
	public static float velGar = .02f;
	
	public static void load(){
		welcome = loadImage("welcome.png");
		iconimage = loadImage("iconimage.png");
		block = loadImage("block.png");
		cloud1 = loadImage("cloud1.png");
		cloud2 = loadImage("cloud2.png");
		duck = loadImage("duck.png");
		grass = loadImage("grass.png");
		gargolaOriginal = loadImage("gargolaOriginal.png");
		jump = loadImage("jump.png");
		run1 = loadImage("run_anim1.png");
		run2 = loadImage("run_anim2.png");
		run3 = loadImage("run_anim3.png");
		run4 = loadImage("run_anim4.png");
		run5 = loadImage("run_anim5.png");
		selector = loadImage("selector.png");
		gar1 = loadImage("gar1.png");
		gar2 = loadImage("gar2.png");
		gar3 = loadImage("gar3.png");
		gar4 = loadImage("gar4.png");
		gar5 = loadImage("gar5.png");
		gar6 = loadImage("gar6.png");
		gar7 = loadImage("gar7.png");
		gar8 = loadImage("gar8.png");
		gar9 = loadImage("gar9.png");
		gar10 = loadImage("gar10.png");
		gar11 = loadImage("gar11.png");
		gar12 = loadImage("gar12.png");
		gar13 = loadImage("gar13.png");
		gar14 = loadImage("gar14.png");
		gar16 = loadImage("gar16.png");
		gar15 = loadImage("gar15.png");
		
		hit = loadSound("hit.wav");
		onjump = loadSound("onjump.wav");
		skyBlue = new Color(208, 244, 247);
		
		Frame f1 = new Frame(run1, .1f);
		Frame f2 = new Frame(run2, .1f);
		Frame f3 = new Frame(run3, .1f);
		Frame f4 = new Frame(run4, .1f);
		Frame f5 = new Frame(run5, .1f);
		runAnim = new Animation(f1, f2, f3, f4, f5, f3, f2);
		Frame g1 = new Frame(gar1, velGar);
		Frame g2 = new Frame(gar2, velGar);
		Frame g3 = new Frame(gar3, velGar);
		Frame g4 = new Frame(gar4, velGar);
		Frame g5 = new Frame(gar5, velGar);
		Frame g6 = new Frame(gar6, velGar);
		Frame g7 = new Frame(gar7, velGar);
		Frame g8 = new Frame(gar8, velGar);
		Frame g9 = new Frame(gar9, velGar);
		Frame g10 = new Frame(gar10, velGar);
		Frame g11 = new Frame(gar11, velGar);
		Frame g12 = new Frame(gar12, velGar);
		Frame g13 = new Frame(gar13, velGar);
		Frame g14 = new Frame(gar14, velGar);
		Frame g15 = new Frame(gar15, velGar);
		Frame g16 = new Frame(gar16, velGar);
		runAnimGargola = new Animation(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16);
		
	}
	
	private static AudioClip loadSound(String filename){
		URL fileURL = Resources.class.getResource("/resources/" + filename);
		return Applet.newAudioClip(fileURL);		
	}
	
	private static BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try {
			img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
		} catch (Exception e) {
			System.out.println("Error de lectura: " + filename);
			e.printStackTrace();
		}
		return img;
	}
}
