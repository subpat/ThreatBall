package developer.subhadeep.threatball.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.datastore.rxjava3.RxDataStore;
import developer.subhadeep.threatball.model.datastore.Data;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;


/**
 *
 * <h1> LocalDataStoreRepo </h1>
 *
 * <p> This class is responsible for communication with the {@link androidx.lifecycle.ViewModel}s
 * thus exposing the functionalities offered by the data layer. </>
 *
 * <p> Only one instance of this class exists during the lifetime of the application,
 * which is created by Dagger Dependency Injection framework. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

@Singleton
public class LocalDataStoreRepo implements LocalDataStore {

    /**
     * This variable stores a reference to an object of {@link RxDataStore} that helps store data.
     */
    private RxDataStore<Data> rxDataStore;


    /**
     * This constructor is used to create an object of this class.
     *
     * @param rxDataStore is supplied by Dagger Dependency Injection framework.
     */
    @Inject
    public LocalDataStoreRepo (RxDataStore<Data> rxDataStore) {
        this.rxDataStore = rxDataStore;
    }

    /**
     * This method is used to store an object of a class that implements the {@link Data} interface.
     *
     * @param data contains all important information related to the game.
     * @return an {@link io.reactivex.rxjava3.core.Observable} that can be monitored for errors.
     */
    @Override
    public Single<Data> store (Data data) {
        return rxDataStore.updateDataAsync(newData -> Single.just(data));
    }


    /**
     * This method helps retrieve data related to the game.
     *
     * @return a {@link Flowable} of the data from DataStore containing values or error information.
     */
    @Override
    public Flowable<Data> retrieve () {
        return rxDataStore.data();
    }


}
