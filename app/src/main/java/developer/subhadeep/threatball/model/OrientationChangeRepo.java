package developer.subhadeep.threatball.model;

import android.graphics.Point;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;

import org.jetbrains.annotations.TestOnly;

import javax.inject.Inject;

import developer.subhadeep.threatball.AppConstants;
import developer.subhadeep.threatball.model.sensor.OrientationManager;
import io.reactivex.rxjava3.core.Single;


/**
 *
 * <h1> OrientationChangeRepo </h1>
 *
 * <p> This class exposes the orientation updates to the view models. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */


public class OrientationChangeRepo extends OrientationManager {

    /**
     * This variable indicates whether the application has registered to sensor data updates.
     */
    private boolean registered;

    /**
     * This variable stores the current orientation information.
     */
    private float currentOrientation[];

    /**
     * This variable stores the information related to the mobile screen.
     */
    private DisplayMetrics displayMetrics;


    /**
     * The constructor to instantiate this class.
     *
     * @param sensorManager is used to gain access to the sensors.
     * @param displayMetrics contains information about the display.
     */
    @Inject
    public OrientationChangeRepo(SensorManager sensorManager, DisplayMetrics displayMetrics) {
        super(sensorManager);
        registered = false;
        this.displayMetrics = displayMetrics;
    }


    /**
     * This method is used to register to sensor data updates.
     */
    @Override
    public void register () {
        super.register();
        registered = true;
    }


    /**
     * This method is used to unregister from sensor updates.
     */
    @Override
    public void unregister () {
        super.unregister();
        registered = false;
    }


    /**
     * This method is automatically called new orientation information is available.
     *
     * @param orientationData contains the updated orientation information.
     */
    @Override
    public void onOrientationChanged (float orientationData[]) {
        currentOrientation = orientationData;
    }


    /**
     * This method is used to supply simulated orientation information useful in test cases where
     * the application will be running may be running on the emulator.
     *
     * @param orientationData contains supplied orientation information.
     */
    @TestOnly
    public void setCurrentOrientation (float orientationData[]) {
        onOrientationChanged(orientationData);
    }


    /**
     * This method may be used to query if we are registered for sensory updates.
     *
     * @return true if registered and false otherwise.
     */
    @Override
    public boolean isRegistered () {
        return registered;
    }


    /**
     * This method exposes the change in position of mobile artifacts
     * to the view model layer. It should be called everytime a frame is displayed.
     *
     * @return a {@link Single} {@link Point} that indicates the change in position.
     */
    @Override
    public Single<Point> requestChangeInPosition () {
        Point result = new Point(0, 0);
        if (currentOrientation != null) {
            float pitch = currentOrientation[1];
            float roll = currentOrientation[2];
            float xspeed = 3 * roll * (displayMetrics.widthPixels + displayMetrics.heightPixels) / displayMetrics.widthPixels;
            float yspeed = 3 * pitch * (displayMetrics.widthPixels + displayMetrics.heightPixels) / displayMetrics.widthPixels;
            result.x = Math.round(xspeed * 1000/AppConstants.MAX_FPS);
            result.y = Math.round(yspeed * 1000/AppConstants.MAX_FPS);
        }
        return Single.just(result);
    }


}
