package developer.subhadeep.threatball.view.artifacts;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 *
 * <h1> Hole </h1>
 *
 * <p> This class represents the destination or obstacle hole artifact. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public class Hole extends GameCircle {

    /**
     * Image to be displayed as this hole.
     */
    private Bitmap bitmap;


    /**
     * Constructor to create an object of this class.
     *
     * @param bitmap is the desired representation of this hole.
     * @param center is the center of the hole.
     */
    public Hole(Bitmap bitmap, Point center) {
        super(center, bitmap.getWidth()/2);
        this.bitmap = bitmap;
    }


    /**
     * This method allows displaying the interpretation of this hole artifact.
     *
     * @param canvas is used to draw the image of the artifact.
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap (bitmap, getCenter().x - getRadius(), getCenter().y - getRadius(), null);
    }


    /**
     * This method allows updating the position and interpretation of this artifact.
     * This method is currently not in use by this class as the holes are fixed.
     *
     * @param position is the change is location of an artifact. This value is NOT used for this class.
     * @param inPlay is a boolean value denoting whether the artifact is active. This value is NOT used for this class.
     */
    @Override
    public void update(Point position, boolean inPlay) {}


}
