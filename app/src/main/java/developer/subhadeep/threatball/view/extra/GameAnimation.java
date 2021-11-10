package developer.subhadeep.threatball.view.extra;

import android.graphics.Canvas;
import android.graphics.Point;

import developer.subhadeep.threatball.model.LoadingRepo;


/**
 *
 * <h1> GameAnimation </h1>
 *
 * <p> This interface lists the must have methods in our {@link BallAnimation} class
 * which can be used to create different animations for the ball game artifact. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface GameAnimation {

    /**
     * This method signature allows us to find out if the current animation is being played.
     *
     * @return a boolean value.
     */
    boolean isPlaying ();


    /**
     * This method signature can be used to start the animation.
     */
    void start ();

    /**
     * This method signature can be used to stop the animation.
     */
    void stop ();


    /**
     * This method signature can be used to update the position of the animation on screen.
     *
     * @param position is the desired position of the animation.
     */
    void update (Point position);


    /**
     * This method signature allows displaying the animation.
     *
     * @param canvas is used to draw the animation on screen.
     */
    void draw (Canvas canvas);

}
