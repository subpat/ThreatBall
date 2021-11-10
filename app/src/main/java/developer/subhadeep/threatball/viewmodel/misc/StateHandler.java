package developer.subhadeep.threatball.viewmodel.misc;

import developer.subhadeep.threatball.STATE;


/**
 *
 * <h1> StateHandler </h1>
 *
 * <p> This interface lists the must have methods in {@link StateManager} class.
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface StateHandler {


    /**
     * This method signature may be used to set the current state of the game.
     *
     * @param state is the desired state.
     */
    void setState (STATE state);


    /**
     * This method signature may be used to retrieve the current state of the game.
     *
     * @return the current state of the game.
     */
    STATE getState ();

}
