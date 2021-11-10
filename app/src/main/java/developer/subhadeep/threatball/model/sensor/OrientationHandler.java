package developer.subhadeep.threatball.model.sensor;

import android.graphics.Point;

import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import io.reactivex.rxjava3.core.Single;


/**
 *
 * <h1> OrientationHandler </h1>
 *
 * <p> This interface lists the must implemented by the {@link OrientationManager} or
 * {@link developer.subhadeep.threatball.model.OrientationChangeRepo} class that is used to
 * listen to changes in the orientation of the mobile phone. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface OrientationHandler {

    /**
     * Method signature that should be used to register for updates from the sensors.
     */
    void register ();


    /**
     * Method signature that should be used to stop receiving updates from the sensors.
     */
    void unregister ();


    /**
     * This method signature allows users implementing it to received the processed sensor information.
     * Once data from various sensors is received and processed, this method is used to publish the results.
     *
     * @param orientationData contains the updated orientation information.
     */
    void onOrientationChanged (float orientationData[]);


    /**
     * This method signature allows users to query whether they have already registered for sensor updates.
     *
     * @return a boolean value.
     */
    boolean isRegistered ();


    /**
     * This method signature allows us to access the change in position of artifact due to change in the orientation.
     *
     * @return a {@link Single} {@link Point} that stores the change in position.
     */
    Single<Point> requestChangeInPosition ();

}
