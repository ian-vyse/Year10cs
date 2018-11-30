package game_1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;

import sun.audio.*; //All of these import needed classes and bits of code

public class MyCanvas extends Canvas implements KeyListener { //Inherits certain code
	GoodGuy hmmm = new GoodGuy(10,10,30,30, "files/right.png");
	GoodGuy_1 hmmmm = new GoodGuy_1(10,340,30,30, "files/right2.png");
	PlayerOneWin one = new PlayerOneWin (250, 30, 920, 240, "files/One.png");
	PlayerTwoWin two = new PlayerTwoWin (250, 30, 920, 240, "files/Two.png");
	boolean playerOneWin = false;
	boolean playerTwoWin = false;
//	LinkedList badguys = new LinkedList();
	LinkedList obstacles = new LinkedList();
	public MyCanvas() {
		this.setSize(1440,420); //Creates Canvas
		this.addKeyListener(this); //Adds KeyListener to listen for key presses
		playIt("files/Subwoofer.wav"); //Defines "filename" for audio
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
//		for (int i = 0; i < 10; i++) {
//			BadGuy bg = new BadGuy(rand.nextInt(winwidth), rand.nextInt(winheight),50,50,"files/oof.png");
//			Rectangle r = new Rectangle (100, 100, 30, 30);
//			if (r.contains(hmmm.getxCoord(),hmmm.getyCoord())) {
//				System.out.println("Badguy ontop of oof");
//				continue;
//			}
//			if (r.contains(hmmmm.getxCoord(),hmmmm.getyCoord())) {
//				System.out.println("Badguy ontop of oof");
//				continue;
//			}
//			badguys.add(bg);
//		}
		for (int i = 0; i < 40; i++) {
			Obstacle bg = new Obstacle(rand.nextInt(winwidth), rand.nextInt(winheight),50,50,"files/oof.png");
			Rectangle r = new Rectangle (100, 100, 30, 30);
			if (r.contains(hmmm.getxCoord(),hmmm.getyCoord())) {
				System.out.println("Obstacle ontop of oof");
				continue;
			}
			if (r.contains(hmmmm.getxCoord(),hmmmm.getyCoord())) {
				System.out.println("Obstacle ontop of oof");
				continue;
			}
			obstacles.add(bg);
		}
	}
	public void playIt(String filename) { //playIt class for playing audio sound track
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);	
		} catch(IOException e) {
			System.out.println(e);
		}
	}
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(1420, 0, 20, 400);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 1420, 400);
		g.setColor(Color.WHITE);
		g.fillRect(0, 70, 1440, 5);
		g.fillRect(0, 130, 1440, 5);
		g.fillRect(0, 190, 1440, 5);
		g.fillRect(0, 250, 1440, 5);
		g.fillRect(0, 310, 1440, 5);
		g.fillRect(0, 370, 1440, 5);
		g.drawImage(hmmm.getImg(), hmmm.getxCoord(), hmmm.getyCoord(), hmmm.getWidth(), hmmm.getHeight(), this);
		g.drawImage(hmmmm.getImg(), hmmmm.getxCoord(), hmmmm.getyCoord(), hmmmm.getWidth(), hmmmm.getHeight(), this);
		if (playerOneWin == true) {
			g.drawImage(one.getImg(), one.getxCoord(), one.getyCoord(), one.getWidth(), one.getHeight(), this);
			return;
		} else if (playerTwoWin == true) {
			g.drawImage(two.getImg(), two.getxCoord(), two.getyCoord(), two.getWidth(), two.getHeight(), this);
			return;
		}
		//		for (int i = 0; i < badguys.size(); i ++) {
//			BadGuy bg = (BadGuy) badguys.get (i);
//			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(),bg.getHeight(), bg.getWidth(), this);
//		}
		for (int i = 0; i < obstacles.size(); i ++) {
			Obstacle bg = (Obstacle) obstacles.get (i);
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(),bg.getHeight(), bg.getWidth(), this);
		}
	}
	public void keyTyped (KeyEvent e) {
		//System.out.println(e);
	}
	public void keyPressed (KeyEvent e) {
		System.out.println(e);
		hmmm.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
		hmmmm.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
		Rectangle f = new Rectangle(1420, 0, 20, 440);
		if (f.contains(hmmm.getxCoord(), hmmm.getyCoord())) {
			System.out.println("PLayer 1 has won.");
			playerOneWin = true;
			return;
		}
		if (f.contains(hmmmm.getxCoord(), hmmmm.getyCoord())) {
			System.out.println("Player 2 has won.");
			playerTwoWin = true;
			return;
		}
//		for(int i = 0; i < badguys.size(); i++) {
//			BadGuy bg = (BadGuy) badguys.get(i);
//			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getHeight(),bg.getWidth());
//			if (r.contains(hmmm.getxCoord(), hmmm.getyCoord())) {
//				System.out.println("Badguy hit by hmmm.");
//				badguys.remove(i);
//			}
//			if (r.contains(hmmmm.getxCoord(), hmmmm.getyCoord())) {
//				System.out.println("Badguy hit by hmmmm.");
//				badguys.remove(i);
//			}
//		}
		for(int i = 0; i < obstacles.size(); i++) {
			Obstacle bg = (Obstacle) obstacles.get(i);
			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getHeight(),bg.getWidth());
			if (r.contains(hmmm.getxCoord(), hmmm.getyCoord())) {
				System.out.println("Obstacle hit by hmmm.");
				hmmm.moveIt(-99, 1440, 440);
			}
			if (r.contains(hmmmm.getxCoord(), hmmmm.getyCoord())) {
				System.out.println("Obstacle hit by hmmmm.");
				hmmmm.moveIt(-99, 1440, 440);
			}
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
