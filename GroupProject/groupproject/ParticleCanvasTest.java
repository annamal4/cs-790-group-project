package groupproject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ParticleCanvasTest {

	
    @Test
    void testSetAndGetParticles() {
        ParticleCanvas canvas = new ParticleCanvas(400);
        //test 1
        //test1
        // Create an array of particles
        Particle[] particles = new Particle[5];
        for (int i = 0; i < particles.length; i++) {
            particles[i] = new Particle(100, 100, i+1);
        }

        // Set particles and verify
        canvas.setParticles(particles);
        assertArrayEquals(particles, canvas.getParticles(), "Particles do not match after setting and getting");
    }

    @Test
    void testSetParticlesThrowsExceptionForNull() {
        ParticleCanvas canvas = new ParticleCanvas(400);

        // Setting null should throw the IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> canvas.setParticles(null), "Should throw exception for null input");
    }

    
}
