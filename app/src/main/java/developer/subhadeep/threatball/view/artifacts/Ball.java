package developer.subhadeep.threatball.view.artifacts;

import android.graphics.Canvas;
import android.graphics.Point;

import androidx.annotation.NonNull;
import developer.subhadeep.threatball.view.extra.GameAnimation;


/**
 *
 * <h1> Ball </h1>
 *
 * <p> This class represents the ball game artifact. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */


public class Ball extends GameCircle {

    /**
     * This variable helps select the animation to be played.
     */
    private boolean isInPlay;


    /**
     * This variable stores the animation to be displayed if the ball drops down a hole.
     */
    private GameAnimation ballFallsAnim;


    /**
     * This variable stores the animation to be displayed when the outcome of the game is not yet decided.
     */
    private GameAnimation ballDefaultAnim;


    /**
     * The constructor used to create an object of this class.
     *
     * @param center is the initial center of the ball.
     * @param radius is the radius of the ball.
     * @param ballDefaultAnim is the animation to be displayed while the outcome of the game is not decided.
     * @param ballFallsAnim is the animation to be displayed when the ball drops down a hole.
     */
    public Ball (Point center, int radius, @NonNull GameAnimation ballDefaultAnim, @NonNull GameAnimation ballFallsAnim) {
        super(center, radius);
        this.isInPlay = true;
        this.ballFallsAnim = ballFallsAnim;
        this.ballDefaultAnim = ballDefaultAnim;
    }


    /**
     * This method allows displaying the interpretation of this ball artifact.
     *
     * @param canvas is used to draw the image of the artifact.
     */
    @Override
    public void draw (Canvas canvas) {
        GameAnimation ballAnimation = isInPlay ? ballDefaultAnim : ballFallsAnim;
        ballAnimation.start();
        ballAnimation.draw(canvas);
    }


    /**
     * This method allows updating the position and interpretation of this ball artifact.
     *
     * @param position is the change is location of this ball artifact.
     * @param inPlay is a boolean value depending on which a different animation is displayed.
     */
    @Override
    public void update (Point position, boolean inPlay) {
        setCenter (position);
        this.isInPlay = inPlay;
        ballDefaultAnim.update(getCenter());
        ballFallsAnim.update(getCenter());
    }

}
