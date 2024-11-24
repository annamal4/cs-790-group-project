package groupproject;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


class ParticleCanvas extends JPanel {

	  private Particle[] particles = new Particle[0]; 

	  ParticleCanvas(int size) {
	    setSize(new Dimension(size, size));
	  }
	 
	  // Intended to be called by applet
	  synchronized void setParticles(Particle[] ps) {
	    if (ps == null) 
	      throw new IllegalArgumentException("Cannot set null");

	    particles = ps; 
	  }

	  protected synchronized Particle[] getParticles() { 
	    return particles; 
	  }
	 
	  public void paint(Graphics g) { // override Canvas.paint
	    Particle[] ps = getParticles();

	    for (int i = 0; i < ps.length; ++i) 
	      ps[i].draw(g);

	  }

}

