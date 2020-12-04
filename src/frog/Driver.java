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

	Player dino;
	Background grass;
	Background ketchup;
	Broccoli brocs[] = new Broccoli[10];

	Font big = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	Font biggest = new Font("Courier New", 1, 80);
	
	int width = 560;
	int height = 800;
	
	// ****************************paint
	// method******************************************
	public void paint(Graphics g) {
		super.paintComponent(g);

		ketchup.paint(g);
		grass.paint(g);
		dino.paint(g);
		
		for(Broccoli temp: brocs) {
			temp.paint(g);
		}
		
		if (dino.getX() < 0) {
			dino.setX(250);
			dino.setY(700);
		}
		
		if (dino.getX() >= width - 10) {
			dino.setX(250);
			dino.setY(700);
		}

		//System.out.println(dino.getX() + "," + dino.getY());
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
		f.setTitle("Frogger");
		f.setSize(width, height);
		f.setResizable(false);
		f.addKeyListener(this);
		
		dino = new Player("pter.png");
		ketchup = new Background("ketchupriver.png", 0, 0, 3, 3);
		grass = new Background("grass.png", 0, 300, 1.25, 1.25);
		
		for (int i = 0; i < brocs.length; i++) {
			brocs[i] = new Broccoli("broccoli.png", 0, height - i*50, 0.1, 0.1);
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
		dino.hop(e);
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