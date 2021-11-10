package developer.subhadeep.threatball.viewmodel;

import androidx.lifecycle.LiveData;


/**
 *
 * <h1> GameViewModel </h1>
 *
 * <p> This interface lists the must have methods in our view model classes.
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */


public interface GameViewModel <T> {

    /**
     * This method signature can be is used to subscribe to data from the repositories.
     * The data from the repositories is acquired with the help of RxJava {@link io.reactivex.rxjava3.core.Observable}s.
     */
    void subscribeToRepo();


    /**
     * The data from the repositories is exposed to the activities with the help of {@link LiveData}.
     * This method signature can be used for this purpose.
     *
     * @return the relevant data encapsulated by {@link LiveData} which is lifecycle aware observable data holder class.
     * This encapsulated data is observed by the activities.
     */
    LiveData<T> getData ();

}
