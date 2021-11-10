package developer.subhadeep.threatball.model.position;

import android.graphics.Point;
import android.util.DisplayMetrics;

import java.util.Random;
import javax.inject.Inject;
import developer.subhadeep.threatball.AppConstants;


/**
 *
 * <h1> PositionGenerator </h1>
 *
 * <p> This class is used to generate the x and y coordinate of game artifacts. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public class PositionGenerator implements Generator{

    /**
     * This variable is used to store the number of virtual rows into which the screen has been divided.
     */
    private int rowMax;


    /**
     * This variable is used to store the number of virtual columns into which the screen has been divided.
     */
    private int colMax;


    /**
     * This variable is used to store the width of virtual cell in pixels.
     */
    private int cellWidth;


    /**
     * This variable is used to store the height of virtual cell in pixels.
     */
    private int cellHeight;


    /**
     * A variable that stores the an object of the {@link Random} class.
     */
    private Random random;


    /**
     * The constructor to create an instance of this class.
     *
     * @param displayMetrics is used to calculate the display bounds for the position generation process.
     */
    @Inject
    public PositionGenerator (DisplayMetrics displayMetrics) {
        random = new Random(System.currentTimeMillis());
        int wiggleRoom = random.nextInt(AppConstants.WIGGLE_MAX);
        this.rowMax = displayMetrics.heightPixels / (AppConstants.HOLE_SIZE + wiggleRoom);
        this.colMax = displayMetrics.widthPixels / (AppConstants.HOLE_SIZE + wiggleRoom);
        this.cellWidth = displayMetrics.widthPixels / colMax;
        this.cellHeight = displayMetrics.heightPixels / rowMax;
    }


    /**
     * This method may be used to generate a random positive integer between o and bounds.
     *
     * @param bound is a positive integer.
     * @return an integer between 0 (inclusive) and bounds (exclusive).
     */
    @Override
    public int getRandomInt (int bound) { return random.nextInt(bound); }


    /**
     * This method generates a random x coordinate value.
     *
     * @param col is the virtual column number in which the artifact is to be positioned.
     * @param diameter is the width or height of the square image to be displayed.
     * @return an integer denoting the x coordinate.
     */
    @Override
    public int generateRandomX (int col, int diameter) {
        return col * cellWidth + diameter/2 + getRandomInt(cellWidth - diameter);
    }


    /**
     * This method generates a random y coordinate value.
     *
     * @param row is the virtual row number in which the artifact is to be positioned.
     * @param diameter is the width or height of the square image to be displayed.
     * @return an integer denoting the y coordinate.
     */
    @Override
    public int generateRandomY (int row, int diameter) {
        return row * cellHeight + diameter/2 + getRandomInt(cellHeight - diameter);
    }


    /**
     * Generates a random (x,y) position for display purposes.
     *
     * @param row is the virtual row number in which the artifact is to be positioned.
     * @param col is the virtual column number in which the artifact is to be positioned.
     * @param diameter is the width or height of the square image to be displayed.
     * @return a {@link Point} object denoting the (x,y) coordinate.
     */
    @Override
    public Point getRandomPoint (int row, int col, int diameter) {
        return new Point(generateRandomX(col, diameter), generateRandomY(row, diameter));
    }


    /**
     * This method allows to access the number of virtual rows into which the screen has been divided.
     *
     * @return an integer denoting the maximum number of virtual rows.
     */
    @Override
    public int getRowMax () { return rowMax; }


    /**
     * This method allows to access the number of virtual columns into which the screen has been divided.
     *
     * @return an integer denoting the maximum number of virtual columns.
     */
    @Override
    public int getColMax () { return colMax; }


}
