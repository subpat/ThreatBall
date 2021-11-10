package developer.subhadeep.threatball.viewmodel;

import org.jetbrains.annotations.TestOnly;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import developer.subhadeep.threatball.model.Loading;
import developer.subhadeep.threatball.model.LoadingRepo;
import developer.subhadeep.threatball.model.LocalDataStore;
import developer.subhadeep.threatball.model.datastore.Data;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;



/**
 *
 * <h1> ViewModel_Activity2 </h1>
 *
 * <p> This class acts as a bridge been the View and Model classes for the
 * {@link developer.subhadeep.threatball.view.Activity2_Loading} view.
 * It gets notified when the game view can be loaded (after 500 ms), with the help of RxJava {@link Completable}.
 * It converts the response received to lifecycle aware LiveData for the view to observe it.</>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

@HiltViewModel
public class ViewModel_Activity2 extends ViewModel implements GameViewModel {

    /**
     * It is used access the Rxjava {@link Completable}.
     */
    private Loading loadingRepository;


    /**
     * This variable is used store all the {@link Disposable}s generated.
     */
    private CompositeDisposable compositeDisposable;


    /**
     * This variable is used to notify the view, that the game may be started.
     */
    private MutableLiveData<Boolean> loadingMutableLiveData;


    /**
     * This constructor is to instantiate the class, should be only used for testing purposes.
     *
     * @param testLoadingRepository allows access the Rxjava {@link Completable}.
     */
    @TestOnly
    public ViewModel_Activity2 (Loading testLoadingRepository) {
        setUp (testLoadingRepository);
    }


    /**
     * Constructor that is called automatically.
     *
     * @param loadingRepository allows access the Rxjava {@link Completable}.
     */
    @Inject
    public ViewModel_Activity2 (LoadingRepo loadingRepository) {
        setUp (loadingRepository);
    }


    /**
     * This method is used to initialize the global variables.
     *
     * @param loadingRepository allows access the Rxjava {@link Completable}.
     */
    private void setUp (Loading loadingRepository) {
        loadingMutableLiveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        this.loadingRepository = loadingRepository;
    }


    /**
     * This method is used to subscribe to the {@link Completable} from the repository.
     */
    @Override
    public void subscribeToRepo() {
        Completable completable = loadingRepository.get();
        completable = completable.observeOn(AndroidSchedulers.mainThread());
        completable = completable.subscribeOn(Schedulers.io());

        Action onComplete = () -> loadingMutableLiveData.postValue(new Boolean(true));
        Consumer<Throwable> onErrorConsumer = throwable -> loadingMutableLiveData.postValue (new Boolean(false));
        Disposable disposable = completable.subscribe(onComplete, onErrorConsumer);
        compositeDisposable.add(disposable);
    }


    /**
     * A method that exposes the encapsulated {@link Boolean} data to
     * the {@link developer.subhadeep.threatball.view.Activity2_Loading}.
     *
     * @return data encapsulated in {@link LiveData}.
     */
    @Override
    public LiveData<Boolean> getData() {
        return loadingMutableLiveData;
    }


    /**
     * This method is used to dispose off all {@link Disposable}s.
     */
    @Override
    protected void onCleared () {
        compositeDisposable.clear();
        super.onCleared();
    }


}
