package developer.subhadeep.threatball;


/**
 *
 * <h1> AppConstants </h1>
 *
 * <p> This class stores constant values used by other classes. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

public class AppConstants {

    /**
     * This variable is used to print debug messages.
     */
    public static final boolean DEBUG = true;


    /**
     * Stores the name of the datastore file where game related information is stored.
     */
    public static final String DATA_FILE_NAME = "ThreadBallData";


    /**
     * Stores the name of the datastore file where game related information is stored for testing.
     */
    public static final String TEST_FILE_NAME = "TestBallData";


    /**
     * Stores the value to be store/retrieved from the test datastore file when running tests.
     */
    public static final int SCORE_TEST_VAL = 60;


    /**
     * This variable stores the minimum time in milliseconds for the loading screen to be displayed.
     */
    public static final int MIN_LOADING_DELAY_MILLIS = 500;


    /**
     * This variable stores the minimum time in milliseconds for the loading screen to be displayed.
     */
    public static final int MAX_FPS = 30;


    /**
     * This variable stores the size of the ball (in pixels) to be displayed.
     */
    public static final int BALL_SIZE = 150;


    /**
     * This variable stores the size of the holes (in pixels) to be displayed, including the destination and obstacles.
     */
    public static final int HOLE_SIZE = 250;


    /**
     * This variable stores the maximum room (in pixels) between two displayed obstacles.
     */
    public static final int WIGGLE_MAX = 25;


    /**
     * This variable stores the size (in scalable pixels) of text messages that may appear on screen.
     */
    public static final int TEXT_SIZE = 40;

}


