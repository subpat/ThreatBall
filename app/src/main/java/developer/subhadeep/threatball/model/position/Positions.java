package developer.subhadeep.threatball.model.position;


import java.util.ArrayList;
import android.graphics.Point;


/**
 *
 * <h1> Positions </h1>
 *
 * <p> This interface lists the must have methods in our {@link ArtifactsPosition} class that is used to
 * generate the initial positions of game artifacts and store them for future use. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface Positions {


    /**
     * This method signature allows to start the generation of positions for artifacts.
     *
     * @return A boolean value indicating if the process was successful.
     */
    boolean generate ();


    /**
     * This method signature allows us to access the position of the ball on screen.
     *
     * @return A {@link Point} object.
     */
    Point getBallPosition ();


    /**
     * This method signature allows us to access the position of the winning hole or destination.
     *
     * @return A {@link Point} object.
     */
    Point getWinHolePosition ();


    /**
     * This method signature allows us to access the positions of the obstacles.
     *
     * @return A {@link ArrayList} of {@link Point}.
     */
    ArrayList<Point> getObstaclesPosition();

}
