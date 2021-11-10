package developer.subhadeep.threatball;

/**
 *
 * <h1> STATE </h1>
 *
 * <p> This enumerator stores the different states of the game. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public enum STATE {

    /**
     * This is the default initial state when the game has not been started yet.
     */
    INIT,


    /**
     * This state occurs when the game is generating the locations of artifacts before user begin playing.
     */
    START,

    /**
     * This state indicates that the user is interacting with the game.
     */
    STARTED,


    /**
     * This state indicates the end of the game and the use is taken back to the main menu.
     */
    LOST,


    /**
     * This state indicates the favourable end of the game and the use is taken to the next level.
     */
    WON


}
