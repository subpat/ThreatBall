package developer.subhadeep.threatball.viewmodel;

import javax.inject.Inject;
import android.graphics.Point;

import org.jetbrains.annotations.TestOnly;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import developer.subhadeep.threatball.model.sensor.OrientationManager;
import io.reactivex.rxjava3.core.Single;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.core.Flowable;
import developer.subhadeep.threatball.STATE;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.Disposable;
import developer.subhadeep.threatball.model.FrameControl;
import developer.subhadeep.threatball.model.Initialization;
import developer.subhadeep.threatball.model.LocalDataStore;
import developer.subhadeep.threatball.model.datastore.Data;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import developer.subhadeep.threatball.model.FrameControlRepo;
import developer.subhadeep.threatball.model.sensor.OrientationHandler;
import developer.subhadeep.threatball.viewmodel.misc.Analysis;
import developer.subhadeep.threatball.model.LocalDataStoreRepo;
import developer.subhadeep.threatball.model.datastore.GameData;
import developer.subhadeep.threatball.model.position.Positions;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import developer.subhadeep.threatball.viewmodel.misc.StateHandler;
import developer.subhadeep.threatball.viewmodel.misc.StateManager;
import developer.subhadeep.threatball.model.OrientationChangeRepo;
import developer.subhadeep.threatball.model.ArtifactsInitializationRepo;



/**
 *
 * <h1> ViewModel_Activity3 </h1>
 *
 * <p> This class acts as a bridge been the View and Model classes for the
 * {@link developer.subhadeep.threatball.view.Activity3_GamePanel} view.
 * It performs all operations on data using RxJava and converts the response to lifecycle aware
 * LiveData for the view to observe it.</>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

@HiltViewModel
public class ViewModel_Activity3  extends ViewModel implements GameViewModel {

    /**
     * This variable is used to keep track of the different states of the game lifecycle.
     */
    private StateHandler stateHandler;


    /**
     * This variable is used store all the {@link Disposable}s generated.
     */
    private CompositeDisposable compositeDisposable;


    /**
     * This variable is used to control the FPS of the game.
     */
    private MutableLiveData<Long> frameMutableLiveData;


    /**
     * This variable is used to supply the generated initial positions of artifacts to the view.
     */
    private MutableLiveData<Positions> initialPositionsMutableLiveData;


    /**
     * This variable is used to supply the view with the change in position of the ball.
     */
    private MutableLiveData<Point> positionChangeMutableLiveData;


    /**
     * This variable stores the object of the {@link FrameControl} implementation, used in controlling frames.
     */
    private FrameControl frameControlRepo;


    /**
     * This variable stores the object of the {@link LocalDataStore} implementation, used to store high scores on disk.
     */
    private LocalDataStore localDataStoreRepository;


    /**
     * This variable stores the object of the {@link Initialization} implementation,
     * used to initialize the game with initial artifacts position.
     */
    private Initialization artifactsInitializationRepo;


    /**
     * This variable stores the object of the {@link OrientationHandler} implementation,
     * used to gather orientation related information.
     */
    private OrientationHandler orientationChangeRepo;



    /**
     * This constructor is to instantiate the class, should be only used for testing purposes.
     *
     * @param stateManager keep track and manage of state of the current game.
     * @param localDataStoreRepository is used for saving and retrieving scores from disk.
     * @param frameControlRepo advices on changing the currently displayed frame.
     * @param artifactsInitializationRepo is used to get the initial position related information of game artifacts.
     * @param ballPositionChangeRepo supplies the position related changes due to orientation change.
     *
     */
    @TestOnly
    public ViewModel_Activity3 (StateHandler stateManager, LocalDataStore localDataStoreRepository,
                                FrameControl frameControlRepo, Initialization artifactsInitializationRepo,
                                OrientationManager ballPositionChangeRepo) {
        setUp (stateManager, localDataStoreRepository, frameControlRepo, artifactsInitializationRepo, ballPositionChangeRepo);
    }


    /**
     * Constructor that is called automatically.
     *
     * @param stateManager keep track and manage of state of the current game.
     * @param localDataStoreRepository is used for saving and retrieving scores from disk.
     * @param frameControlRepo advices on changing the currently displayed frame.
     * @param artifactsInitializationRepo is used to get the initial position related information of game artifacts.
     * @param orientationChangeRepo supplies the position related changes due to orientation change.
     */
    @Inject
    public ViewModel_Activity3 (StateManager stateManager, LocalDataStoreRepo localDataStoreRepository,
                                FrameControlRepo frameControlRepo, ArtifactsInitializationRepo artifactsInitializationRepo,
                                OrientationChangeRepo orientationChangeRepo) {
        setUp (stateManager, localDataStoreRepository, frameControlRepo, artifactsInitializationRepo, orientationChangeRepo);
    }


    /**
     * This method is used to initialize the global variables.
     *
     * @param stateManager keep track and manage of state of the current game.
     * @param localDataStoreRepository is used for saving and retrieving scores from disk.
     * @param frameControlRepo advices on changing the currently displayed frame.
     * @param artifactsInitializationRepo is used to get the initial position related information of game artifacts.
     * @param orientationChangeRepo supplies the position related changes due to orientation change.
     */
    private void setUp (StateHandler stateManager, LocalDataStore localDataStoreRepository,
                        FrameControl frameControlRepo, Initialization artifactsInitializationRepo,
                        OrientationManager orientationChangeRepo) {

        compositeDisposable = new CompositeDisposable();

        frameMutableLiveData = new MutableLiveData<>();
        initialPositionsMutableLiveData = new MutableLiveData<>();
        positionChangeMutableLiveData = new MutableLiveData<>();

        this.stateHandler = stateManager;
        this.frameControlRepo = frameControlRepo;
        this.artifactsInitializationRepo = artifactsInitializationRepo;
        this.orientationChangeRepo = orientationChangeRepo;
        this.localDataStoreRepository = localDataStoreRepository;
    }


    /**
     * This method is used to dispose off all {@link Disposable}s.
     */
    @Override
    protected void onCleared () {
        unSubscribeFromRepo();
    }


    /**
     * This method is supposed to subscribe to a repository, but is unused in this case.
     */
    @Override
    public void subscribeToRepo() {}


    /**
     * This method is used to subscribe to the data from the repository that is used to control the frames of the game.
     */
    private void subscribeToFrameControlRepo() {
        Observable<Long> observable = frameControlRepo.get();
        observable = observable.observeOn(AndroidSchedulers.mainThread());
        observable = observable.subscribeOn(Schedulers.computation());
        Consumer<Long> onNextConsumer = aLong -> {
            frameMutableLiveData.postValue(aLong);
            checkGameState(); // perform some operation depending on the state of the game
        };
        Consumer<Throwable> onErrorConsumer = throwable -> frameMutableLiveData.postValue(new Long(Long.MIN_VALUE));
        Disposable disposable = observable.subscribe(onNextConsumer, onErrorConsumer);
        compositeDisposable.add(disposable);
    }


    /**
     * This method handles operations like subscribing to orientation data from the model layer,
     * when the user is playing the game, or to stop the subscription when the game ends.
     */
    private void checkGameState() {
        if (stateHandler.getState() == STATE.STARTED) registerForPositionUpdate();
        else {
            switch (stateHandler.getState()) {
                case LOST:
                case WON:
                    unregisterFromPositionUpdate();
                    break;
            }
        }
    }


    /**
     * This method is used to subscribe to the repository that generates the initial positions of game artifacts.
     */
    private void subscribeToArtifactsInitialPosRepo() {
        Single<Positions> initialPositionsSingle = artifactsInitializationRepo.get();
        initialPositionsSingle = initialPositionsSingle.observeOn(AndroidSchedulers.mainThread());
        initialPositionsSingle = initialPositionsSingle.subscribeOn(Schedulers.io());
        Consumer<Positions> onSuccess = initialPositions -> initialPositionsMutableLiveData.postValue(initialPositions);
        Disposable disposable = initialPositionsSingle.subscribe(onSuccess);
        compositeDisposable.add(disposable);
    }


    /**
     * This method is used to subscribe to changes in position from the {@link OrientationChangeRepo}.
     */
    private void registerForPositionUpdate() {
        if (!orientationChangeRepo.isRegistered()) orientationChangeRepo.register();
        Single<Point> pointSingle = orientationChangeRepo.requestChangeInPosition();
        pointSingle = pointSingle.observeOn(AndroidSchedulers.mainThread());
        pointSingle = pointSingle.subscribeOn(Schedulers.io());
        Consumer<Point> onSuccess = initialPositions -> {
            if(stateHandler.getState() == STATE.STARTED) positionChangeMutableLiveData.postValue(initialPositions);
        };
        Disposable disposable = pointSingle.subscribe(onSuccess);
        compositeDisposable.add(disposable);
    }


    /**
     * This method is used to unsubscribe from changes in position coming from the {@link OrientationChangeRepo}.
     */
    private void unregisterFromPositionUpdate() {
        if (orientationChangeRepo.isRegistered()) orientationChangeRepo.unregister();
    }


    /**
     * This method is used to notify this view model that the view is not in the foreground.
     */
    public void onPause () {
        unSubscribeFromRepo();
    }


    /**
     * This method is used to notify this view model that the view is back to the foreground.
     */
    public void onResume () {
        subscribeToFrameControlRepo();
    }


    /**
     * This method is used to initialize the game by requesting for initial positions of artifacts.
     */
    public void initGame () {
        subscribeToArtifactsInitialPosRepo(); // get initial positions
        stateHandler.setState(STATE.START);
    }


    /**
     * This method is used to notify this view model that the user has started playing this game.
     */
    public void startGame () {
        stateHandler.setState(STATE.STARTED);
    }


    /**
     * This method is used to notify this view model that the game is over
     * and that the current score is to be saved if it surpasses the previously set high score.
     *
     * @param currentScore indicates the winning streak of the user.
     */
    public void endGame (int currentScore) {
        Flowable<Data> flowable = localDataStoreRepository.retrieve();
        flowable = flowable.observeOn(AndroidSchedulers.mainThread());
        flowable = flowable.subscribeOn(Schedulers.io());
        Consumer<Data> onNextConsumer = data -> {
            if (currentScore > data.getHighScore()) localDataStoreRepository.store(new GameData(currentScore));
        };
        Consumer<Throwable> onErrorConsumer = throwable -> localDataStoreRepository.store(new GameData(currentScore));
        Disposable disposable = flowable.subscribe(onNextConsumer, onErrorConsumer);
        compositeDisposable.add(disposable);
    }


    /**
     * This method is used to detect if the game is won by guiding the ball to the destination hole.
     *
     * @param currentPositions has the actual positions of all displayed artifacts.
     * @return a boolean indicating whether the user has won.
     */
    public boolean hasWon (Positions currentPositions) {
        if (Analysis.isGameWon(currentPositions.getBallPosition(), currentPositions.getWinHolePosition())) {
            stateHandler.setState(STATE.WON);
            return true;
        }
        return false;
    }


    /**
     * This method is used to detect if the game is lost due to the ball falling in a obstacle hole.
     *
     * @param currentPositions has the actual positions of all displayed artifacts.
     * @return a boolean indicating whether the user has actually lost.
     */
    public boolean hasLost (Positions currentPositions) {
        if (Analysis.isGameLost(currentPositions.getBallPosition(), currentPositions.getObstaclesPosition())) {
            stateHandler.setState(STATE.LOST);
            return true;
        }
        return false;
    }


    /**
     * Method to get the current state of the game.
     *
     * @return a state indicated by {@link STATE}.
     */
    public STATE getState () { return stateHandler.getState(); }


    /**
     * A method that exposes the encapsulated {@link Long} data to the {@link developer.subhadeep.threatball.view.Activity3_GamePanel}.
     * This value just acts as a reminder to the view to update the display by drawing the next frame.
     *
     * @return {@link Long} data encapsulated in {@link LiveData}.
     */
    @Override public LiveData<Long> getData() {
        return frameMutableLiveData;
    }


    /**
     * A method that exposes the encapsulated {@link Point} data to the {@link developer.subhadeep.threatball.view.Activity3_GamePanel}.
     * This data is used to update the position of the ball.
     *
     * @return {@link Point} data encapsulated in {@link LiveData}.
     */
    public LiveData<Point> getPositionChangeUpdate () {
        return positionChangeMutableLiveData;
    }


    /**
     * A method that exposes the encapsulated {@link Positions} data to the {@link developer.subhadeep.threatball.view.Activity3_GamePanel}.
     * This data is used to generate the initial positions of the game artifacts.
     *
     * @return {@link Positions} data encapsulated in {@link LiveData}.
     */
    public LiveData<Positions> getInitialPositionData() {
        return initialPositionsMutableLiveData;
    }


    /**
     * This method is used to dispose off all {@link Disposable}s.
     */
    public void unSubscribeFromRepo() {
        compositeDisposable.clear();
        super.onCleared();
    }


}
