package developer.subhadeep.threatball.view.scene;

import android.graphics.Canvas;
import android.graphics.Point;

import javax.inject.Inject;

import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.view.artifacts.Ball;


/**
 *
 * <h1> SceneManager </h1>
 *
 * <p> This class helps us to manipulate scenes being displayed. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public class SceneManager implements Choreographer {

    /**
     * Is the current scene being displayed.
     */
    private Scene scene;


    /**
     * The constructor for this class.
     */
    @Inject
    public SceneManager () {
        reset ();
    }


    /**
     * This method is used to reset the {@link SceneManager}.
     */
    private void reset () {
        scene = null;
    }


    /**
     * This method is used to start displaying the required scene.
     *
     * @param scene is the displayable continuous action composed of various game artifacts.
     */
    @Override
    public void startNewScene (Scene scene) {
        stopActiveScene();
        this.scene = scene;
    }


    /**
     * This method is used to stop displaying the current scene.
     */
    @Override
    public void stopActiveScene () {
        reset ();
    }


    /**
     * This method is used to receive new updates from the {@link developer.subhadeep.threatball.view.game.GamePanel}
     * and propagate it to the current {@link Scene}.
     *
     * @param position the change in position identified using the sensor data, required by the {@link Ball} artifact.
     * @param inPlay indicates which Animation is to be displayed, useful especially for a {@link Ball} object.
     * @param message is a {@link String} to be displayed on screen.
     */
    @Override
    public void update (Point position, boolean inPlay, String message) {
        if (scene != null) scene.update(position, inPlay, message);
    }


    /**
     * This method is used to receive the initial position of game artifacts.
     *
     * @param initialPositions contains the initial position of all artifacts.
     */
    @Override
    public void init (Positions initialPositions) {
        scene.init (initialPositions);
    }


    /**
     * This method can be used to query if a scene is currently active.
     *
     * @return a boolean value.
     */
    @Override
    public boolean hasActiveScene () {
        return scene != null;
    }


    /**
     * This method allows displaying the current scene on screen.
     *
     * @param canvas is used to draw the scene.
     */
    @Override
    public void draw (Canvas canvas) {
        if (scene != null)
            scene.draw(canvas);
    }


}
