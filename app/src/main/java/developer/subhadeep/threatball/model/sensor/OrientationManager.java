package developer.subhadeep.threatball.model.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.CallSuper;



/**
 *
 * <h1> OrientationManager </h1>
 *
 * <p> This class implements some of the functions of the {@link OrientationHandler}. </>
 * <p> It process the data from sensors and calculates the current orientation. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public abstract class OrientationManager implements SensorEventListener, OrientationHandler {


    /**
     * Stores a sensor object of type magnetometer.
     */
    private Sensor magnetometer;


    /**
     * Stores a sensor object of type accelerometer.
     */
    private Sensor accelerometer;


    /**
     * Stores the calculated orientation information.
     */
    private float[] orientation;


    /**
     * Stores data coming from the magnetometer.
     */
    private float[] magnetometerData;


    /**
     * Stores data coming from the accelerometer.
     */
    private float[] accelerometerData;


    /**
     * It is used to gain access to the required sensors.
     */
    private SensorManager sensorManager;



    /**
     * The constructor used to create objects of this class.
     *
     * @param sensorManager is an object of the {@link SensorManager} class.
     */
    public OrientationManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }


    /**
     * Method is used to register for updates from the sensors.
     */
    @CallSuper
    @Override
    public void register () {
        orientation = new float[3];
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
    }


    /**
     * Method used to stop receiving updates from the sensors.
     */
    @CallSuper
    @Override
    public void unregister () {
        sensorManager.unregisterListener(this);
    }


    /**
     * This method is automatically called when registered for sensor updates.
     *
     * @param sensorEvent contains all necessary information of the sensor update.
     */
    @CallSuper
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            accelerometerData = sensorEvent.values;
        else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            magnetometerData = sensorEvent.values;

        if (accelerometerData != null && magnetometerData != null) {
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, accelerometerData, magnetometerData);
            if (success) {
                SensorManager.getOrientation(R, orientation);
                onOrientationChanged(orientation);
            }
        }
    }


    /**
     * This method is automatically called when sensor accuracy changes.
     *
     * @param sensor contains information on the sensor whose accuracy has changed.
     * @param accuracy is the new accuracy of the sensor.
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}


    /**
     * This method is automatically called by the {@link OrientationManager#onOrientationChanged(float[])}
     * method after it completes the orientation estimation.
     *
     * @param orientationData contains the updated orientation information.
     */
    @Override
    public abstract void onOrientationChanged (float orientationData[]);


}
