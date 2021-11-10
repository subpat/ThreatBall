package developer.subhadeep.threatball.model.position;


/**
 *
 * <h1> ARTIFACT_TYPE </h1>
 *
 * <p> This enumerator stores the types of artifacts used in the game, used in the generation of initial positions. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public enum ARTIFACT_TYPE {

    /**
     * This type of artifact in unique in the game scene, has mobility.
     */
    BALL,

    /**
     * A destination type artifact is also unique, but its position is fixed.
     * The game is considered to be won when the ball reaches the destination.
     */
    DESTINATION,

    /**
     * Multiple artifacts can be of type obstacle.
     * The player loses the game if the ball reaches an obstacle.
     */
    OBSTACLE

}
