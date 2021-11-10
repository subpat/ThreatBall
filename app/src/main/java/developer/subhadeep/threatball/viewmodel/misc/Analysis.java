package developer.subhadeep.threatball.viewmodel.misc;

import android.graphics.Point;

import java.util.ArrayList;

import developer.subhadeep.threatball.AppConstants;


/**
 *
 * <h1> Analysis </h1>
 *
 * <p> This class helps identify if the ball falls down a hole. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */


public class Analysis {


    /**
     * This method is used to calculate the distance between the ball and the hole.
     *
     * @param ballPosition is the center of the ball.
     * @param holePosition is the center of the hole.
     * @return a double value indicating the distance between the two centers.
     */
    private static double distanceBetweenCenters (Point ballPosition, Point holePosition) {
        return Math.sqrt( Math.pow(ballPosition.x - holePosition.x, 2) + Math.pow(ballPosition.y - holePosition.y, 2));
    }


    /**
     * Method to evaluate if the ball should fall inside the hole.
     *
     * @param ballPosition is the center of the ball.
     * @param holePosition is the center of the hole.
     * @return true if the ball should fall, otherwise returns false.
     */
    private static boolean isBallInHole (Point ballPosition, Point holePosition) {
        if (ballPosition != null & holePosition != null) {
            //Remember only the surface just below the center of the ball touches the ground.
            //Just because the perimeter of the ball and hole coincide does not mean it falls in.
            double distanceBallToHole = distanceBetweenCenters(ballPosition, holePosition);
            if (distanceBallToHole < (AppConstants.BALL_SIZE/2 + AppConstants.HOLE_SIZE/2) / 2.5)
                return true;
        }
        return false;
    }


    /**
     * Evaluates if the game is won by the ball falling in the destination hole.
     *
     * @param ballPosition is the center of the ball.
     * @param winHolePosition is the center of the destination hole.
     * @return true if the ball should fall, otherwise returns false.
     */
    public static boolean isGameWon (Point ballPosition, Point winHolePosition) {
        return isBallInHole(ballPosition, winHolePosition);
    }


    /**
     * Evaluates if the game is lost by the ball falling in one of the obstacle holes.
     *
     * @param ballPosition is the center of the ball.
     * @param loseHolesPosition is list of centers of the obstacle holes.
     * @return true if the ball should fall, otherwise returns false.
     */
    public static boolean isGameLost (Point ballPosition, ArrayList<Point> loseHolesPosition) {
        if (loseHolesPosition != null && !loseHolesPosition.isEmpty()) {
            for (Point loseHolePosition : loseHolesPosition) {
                if (isBallInHole(ballPosition, loseHolePosition)) {
                    return true;
                }
            }
        }
        return false;
    }


}
