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
	Broccoli leftBrocs3[] = new Broccoli[1];
	Broccoli leftBrocs2[] = new Broccoli[2];
	Broccoli leftBrocs1[] = new Broccoli[4];
	Broccoli rightBrocs2[] = new Broccoli[3];
	Broccoli rightBrocs1[] = new Broccoli[5];
	Broccoli botBrocs[] = new Broccoli[11];
	Log logs[] = new Log[3];
	Onion bot[] = new Onion[5];
	Onion mid[] = new Onion[5];
	Onion top[] = new Onion[5];
	Hostile steg[] = new Hostile[3];
	Hostile bronc[] = new Hostile[2];
	
	Music bg;

	Font small = new Font("Courier New", 1, 30);
	Font big = new Font("Courier New", 1, 50);
	Font biggest = new Font("Courier New", 1, 80);
	
	int width = 550;
	int height = 800;
	
	// ****************************paint
	// method******************************************
	public void paint(Graphics g) {
		super.paintComponent(g);

		grass.paint(g);
		ketchup.paint(g);
		for (Broccoli temp: leftBrocs3) {
			temp.paint(g);
		}
		for (Broccoli temp: leftBrocs2) {
			temp.paint(g);
		}
		for (Broccoli temp: leftBrocs1) {
			temp.paint(g);
		}
		for (Broccoli temp: rightBrocs2) {
			temp.paint(g);
		}
		for (Broccoli temp: rightBrocs1) {
			temp.paint(g);
		}
		for (Broccoli temp: botBrocs) {
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
		for (Hostile temp: steg) {
			temp.paint(g);
		}
		for (Hostile temp: bronc) {
			temp.paint(g);
		}
		p.paint(g);
		
		// collision with dinos
		if (p.isColliding(steg) || p.isColliding(bronc)) {
			p.reset();
		}
		
		//collision with broccoli
		p.isColliding(botBrocs);
		p.isColliding(leftBrocs1);
		p.isColliding(leftBrocs2);
		p.isColliding(leftBrocs3);
		p.isColliding(rightBrocs1);
		p.isColliding(rightBrocs2);
		
		//collision with onions and logs
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
		//System.out.println("l:" + logs[0].getVx());
		//System.out.println("p: " + p.getVx());
	}

	public void update() {
		// tyson said to use this, maybe eventually
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
		
		for (int i = 0; i < leftBrocs3.length; i++) {
			leftBrocs3[i] = new Broccoli("broccoli.png", 0 + i*50, 600, 50, 50);
		}
		for (int i = 0; i < leftBrocs2.length; i++) {
			leftBrocs2[i] = new Broccoli("broccoli.png", 0 + i*50, 650, 50, 50);
		}
		for (int i = 0; i < leftBrocs1.length; i++) {
			leftBrocs1[i] = new Broccoli("broccoli.png", 0 + i*50, 700, 50, 50);
		}
		for (int i = 0; i < rightBrocs2.length; i++) {
			rightBrocs2[i] = new Broccoli("broccoli.png", 500 - i*50, 650, 50, 50);
		}
		for (int i = 0; i < rightBrocs1.length; i++) {
			rightBrocs1[i] = new Broccoli("broccoli.png", 500 - i*50, 700, 50, 50);
		}
		for (int i = 0; i < botBrocs.length; i++) {
			botBrocs[i] = new Broccoli("broccoli.png", 0 + i*50, 750, 50, 50);
		}
		for (int i = 0; i < steg.length; i++) {
			steg[i] = new Hostile("steg.png", 0, 350 + i*100, (Math.random()>.5)?Math.random()*(5-3+1)+3:Math.random()*(-5+3+1)-3, 50, 50);
		}
		for (int i = 0; i < bronc.length; i++) {
			bronc[i] = new Hostile("bronc.png", 0, 400 + i*100, (Math.random()>.5)?Math.random()*(5-3+1)+3:Math.random()*(-5+3+1)-3, 50, 50);
		}
		for (int i = 0; i < logs.length; i++) {
			logs[i] = new Log("french rect.png", 0, 250 - i*100, (Math.random()>.5)?Math.random()*(7-3+1)+3:Math.random()*(-7+3+1)-3, 300, 50);
		}
		for (int i = 0; i < bot.length; i++) {
			bot[i] = new Onion("onion.png", 50 + i*100, 300, 50, 50);
		}
		
		for (int i = 0; i < mid.length; i++) {
			mid[i] = new Onion("onion.png", 0 + i*100, 200, 50, 50);
		}
		
		for (int i = 0; i < top.length; i++) {
			top[i] = new Onion("onion.png", 75 + i*100, 100, 50, 50);
		}
	
		f.addMouseListener(this);
		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		Music bg = new Music("music.wav", true);
		//bg.loop();
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