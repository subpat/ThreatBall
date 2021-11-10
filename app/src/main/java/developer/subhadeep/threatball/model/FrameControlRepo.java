package developer.subhadeep.threatball.model;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import developer.subhadeep.threatball.AppConstants;
import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import io.reactivex.rxjava3.core.Observable;


/**
 *
 * <h1> FrameControlRepo </h1>
 *
 * <p> This class works like a game loop informing the application when to generate a new frame. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

@Singleton
public class FrameControlRepo implements FrameControl {

    /**
     * This constructor is used by Dagger to automatically create an instance of the class.
     */
    @Inject
    public FrameControlRepo () {}



    /**
     * This method allows access to an infinite observable.
     *
     * @return an {@link Observable} that emits a long value at every 1/FPS time interval.
     */
    @Override
    public Observable<Long> get () {
        return Observable.interval(1000/ AppConstants.MAX_FPS, TimeUnit.MILLISECONDS);
    }


}
