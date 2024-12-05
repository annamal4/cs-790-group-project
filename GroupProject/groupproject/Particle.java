package groupproject;

import java.awt.Graphics;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class Particle {
	  protected int x;
	  protected int y;
	  protected int steps;
	  protected int id;
	  protected boolean complete = false;
	  protected final Random rng = new Random();
	 
	  public Particle(int initialX, int initialY, int particleId) { 
	    x = initialX;
	    y = initialY;
	    steps = 0;
	    id = particleId;
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
		  if(x<411 && y<411 && x>-11 && y>-11) {
			  x += rng.nextInt(10) - 5;
			  y += rng.nextInt(20) - 10;
			  steps++;//so out of boundary particles will not move anymore
		  }
		  else if(complete==false){
			  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.ns");
			  System.out.println(java.time.LocalDateTime.now().format(myFormatObj)+": Thread "+ id+" hit the boundary at ("+x+","+y+") on step number "+ steps+".");
			  complete = true;
		  }
	  }

	  public void draw(Graphics g) {
	    int lx, ly;
	    synchronized (this) { lx = x; ly = y; }
	    g.drawRect(lx, ly, 10, 10);
	  }
}