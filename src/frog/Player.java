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
	// attributes of a dino
	private int x, y; // Position of dino
	private boolean alive; // lives
	private int width; // the size of dino
	private int height;
	
	private Image img; // image of the dino
	
	/* if filename is provided */
	public Player(String fileName, int width, int height) {
		// assignment statements for attributes
		x = 250;
		y = 700;
		alive = true;
		img = getImage(fileName);
		img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		
		init(x, y);

	}

	public void reset() {
		if (!alive) {
			x = 200;	//reset position
			y = 500;
		}
	}
	
	public void move() {
		if (x < 0) {
			x = 250;
			y = 700;
		}
		
		if (x >= 560 - 10) {
			x = 250;
			y = 700;
		}
		
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
		move(); //ask dino to update its location variables
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
