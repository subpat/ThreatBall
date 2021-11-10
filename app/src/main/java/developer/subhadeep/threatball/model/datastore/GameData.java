package developer.subhadeep.threatball.model.datastore;

import java.io.Serializable;


/**
 *
 * <h1> GameData </h1>
 *
 * <p> This class is used to store all game related information on disk. </>
 * <p> It implements the {@link Data} interface to provides us with all the required data functionalities. </>
 * <p> It also implements the {@link Serializable} interface which allows us to store this object directly to disk. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

public class GameData implements Data, Serializable {

    /**
     * This variable contains the highest achieved score among all users.
     */
    private int highScore;


    /**
     * A constructor of this class that creates an instance setting the high score to a default zero.
     */
    public GameData () {
        highScore = 0;
    }


    /**
     * A constructor of this class that creates an instance and sets the high score to a user defined value.
     */
    public GameData (int highscore) {
        this.highScore = highscore;
    }


    /**
     * This method is used to access the high score stored in the object of this class.
     *
     * @return a non negative integer value.
     */
    @Override
    public int getHighScore () {
        return highScore;
    }


}
