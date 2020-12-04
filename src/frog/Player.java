package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Player{
	// attributes of a frog
	private int x, y; // Position of frog
	private boolean alive; // lives
	private int width; // the size of frog
	private int height;
	
	private Image img; // image of the frog
	private Image left,right,up,down;

	public Player() {
		// assignment statements for attributes
		x = 250;
		y = 700;
		width = 50;
		height = 50;
		left = getImage("frogleft.png");
		right = getImage("frogright.png");
		up = getImage("bronc.png");
		down = getImage("frogdown.png");
		img = up;
		
		init(x, y);
	}
	
	/* if filename is provided */
	public Player(String fileName) {
		// assignment statements for attributes
		x = 200;
		y = 500;
		width = 50;
		height = 50;
		img = getImage(fileName);
		init(x, y);

	}

	public void reset() {
		x = 200;	//reset position
		y = 500;
		img = up;	//reset img
	}
	
	public void move() {
		tx.setToTranslation(x, y);
	}

	//move the main dino
	public void hop(KeyEvent e) {
		if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
			y += 50;
		}
		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
			y -= 50;
		}
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			x += 50;
		}
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			x -= 50;
		}
		tx.setToTranslation(x, y);
		
	}
	
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move(); //ask frog to update its location variables
		tx.scale(0.25, 0.25);
		g2.drawImage(img, tx, null);
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Player.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	// setters and getters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	/* Helper function for collision detection later */
	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x,y,width,height);
		return temp;
	}

}
