package groupproject;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class ParticleApp extends JFrame {

	  protected Thread[] threads;
	  protected static LocalDateTime start;

	  protected final static ParticleCanvas canvas = new ParticleCanvas(400);
	  
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

	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.ns");
	    this.start = java.time.LocalDateTime.now();
	    System.out.println(start.format(myFormatObj)+": Particle App Started");
	    
	    
	    int n = 10; // just for demo

	    if (threads == null) {
	      Particle[] particles = new Particle[n];
	      for (int i = 0; i < n; ++i) 
	        particles[i] = new Particle(200, 200, i+1);//400 bad
	      canvas.setParticles(particles);

	      threads = new Thread[n];
	      for (int i = 0; i < n; ++i) {
	        threads[i] = makeThread(particles[i]);
	        threads[i].start();
	      }
  	      System.out.println(java.time.LocalDateTime.now().format(myFormatObj)+": Thread Creation Complete");
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
	            WindowAdapter wa = new WindowAdapter() {
	                public void windowClosing(WindowEvent e) {
	                	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.ns");
	                	LocalDateTime end = java.time.LocalDateTime.now();
	            	    System.out.println(end.format(myFormatObj)+": Particle App Ended");
	            	    var particles = canvas.getParticles();
	            	    int count = 1;
	            	    for(Particle p: particles) {
		            	    System.out.println("--Thread "+ count+" Ended on Step "+ p.getSteps()+"--");
		            	    count++;
	            	    }

	            	    System.out.println(java.time.LocalDateTime.now().format(myFormatObj)+": Particle App Elapsed Time = " + Duration.between(start, end).getSeconds()+ " seconds");
	            	    
	                }
	            };
	            app.addWindowListener(wa);
	            app.setVisible(true);
	            app.start();
	        });
	    }
	}