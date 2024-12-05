package groupproject;

import java.awt.Graphics;
import java.util.Random;


public class Particle {
	  protected int x;
	  protected int y;
	  protected int steps;
	  protected final Random rng = new Random();
	 
	  public Particle(int initialX, int initialY) { 
	    x = initialX;
	    y = initialY;
	    steps = 0;
	  }

		public int getX() {
			return this.x;
		}

		public int getSteps() {
			return this.steps;
		}

		public int getY() {
			return this.y;
		}

	  public synchronized void move() {
		  if(x<410 && y<410 && x>-10 && y>-10) {
			  x += rng.nextInt(10) - 5;
			  y += rng.nextInt(20) - 10;
			  steps++;//so out of boundary particles will not move anymore
		  }
	  }

	  public void draw(Graphics g) {
	    int lx, ly;
	    synchronized (this) { lx = x; ly = y; }
	    g.drawRect(lx, ly, 10, 10);
	  }
}