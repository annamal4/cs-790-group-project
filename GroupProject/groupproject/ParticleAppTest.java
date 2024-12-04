package groupproject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticleAppTest {

    private ParticleApp app;

    @BeforeEach
    void setUp() {
        app = new ParticleApp();
    }

    @AfterEach
    void tearDown() {
        if (app.threads != null) {
            app.stop(); // Ensure threads are stopped after each test
        }
    }

    @Test
    void testStartInitializesThreads() {
        app.start();

        assertNotNull(app.threads, "Threads should be initialized after start");
        assertEquals(10, app.threads.length, "Number of threads should match number of particles");

        for (Thread thread : app.threads) {
            assertNotNull(thread, "Thread should not be null");
            assertTrue(thread.isAlive(), "Thread should be alive after start");
        }
    }

    @Test
    void testStopTerminatesThreads() {
        app.start(); // Initialize threads

        app.stop(); // Terminate threads

        // Since threads are set to null, check that the threads array is cleared
        assertNull(app.threads, "Threads should be set to null after stop");
    }

    @Test
    void testMultipleStartsAndStops() {
        app.start();
        assertNotNull(app.threads, "Threads should be initialized after start");
        app.stop();
        assertNull(app.threads, "Threads should be set to null after stop");

        app.start();
        assertNotNull(app.threads, "Threads should be initialized again after restart");
        app.stop();
        assertNull(app.threads, "Threads should be set to null after stop again");
    }

   
}