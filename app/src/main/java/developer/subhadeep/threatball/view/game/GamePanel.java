package developer.subhadeep.threatball.view.game;

import android.graphics.Point;

import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.view.artifacts.Ball;
import developer.subhadeep.threatball.view.extra.BallAnimation;
import developer.subhadeep.threatball.view.scene.Scene;


/**
 *
 * <h1> GamePanel </h1>
 *
 * <p> This interface lists the must have methods in our {@link GamePanelView} class
 * which is like the game center where different scenes of the game may be displayed. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface GamePanel {

    /**
     * This method signature may be used to manually draw on canvas as soon as it is time to display the next frame.
     */
    void requestDraw ();


    /**
     * This method signature may used to update the current {@link Scene} handled by the {@link developer.subhadeep.threatball.view.scene.Choreographer}.
     *
     * @param position the change in position identified using the sensor data.
     * @param inPlay indicates which Animation is to be displayed, useful especially for a {@link Ball} object.
     * @param message is a {@link String} to be displayed on screen.
     */
    void update (Point position, boolean inPlay, String message);


    /**
     * This method signature can be used to propagate the initial artifacts' position
     * to the {@link developer.subhadeep.threatball.view.scene.Choreographer}.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    void init (Positions initialPositions);


    /**
     * This method signature can be used to set a new active scene.
     *
     * @param scene is the desired game scene to be displayed.
     */
    void addNewSceneToManager (Scene scene);


    /**
     * This method signature can be used to remove an active scene.
     */
    void removeActiveSceneFromManager ();


    /**
     * This method signature can be used to query whether there is a current active
     * scene being displayed.
     *
     * @return a boolean value.
     */
    boolean hasActiveScene ();

}
