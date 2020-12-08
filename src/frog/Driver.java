package frog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener {

	Player p;
	Background grass;
	Background ketchup;
	Broccoli fourBrocs[] = new Broccoli[1];
	Broccoli threeBrocs[] = new Broccoli[2];
	Broccoli twoBrocs[] = new Broccoli[4];
	Broccoli threeBrocs2[] = new Broccoli[3];
	Broccoli twoBrocs2[] = new Broccoli[5];
	Broccoli oneBrocs[] = new Broccoli[11];
	Log logs[] = new Log[3];
	Onion bot[] = new Onion[5];
	Onion mid[] = new Onion[5];
	Onion top[] = new Onion[5];
	Hostile dinos[] = new Hostile[5];

	Font big = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	Font biggest = new Font("Courier New", 1, 80);
	
	int width = 560;
	int height = 800;
	
	// ****************************paint
	// method******************************************
	public void paint(Graphics g) {
		super.paintComponent(g);

		grass.paint(g);
		ketchup.paint(g);
		for (Broccoli temp: fourBrocs) {
			temp.paint(g);
		}
		for (Broccoli temp: threeBrocs) {
			temp.paint(g);
		}
		for (Broccoli temp: twoBrocs) {
			temp.paint(g);
		}
		for (Broccoli temp: threeBrocs2) {
			temp.paint(g);
		}
		for (Broccoli temp: twoBrocs2) {
			temp.paint(g);
		}
		for (Broccoli temp: oneBrocs) {
			temp.paint(g);
		}
		for (Log temp: logs) {
			temp.paint(g);
		}
		for (Onion temp: bot) {
			temp.paint(g);
		}
		for (Onion temp: mid) {
			temp.paint(g);
		}
		for (Onion temp: top) {
			temp.paint(g);
		}
		for (Hostile temp: dinos) {
			temp.paint(g);
		}
		p.paint(g);
		
		// collision with dinos
		if (p.isColliding(dinos)) {
			p.reset();
		}
		
		//collision with broccoli
		if (p.isColliding(oneBrocs) ||
				p.isColliding(twoBrocs) ||
				p.isColliding(threeBrocs) ||
				p.isColliding(fourBrocs) ||
				p.isColliding(twoBrocs2) ||
				p.isColliding(threeBrocs2)) {
			p.reset();
		}
		
		//collision with onions
		if (p.getY() <= 300 && p.getY() > 200 && !p.isColliding(bot)) {
			if (!p.isColliding(logs)) {
				p.reset();
			}
		}
		if (p.getY() <= 200 && p.getY() > 100 && !p.isColliding(mid)) {
			if (!p.isColliding(logs)) {
				p.reset();
			}
		}
		if (p.getY() <= 100 && p.getY() > 0 && !p.isColliding(top)) {
			if (!p.isColliding(logs)) {
				p.reset();
			}
		}
		
		//movement with river objects
		if (p.isColliding(logs)) {
			int i = p.whichLog(logs);
			p.setVx(logs[i].getVx());
		} else {
			p.setVx(0);
		}

		//debug
		//System.out.println(p.getX() + "," + p.getY());
		//System.out.println("d:" + logs[0].getVx());
		//System.out.println("p: " + p.getVx());
	}

	public void update() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Dino Nuggets");
		f.setSize(width, height);
		f.setResizable(false);
		f.addKeyListener(this);
		
		p = new Player("pter.png", 50, 50);
		ketchup = new Background("ketchupriver.png", 0, 50, width, 300);
		grass = new Background("sand.jpg", 0, 0, width, height);
		
		for (int i = 0; i < fourBrocs.length; i++) {
			fourBrocs[i] = new Broccoli("broccoli.png", 0 + i*50, 600, 50, 50);
		}
		for (int i = 0; i < threeBrocs.length; i++) {
			threeBrocs[i] = new Broccoli("broccoli.png", 0 + i*50, 650, 50, 50);
		}
		for (int i = 0; i < twoBrocs.length; i++) {
			twoBrocs[i] = new Broccoli("broccoli.png", 0 + i*50, 700, 50, 50);
		}
		for (int i = 0; i < threeBrocs2.length; i++) {
			threeBrocs2[i] = new Broccoli("broccoli.png", 500 - i*50, 650, 50, 50);
		}
		for (int i = 0; i < twoBrocs2.length; i++) {
			twoBrocs2[i] = new Broccoli("broccoli.png", 500 - i*50, 700, 50, 50);
		}
		for (int i = 0; i < oneBrocs.length; i++) {
			oneBrocs[i] = new Broccoli("broccoli.png", 0 + i*50, 750, 50, 50);
		}
		for (int i = 0; i < dinos.length; i++) {
			dinos[i] = new Hostile("steg.png", 0, 350 + i*50, Math.random()*(3+3+1)-3, 50, 50);
		}
		for (int i = 0; i < logs.length; i++) {
			logs[i] = new Log("french rect.png", 0, 250 - i*100, Math.random()*(3-1+1)+1, 300, 50);
		}
		
		for (int i = 0; i < bot.length; i++) {
			bot[i] = new Onion("onion.png", 75 + i*100, 300, 50, 50);
		}
		
		for (int i = 0; i < mid.length; i++) {
			mid[i] = new Onion("onion.png", 0 + i*100, 200, 50, 50);
		}
		
		for (int i = 0; i < top.length; i++) {
			top[i] = new Onion("onion.png", 50 + i*100, 100, 50, 50);
		}
	
		f.addMouseListener(this);
		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		p.hop(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*
		 * turn off velocity for Frog if you don't want it moving when you have stopped
		 * pressing the keys
		 */

		// do the same thing for the other keys
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}