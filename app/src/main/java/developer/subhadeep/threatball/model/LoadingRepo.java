package developer.subhadeep.threatball.model;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import developer.subhadeep.threatball.AppConstants;
import io.reactivex.rxjava3.core.Completable;


/**
 *
 * <h1> LoadingRepo </h1>
 *
 * <p> This class helps us display a loading screen between the main menu and the gaming screen. </>
 * <p> Sometimes loading the actual game screen takes time and therefore the screen get black until it is is ready.
 * Thus a loading screen makes sense.</>
 *
 * <p> This class helps to show the loading screen for at least 500 ms, after which it advices the
 * application to change screen. Note that a minimum amount of time has been fixed since
 * the loading screen may not be displayed at all if the game screen starts up too quickly. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

@Singleton
public class LoadingRepo implements Loading {

    /**
     * This constructor is used to create an instance of the class.
     */
    @Inject
    public LoadingRepo () {}


    /**
     * This method allows the game to display a loading screen at least for a fixed amount of time.
     *
     * @return an {@link Completable} that terminates after {@link AppConstants#MIN_LOADING_DELAY_MILLIS} amount of time.
     */
    @Override
    public Completable get() {
        return Completable.complete().delay(AppConstants.MIN_LOADING_DELAY_MILLIS, TimeUnit.MILLISECONDS);
    }

}
