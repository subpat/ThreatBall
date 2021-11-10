package developer.subhadeep.threatball.view.artifacts;

import android.graphics.Point;



/**
 *
 * <h1> GameCircle </h1>
 *
 * <p> This class implements some of the functions of {@link Circle}. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public abstract class GameCircle implements Circle, Artifact {


    /**
     * This variable stores the radius information.
     */
    private int radius;


    /**
     * This variable stores the x and y coordinate of the center.
     */
    private Point center;


    /**
     * Constructor for this abstract class.
     *
     * @param center is a {@link Point} object that is to be assigned as center.
     * @param radius is an integer that is to be assigned as the radius.
     */
    public GameCircle (Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }


    /**
     * This method allows the access to the radius of the circle.
     *
     * @return an integer representing the radius.
     */
    @Override
    public int getRadius () {
        return radius;
    }


    /**
     * This method allows setting the radius of this circle.
     *
     * @param radius is an integer that is to be assigned as the radius.
     */
    @Override
    public void setRadius (int radius) {
        this.radius = radius;
    }


    /**
     * This method allows accessing the center x and y coordinate of this circle.
     *
     * @return a {@link Point} object representing the center.
     */
    @Override
    public Point getCenter () {
        return center;
    }


    /**
     * This method signature allows setting the center x and y coordinate of this circle.
     *
     * @param center is a {@link Point} object that is to be assigned as center.
     */
    @Override
    public void setCenter (Point center) {
        this.center = center;
    }


}
