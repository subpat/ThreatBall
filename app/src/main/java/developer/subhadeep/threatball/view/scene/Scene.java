package developer.subhadeep.threatball.view.scene;

import android.graphics.Canvas;
import android.graphics.Point;

import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.view.artifacts.Ball;
import developer.subhadeep.threatball.view.game.GamePanelView;


/**
 *
 * <h1> Scene </h1>
 *
 * <p> This interface lists the must have methods in our {@link ThreatBallScene} class
 * which is the only scene in our game, although may be repeated multiple times. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface Scene {


    /**
     * This method signature may used to update the current {@link Scene}.
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
     * This method signature allows displaying the current scene on screen.
     *
     * @param canvas is used to draw the scene.
     */
    void draw (Canvas canvas);


    /**
     * This method signature may be used to clear all the artifacts that are a part of this scene.
     */
    void clearScene();

}
