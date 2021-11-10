package developer.subhadeep.threatball.view.extra;

import android.util.DisplayMetrics;


/**
 *
 * <h1> MovementRestricter </h1>
 *
 * <p> This class is used to restrict the movement of the ball within the screen. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public class MovementRestricter {


    /**
     * This method corrects the x coordinate values.
     *
     * @param displayMetrics contains the screen size related information.
     * @param x is the desired coordinate value.
     * @param diameter is the diameter of the ball.
     * @return an integer which is the corrected value of x coordinate.
     */
    public static int correctX (DisplayMetrics displayMetrics, int x, int diameter) {
        if (x - diameter/2 < 0) return diameter/2;
        else if (x + diameter/2 > displayMetrics.widthPixels) return displayMetrics.widthPixels - diameter/2;
        else return x;
    }


    /**
     * This method corrects the y coordinate values.
     *
     * @param displayMetrics contains the screen size related information.
     * @param y is the desired coordinate value.
     * @param diameter is the diameter of the ball.
     * @return an integer which is the corrected value of y coordinate.
     */
    public static int correctY (DisplayMetrics displayMetrics, int y, int diameter) {
        if (y - diameter/2 < 0) return diameter/2;
        else if (y + diameter/2 > displayMetrics.heightPixels) return displayMetrics.heightPixels - diameter/2;
        else return y;
    }


}
