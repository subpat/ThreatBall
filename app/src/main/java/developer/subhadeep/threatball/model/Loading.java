package developer.subhadeep.threatball.model;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;


/**
 *
 * <h1> Loading </h1>
 *
 * <p> This interface lists the must have methods in our {@link LoadingRepo} class
 * which that informs the application when to stop displaying the loading screen. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface Loading {

    /**
     * This method signature allows the game to display a loading screen at least for a fixed amount of time.
     *
     * @return an {@link Completable} that terminates after some time.
     */
    Completable get();

}
