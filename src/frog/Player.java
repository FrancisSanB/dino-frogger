package frog;

import java.awt.Color;
import java.awt.Font;
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
	private double vx, vy;
	private boolean alive; // lives
	private int deaths; //num of deaths
	private int width; // the size of dino
	private int height;
	private boolean isColN, isColS, isColE, isColW;
	
	private Image img; // image of the dino
	Font small = new Font("Courier New", Font.BOLD, 50);

	/* if filename is provided */
	public Player(String fileName, int width, int height) {
		// assignment statements for attributes
		x = 250;
		y = 700;
		vx = 0;
		vy = 0;
		this.width = width;
		this.height = height;
		isColN = isColS = isColE = isColW = false;
		alive = true;
		deaths = 0;
		img = getImage(fileName);
		img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);

		init(x, y);
	}

	public boolean isColliding (Hostile[] d) {
		for (int i = 0; i < d.length; i++) {
			if (getRect().intersects(d[i].getRect())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isColliding (Onion[] d) {
		for (int i = 0; i < d.length; i++) {
			if (getRect().intersects(d[i].getRect())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isColliding (Log[] d) {
		for (int i = 0; i < d.length; i++) {
			if (getRect().intersects(d[i].getRect())) {
				return true;
			}
		}
		return false;
	}
	
	public void isColliding (Broccoli[] d) {
		for (int i = 0; i < d.length; i++) {
			if (northRect().intersects(d[i].getRect())) {
				//System.out.println("n");
				isColN = true;
			}
			if (southRect().intersects(d[i].getRect())) {
				//System.out.println("s");
				isColS = true;
			}
			if (eastRect().intersects(d[i].getRect())) {
				//System.out.println("e");
				isColE = true;
			}
			if (westRect().intersects(d[i].getRect())) {
				//System.out.println("w");
				isColW = true;
			}
		}
	}
	
	public int whichLog (Log[] d) {
		for (int i = 0; i < d.length; i++) {
			if (getRect().intersects(d[i].getRect())) {
				return i;
			}
		}
		return -1;
	}
	
	public void onionSnap (Onion[] o) {
		for (int i = 0; i < o.length; i++) {
			if (getRect().intersects(o[i].getRect())) {
				x = o[i].getX();
				y = o[i].getY();
			}
		}
	}
	
	public void reset() {
		x = 250;	//reset position
		y = 700;
		vx = 0;
		vy = 0;
		deaths++;
	}
	
	public void move() {
		if (x < 0) {
			reset();
		}
		if (x >= 560 - 10) {
			reset();
		}
		if (y < 0) {
			reset();
		}
		
		x += vx;
		y += vy;
		
		tx.setToTranslation(x, y);
	}

	//move the main dino
	public void hop(KeyEvent e) {
		if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
			if (!isColS) {
				System.out.println("s");
				y += 50;
			}
		}
		if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
			if (!isColN) {
				System.out.println("n");
				y -= 50;
			}
		}
		if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
			if (!isColE) {
				System.out.println("e");
				x += 50;
			}
		}
		if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
			if (!isColW) {
				System.out.println("w");
				x -= 50;
			}
		}
		
		isColN = isColS = isColE = isColW = false;
		tx.setToTranslation(x, y);
	}
	
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move(); //ask dino to update its location variables
		g2.drawImage(img, tx, null);
		
		g.setFont(small);
		g.setColor(Color.black);
		g.drawString(getDeaths(), 0, 40);
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
	
	public void setVx(double vx) {
		this.vx = vx;
	}
	
	public double getVx() {
		return vx;
	}
	
	public String getDeaths() {
		return "Deaths:" + deaths;
	}
	
	/* Helper function for collision detection later */
	public Rectangle getRect() {
		Rectangle rect = new Rectangle(x,y,width,height);
		return rect;
	}
	
	public Rectangle northRect() {
		Rectangle rect = new Rectangle(x,y - height,width,height);
		return rect;
	}
	
	public Rectangle southRect() {
		Rectangle rect = new Rectangle(x,y + height,width,height);
		return rect;
	}
	
	public Rectangle eastRect() {
		Rectangle rect = new Rectangle(x + width,y,width,height);
		return rect;
	}
	
	public Rectangle westRect() {
		Rectangle rect = new Rectangle(x - width,y,width,height);
		return rect;
	}
}
