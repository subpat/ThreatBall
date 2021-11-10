package developer.subhadeep.threatball.model;

import org.jetbrains.annotations.TestOnly;

import javax.inject.Inject;
import javax.inject.Singleton;

import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import developer.subhadeep.threatball.model.position.Positions;
import io.reactivex.rxjava3.core.Single;


/**
 *
 * <h1> ArtifactsInitializationRepo </h1>
 *
 * <p> This class makes the generated initial positions of game artifacts available to the view models. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

@Singleton
public class ArtifactsInitializationRepo implements Initialization {


    /**
     * This variable contains the initial positions.
     */
    Positions initialPositions;


    /**
     * This constructor is used to create objects of the class for testing purposes.
     *
     * @param initialPositions is an object of the class the implements {@link Positions}.
     */
    @TestOnly
    public ArtifactsInitializationRepo (Positions initialPositions) {
        this.initialPositions = initialPositions;
    }


    /**
     * This constructor is used by Dagger to automatically create an instance of the class.
     *
     * @param initialPositions is an object of the {@link ArtifactsPosition} class.
     */
    @Inject
    public ArtifactsInitializationRepo (ArtifactsPosition initialPositions) {
        this.initialPositions = initialPositions;
    }


    /**
     * This method allows access to the generated positions for artifacts.
     *
     * @return @return a {@link Single} containing generated {@link Positions}.
     */
    @Override
    public Single<Positions> get () {
        return Single.just(initialPositions)
                .map(initialPositions -> {
                    initialPositions.generate();
                    return initialPositions;
                });
    }

}
