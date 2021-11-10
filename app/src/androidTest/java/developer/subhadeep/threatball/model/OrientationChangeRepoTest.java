package developer.subhadeep.threatball.model;

import android.content.Context;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.Build;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class OrientationChangeRepoTest {

    private OrientationChangeRepo orientationChangeRepo;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        orientationChangeRepo = new OrientationChangeRepo(
                (SensorManager) context.getSystemService(context.SENSOR_SERVICE),
                context.getResources().getDisplayMetrics());
        orientationChangeRepo.register();
    }

    @After
    public void tearDown() {
        orientationChangeRepo.unregister();
        orientationChangeRepo = null;
    }

    @Test
    public void testIfDataIsProperlySent () {
        orientationChangeRepo.setCurrentOrientation(new float[] {0.5f, 2f, 9.45f}); // Set fake orientation data
        Point result = orientationChangeRepo.requestChangeInPosition().blockingGet();
        assertTrue(result.x != 0);
        assertTrue(result.y != 0);
    }

}