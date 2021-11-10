package developer.subhadeep.threatball.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FrameControlRepoTest {

    private FrameControlRepo frameControlRepo;

    @Before
    public void setUp() {
        frameControlRepo = new FrameControlRepo ();
    }

    @After
    public void tearDown() {
        frameControlRepo = null;
    }

    @Test
    public void checkRuntime () {
        frameControlRepo.get()
                .test()
                .awaitDone(5000, TimeUnit.MILLISECONDS)
                .assertNotComplete();
    }

    @Test
    public void checkForErrors () {
        frameControlRepo.get()
                .test()
                .assertNoErrors();
    }

}