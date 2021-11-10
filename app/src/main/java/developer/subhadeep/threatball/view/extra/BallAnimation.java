package developer.subhadeep.threatball.view.extra;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import androidx.annotation.NonNull;


/**
 *
 * <h1> BallAnimation </h1>
 *
 * <p> This class helps us create an animation for the ball. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */


public class BallAnimation implements GameAnimation{

    /**
     * Stores the radius of the ball.
     */
    private int ballRadius;


    /**
     * Stores the position of the ball.
     */
    private Point position;


    /**
     * The current frame being displayed.
     */
    private int frameIndex;


    /**
     * The frames that forms part of this animation.
     */
    private Bitmap frames[];


    /**
     * Depicts if this animation is being played currently.
     */
    private boolean playing;


    /**
     * The time to be spend displaying the same frame.
     */
    private float frameTime;


    /**
     * Time at which the last frame of this animation was displayed.
     */
    private long timeSinceLastFrame;


    /**
     * Constructor for creating objects of this class.
     *
     * @param frames are the images making up the animation.
     * @param animTimeInSec depicts how long the animation is to be played.
     */
    public BallAnimation(@NonNull Bitmap frames[], float animTimeInSec) {
        playing = false;
        this.position = null;
        this.frames = frames;
        ballRadius = frames[0].getWidth() / 2; // all the frames must be of the same size
        animTimeInSec = animTimeInSec > 0 ? animTimeInSec: 1;
        frameTime = animTimeInSec * 1000 / frames.length;
    }


    /**
     * This method allows us to find out if the current animation is being played.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isPlaying() {
        return playing;
    }


    /**
     * This method can be used to start the animation.
     */
    @Override
    public void start() {
        if (!isPlaying() && position != null) {
            playing = true;
            frameIndex = 0;
            timeSinceLastFrame = System.currentTimeMillis();
        }
    }


    /**
     * This method signature can be used to stop the animation.
     */
    @Override
    public void stop() {
        playing = false;
    }


    /**
     * This method can be used to update the position of the animation on screen.
     *
     * @param position is the desired position of the animation.
     */
    @Override
    public void update (Point position) {
        this.position = position;
    }


    /**
     * This method signature allows displaying the animation.
     *
     * @param canvas is used to draw the animation on screen.
     */
    @Override
    public void draw(Canvas canvas) {
        if (isPlaying() && position != null) {
            canvas.drawBitmap(frames[frameIndex], position.x - ballRadius, position.y - ballRadius, null);
            updateFrame();
        }
    }


    /**
     * This method is used to select the next frame in the animation sequence.
     */
    private void updateFrame() {
        if (isPlaying()) {
            if (System.currentTimeMillis() - timeSinceLastFrame > frameTime) {
                frameIndex = (++frameIndex == frames.length) ? frames.length-1 : frameIndex;
                timeSinceLastFrame = System.currentTimeMillis();
            }
        }
    }

}
