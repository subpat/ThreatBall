package developer.subhadeep.threatball.model;

import developer.subhadeep.threatball.model.position.Positions;
import io.reactivex.rxjava3.core.Single;


/**
 *
 * <h1> Initialization </h1>
 *
 * <p> This interface lists the must have methods in our {@link ArtifactsInitializationRepo} class
 * that makes the generated initial positions of game artifacts available to the view models. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */


public interface Initialization {


    /**
     * This method signature allows access to the generated positions for artifacts.
     *
     * @return a {@link Single} containing {@link Positions}.
     */
    Single<Positions> get ();


}
