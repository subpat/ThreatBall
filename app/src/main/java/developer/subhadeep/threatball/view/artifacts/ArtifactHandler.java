package developer.subhadeep.threatball.view.artifacts;

import android.graphics.Canvas;
import android.graphics.Point;

import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import developer.subhadeep.threatball.model.position.Positions;

/**
 *
 * <h1> ArtifactHandler </h1>
 *
 * <p> This interface lists the must have methods in our {@link ArtifactsManager} class. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface ArtifactHandler {


    /**
     * This method signature allows clearing all artifacts related information,
     * especially useful when the scene is being destroyed.
     */
    void clear ();


    /**
     * This method signature allows the generation and initialization of the game artifacts for the current scene.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    void init (Positions initialPositions);


    /**
     * This method signature allows updating the position and interpretation of the artifact.
     * It is only useful in class of a {@link Ball} object.
     *
     * @param position the change in position of the artifact. It is only used by the {@link Ball} object.
     * @param inPlay indicates which Animation is to be displayed, useful especially for a {@link Ball}.
     * @param message is a {@link String} to be displayed on screen.
     */
    void update (Point position, boolean inPlay, String message);


    /**
     * This method signature allows displaying the interpretation of the artifacts.
     *
     * @param canvas is used to draw the image of the artifacts.
     */
    void draw (Canvas canvas);

}
