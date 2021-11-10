package developer.subhadeep.threatball.model;

import developer.subhadeep.threatball.model.datastore.Data;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;


/**
 *
 * <h1> LocalDataStore </h1>
 *
 * <p> This interface lists the method signatures that would define the behaviour of the {@link LocalDataStoreRepo} class. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

public interface LocalDataStore {

    /**
     * This method signature must allow storage of the object of a class that implements the {@link Data} interface.
     *
     * @param data contains all important information related to the game.
     * @return an {@link io.reactivex.rxjava3.core.Observable} that can be monitored for errors.
     */
    Single<Data> store (Data data);


    /**
     * This method signature should allow retrieval of the objects of a class that implements the {@link Data} interface.
     *
     * @return a {@link Flowable} of the data from DataStore containing values or error information.
     */
    Flowable<Data> retrieve ();


}
