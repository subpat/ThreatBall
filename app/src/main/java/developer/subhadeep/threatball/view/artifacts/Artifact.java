package developer.subhadeep.threatball.view.artifacts;

import android.graphics.Canvas;
import android.graphics.Point;


/**
 *
 * <h1> Artifact </h1>
 *
 * <p> This interface lists the must have methods in our {@link Ball} and {@link Hole} classes,
 * which are the two types of artifacts in our game. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface Artifact {


    /**
     * This method signature allows displaying the interpretation of the artifact.
     *
     * @param canvas is used to draw the image of the artifact.
     */
    void draw (Canvas canvas);


    /**
     * This method signature allows updating the position and interpretation of the artifact.
     * It is only useful in class of a {@link Ball} object.
     *
     * @param position is the change is location of the artifact.
     * @param inPlay is a boolean value depending on which a different animation is displayed.
     */
    void update (Point position, boolean inPlay);

}
