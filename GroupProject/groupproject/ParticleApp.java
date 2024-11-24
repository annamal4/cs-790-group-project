package groupproject;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class ParticleApp extends JFrame {

	  protected Thread[] threads;

	  protected final ParticleCanvas canvas = new ParticleCanvas(400);
	  
	  public ParticleApp() {
	        setTitle("Particle Simulation");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        add(canvas, BorderLayout.CENTER);
	        setSize(400, 400);
	    }

	  protected Thread makeThread(final Particle p) {
	    Runnable runloop = new Runnable() {
	      public void run() {
	        try {
	          for(;;) {
	            p.move();
	            canvas.repaint();
	            Thread.sleep(100);
	          }
	        }
	        catch (InterruptedException e) { return; }
	      }
	    };
	    return new Thread(runloop);
	  }

	  public synchronized void start() {
	    int n = 10; // just for demo

	    if (threads == null) {
	      Particle[] particles = new Particle[n];
	      for (int i = 0; i < n; ++i) 
	        particles[i] = new Particle(200, 200);
	      canvas.setParticles(particles);

	      threads = new Thread[n];
	      for (int i = 0; i < n; ++i) {
	        threads[i] = makeThread(particles[i]);
	        threads[i].start();
	      }
	    }
	  }

	  public synchronized void stop() {
	    if (threads != null) {
	      for (int i = 0; i < threads.length; ++i)
	        threads[i].interrupt();
	      threads = null;
	    }
	  }
	  
	  public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            ParticleApp app = new ParticleApp();
	            app.setVisible(true);
	            app.start();
	        });
	    }
	}