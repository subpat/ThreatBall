package developer.subhadeep.threatball.view.artifacts;

import android.graphics.Point;


/**
 *
 * <h1> Circle </h1>
 *
 * <p> This interface lists the must have methods in our {@link Ball} and {@link Hole} classes,
 * since these artifacts are circular in nature. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface Circle {

    /**
     * This method signature allows the access to the radius of the circle.
     *
     * @return an integer representing the radius.
     */
    int getRadius ();


    /**
     * This method signature allows setting the radius of this circle.
     *
     * @param radius is an integer that is to be assigned as the radius.
     */
    void setRadius (int radius);


    /**
     * This method signature allows accessing the center x and y coordinate of this circle.
     *
     * @return a {@link Point} object representing the center.
     */
    Point getCenter ();


    /**
     * This method signature allows setting the center x and y coordinate of this circle.
     *
     * @param center is a {@link Point} object that is to be assigned as center.
     */
    void setCenter (Point center);

}
