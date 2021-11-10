package developer.subhadeep.threatball.model.position;

import android.graphics.Point;



/**
 *
 * <h1> Generator </h1>
 *
 * <p> This interface lists the must have methods in our {@link PositionGenerator} class. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface Generator {

    /**
     * This method signature allows to access the number of virtual rows into which the screen has been divided.
     *
     * @return A non negative integer value.
     */
    int getRowMax ();


    /**
     * This method signature allows to access the number of virtual columns into which the screen has been divided.
     *
     * @return A non negative integer value.
     */
    int getColMax ();


    /**
     * This method signature allows to generate a random positive integer between o and bounds.
     *
     * @param bound is a positive integer.
     * @return an integer between 0 (inclusive) and bounds (exclusive).
     */
    int getRandomInt (int bound);


    /**
     * This method signature allows the generation of x-coordinate for displaying game artifacts.
     *
     * @return A non negative integer value.
     */
    int generateRandomX (int col, int diameter);


    /**
     * This method signature allows the generation of y-coordinate for displaying game artifacts.
     *
     * @return A non negative integer value.
     */
    int generateRandomY (int row, int diameter);


    /**
     * This method signature allows the generation of (x,y) coordinate for displaying game artifacts.
     *
     * @return A {@link Point} object.
     */
    Point getRandomPoint (int row, int col, int diameter);


}
