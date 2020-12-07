package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Log{
	// attributes
	private int x, y; // Position 
	private double vx, vy;
	private double scaleX, scaleY;
	private int height, width;
	
	private Image img; // image
	
	/* if filename is provided */
	public Log(String fileName, int x, int y, double vx, double scaleX, double scaleY) {
		// assignment statements for attributes
		this.x = x;
		this.y = y;
		this.vx = vx;
		vy = 0;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		width = 50;
		height = 50;
		img = getImage(fileName);
		
		init(x, y);
	}
	
	public void move() {
		x += vx;
		y += vy;
		tx.setToTranslation(x, y);
	}
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move(); //ask broccoli to update its location variables
		tx.scale(scaleX, scaleY);
		g2.drawImage(img, tx, null);
		
		if (x < -300) {
			vx *= -1;
		}
		if (x > 600) {
			vx *= -1;
		}
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Log.class.getResource(path);
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
	
	public double getVx() {
		return vx;
	}
	
	/* Helper function for collision detection later */
	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x,y,width,height);
		return temp;
	}

}
