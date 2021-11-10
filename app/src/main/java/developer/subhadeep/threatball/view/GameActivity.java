package developer.subhadeep.threatball.view;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import developer.subhadeep.threatball.viewmodel.GameViewModel;


/**
 *
 * <h1> GameActivity </h1>
 *
 * <p> This interface lists the must have methods in our Activity classes.
 * Note that an Activity is an individual screen that makes up an application.</>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

public interface GameActivity <T>  {


    /**
     * This method signature may be used to get the relevant view model
     * which allows communication with data layer.
     *
     * @return a {@link GameViewModel}
     */
    GameViewModel getViewModel ();


    /**
     * This method signature can be used to start observing the data holder class: {@link androidx.lifecycle.LiveData}.
     *
     * @param viewModel is the relevant view model to be used.
     * @param lifecycleOwner is class that has a lifecycle.
     * @param observer is callback that is able to receive from {@link androidx.lifecycle.LiveData}.
     */
    default void startObserver(GameViewModel viewModel, LifecycleOwner lifecycleOwner, Observer<T> observer) {
        viewModel.getData().observe(lifecycleOwner, observer);
        viewModel.subscribeToRepo();
    }


}
