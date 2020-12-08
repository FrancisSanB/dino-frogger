package frog;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Broccoli{
	// attributes of a broccoli
	private int x, y; // Position of broccoli
	private int height, width;
	
	private Image img; // image of the broccoli
	
	/* if filename is provided */
	public Broccoli(String fileName, int x, int y, int width, int height) {
		// assignment statements for attributes
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		img = getImage(fileName);
		img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		
		init(x, y);
	}
	
	public void move() {
		tx.setToTranslation(x, y);
	}
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move(); //ask broccoli to update its location variables
		g2.drawImage(img, tx, null);
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Broccoli.class.getResource(path);
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
