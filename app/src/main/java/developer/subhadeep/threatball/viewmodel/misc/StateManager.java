package developer.subhadeep.threatball.viewmodel.misc;

import javax.inject.Inject;
import javax.inject.Singleton;

import developer.subhadeep.threatball.STATE;


/**
 *
 * <h1> StateManager </h1>
 *
 * <p> This class allows game state management. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

@Singleton
public class StateManager implements StateHandler {


    /**
     * Stores the current state of the game.
     */
    private STATE state;


    /**
     * The constructor of the class.
     */
    @Inject
    public StateManager () {
        state = STATE.INIT;
    }

    /**
     * This method is used to set the current state of the game.
     *
     * @param state is the desired game state.
     */
    @Override
    public void setState (STATE state) {
        this.state = state;
    }


    /**
     * This method is used to retrieve the current state of the game.
     *
     * @return the current state of the game.
     */
    @Override
    public STATE getState () { return state; }

}
