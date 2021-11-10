package developer.subhadeep.threatball.model;

import io.reactivex.rxjava3.core.Observable;


/**
 *
 * <h1> FrameControl </h1>
 *
 * <p> This interface lists the must have methods in our {@link FrameControlRepo} class
 * which that informs the application when to generate a new game frame. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface FrameControl {

    /**
     * This method signature allows access to an infinite observable.
     *
     * @return an {@link Observable} that emits a long value at every 1/FPS time interval.
     */
    Observable<Long> get ();

}
