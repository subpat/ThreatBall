package developer.subhadeep.threatball.view.scene;

import android.graphics.Canvas;
import android.graphics.Point;

import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.view.artifacts.Ball;


/**
 *
 * <h1> Choreographer </h1>
 *
 * <p> This interface lists the must have methods in our {@link SceneManager} class
 * used to manage the scene being displayed. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface Choreographer {


    /**
     * This method signature may used to start displaying the required scene.
     *
     * @param scene is the displayable continuous action composed of various game artifacts.
     */
    void startNewScene (Scene scene);


    /**
     * This method signature may used to stop displaying the current scene.
     */
    void stopActiveScene ();


    /**
     * This method signature may used to receive new updates from the
     * {@link developer.subhadeep.threatball.view.game.GamePanel} and propagate it to the current {@link Scene}.
     *
     * @param position the change in position identified using the sensor data, required by the {@link Ball} artifact.
     * @param inPlay indicates which Animation is to be displayed, useful especially for a {@link Ball} object.
     * @param message is a {@link String} to be displayed on screen.
     */
    void update (Point position, boolean inPlay, String message);


    /**
     * This method signature can be used to receive the initial position of game artifacts.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    void init (Positions initialPositions);


    /**
     * This method signature can be used to query if a scene is currently active.
     *
     * @return a boolean value.
     */
    boolean hasActiveScene ();


    /**
     * This method signature allows displaying the current scene on screen.
     *
     * @param canvas is used to draw the scene.
     */
    void draw (Canvas canvas);


}
