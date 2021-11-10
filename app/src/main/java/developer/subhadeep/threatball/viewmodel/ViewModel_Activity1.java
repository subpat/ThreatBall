package developer.subhadeep.threatball.viewmodel;


import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.TestOnly;

import dagger.hilt.android.lifecycle.HiltViewModel;
import developer.subhadeep.threatball.model.LocalDataStore;
import developer.subhadeep.threatball.model.LocalDataStoreRepo;
import developer.subhadeep.threatball.model.datastore.Data;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 *
 * <h1> ViewModel_Activity1 </h1>
 *
 * <p> This class acts as a bridge been the View and Model classes for the
 * {@link developer.subhadeep.threatball.view.Activity1_MainMenu} view.
 * It retrieves high score data from disk using RxJava and converts the response to lifecycle aware
 * LiveData for the view to observe it.</>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

@HiltViewModel
public class ViewModel_Activity1 extends ViewModel implements GameViewModel {

    /**
     * This variable is used store all the {@link Disposable}s generated.
     */
    private CompositeDisposable compositeDisposable;


    /**
     * This variable stores the object of the {@link LocalDataStore} implementation, used to access the high score from disk.
     */
    private LocalDataStore localDataStoreRepository;


    /**
     * This variable is used to supply the high score data to the view.
     */
    private MutableLiveData<Data> highScoreMutableLiveData;


    /**
     * This constructor is to instantiate the class, should be only used for testing purposes.
     *
     * @param testDataStoreRepository allows access to the saved high score data.
     */
    @TestOnly
    public ViewModel_Activity1(LocalDataStore testDataStoreRepository) {
        setUp (testDataStoreRepository);
    }


    /**
     * Constructor that is called automatically.
     *
     * @param localDataStoreRepository is used to access the saved high score from disk.
     */
    @Inject
    public ViewModel_Activity1 (LocalDataStoreRepo localDataStoreRepository) {
        setUp (localDataStoreRepository);
    }


    /**
     * This method is used to initialize the global variables.
     *
     * @param dataStoreRepository provides access to the saved high score.
     */
    private void setUp (LocalDataStore dataStoreRepository) {
        this.compositeDisposable = new CompositeDisposable();
        this.highScoreMutableLiveData = new MutableLiveData<>();
        this.localDataStoreRepository = dataStoreRepository;
    }


    /**
     * A method that exposes the encapsulated high score data to the {@link developer.subhadeep.threatball.view.Activity1_MainMenu}.
     *
     * @return data encapsulated in {@link LiveData}.
     */
    @Override
    public LiveData<Data> getData () {
        return highScoreMutableLiveData;
    }


    /**
     * This method is used to dispose off all {@link Disposable}s.
     */
    @Override
    protected void onCleared () {
        compositeDisposable.clear();
        super.onCleared();
    }


    /**
     * This method is used to subscribe to the high score data from the repository.
     */
    @Override
    public void subscribeToRepo() {
        Flowable<Data> flowable = localDataStoreRepository.retrieve();
        flowable = flowable.observeOn(AndroidSchedulers.mainThread());
        flowable = flowable.subscribeOn(Schedulers.io());

        Consumer<Data> onNextConsumer = data -> highScoreMutableLiveData.postValue (data);
        Consumer<Throwable> onErrorConsumer = throwable ->
                highScoreMutableLiveData.postValue (Data.getDefaultObject()); // no information related to high score
        Disposable disposable = flowable.subscribe(onNextConsumer, onErrorConsumer);
        compositeDisposable.add(disposable);
    }


}
