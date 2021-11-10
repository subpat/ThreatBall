package developer.subhadeep.threatball.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import developer.subhadeep.threatball.AppConstants;
import developer.subhadeep.threatball.R;
import developer.subhadeep.threatball.model.position.ArtifactsPosition;
import developer.subhadeep.threatball.model.position.Positions;
import developer.subhadeep.threatball.view.extra.MovementRestricter;
import developer.subhadeep.threatball.view.game.GamePanelView;
import developer.subhadeep.threatball.view.scene.ThreatBallScene;
import developer.subhadeep.threatball.viewmodel.GameViewModel;
import developer.subhadeep.threatball.viewmodel.ViewModel_Activity3;
import developer.subhadeep.threatball.STATE;


/**
 *
 * <h1> Activity3_GamePanel </h1>
 *
 * <p> This the most important activity of this application. It display the game components
 * and user can interact by changing the orientation of the phone. Note that if any game
 * related messages appear on screen, it can be dismissed by clicking on screen. </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

@AndroidEntryPoint
public class Activity3_GamePanel extends AppCompatActivity implements Observer, GameActivity <Long> {


    /**
     * Provides a dedicated drawing surface embedded inside of a view hierarchy.
     */
    @Inject public GamePanelView gamePanelView;


    /**
     * The game scene that is to be displayed.
     */
    @Inject public ThreatBallScene threatBallScene;


    /**
     * Stores the current winning streak of the user.
     */
    private int currentScore;


    /**
     * Store the updated actual positions of all game artifacts.
     */
    private Positions currentPositions;


    /**
     * The relevant view model that acts as a bridge between the view and model layers.
     */
    private ViewModel_Activity3 viewModel_activity3;


    /**
     * This method automatically called when the activity is first created.
     * First a layout that is to be displayed by this activity is set.
     * Later we make use of the view model to start observing for relevant data:
     * like the FPS related data, initial position of artifacts, and position change due to phone movements.
     *
     * @param savedInstanceState contains any data that was passed to {@link android.app.Activity#onSaveInstanceState(Bundle)}
     *                           which may be used to previous state of the activity if it previously shutdown.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int paramFullScreen = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(paramFullScreen, paramFullScreen);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(gamePanelView);

        currentScore = 0;
        viewModel_activity3 = (ViewModel_Activity3) getViewModel();
        viewModel_activity3.getData().observe(this, this);
        viewModel_activity3.getInitialPositionData().observe(this, this);
        viewModel_activity3.getPositionChangeUpdate().observe(this, this);
    }


    /**
     * This method is automatically called when this Activity returns to the foreground.
     * Here we take the opportunity to subscribe to the {@link developer.subhadeep.threatball.model.FrameControlRepo}.
     */
    @Override
    protected void onResume () {
        super.onResume();
        viewModel_activity3.onResume ();
    }


    /**
     * This method is automatically called when this Activity leaves the foreground.
     * Here we take the opportunity to unsubscribe to the {@link developer.subhadeep.threatball.model.FrameControlRepo}.
     */
    @Override
    protected void onPause () {
        viewModel_activity3.onPause ();
        super.onPause();
    }


    /**
     * This method is used to get the relevant view model which allows communication with data layer.
     *
     * @return a {@link GameViewModel}
     */
    @Override
    public GameViewModel getViewModel() {
        return new ViewModelProvider(this).get(ViewModel_Activity3.class);
    }


    /**
     * This method is called when different data we are interested has changed,
     * we check the type of data and take appropriate actions depending on it.
     *
     * @param object contains data we want.
     */
    @Override
    public void onChanged(Object object) {
        if (object instanceof Long) {
            // The frame of the game need to be updated.
            if (!gamePanelView.hasActiveScene()) startNewScene();
            gamePanelView.requestDraw();
        }
        else if (object instanceof Positions) {
            // The initial positions of artifacts were received, so start game.
            currentPositions = (Positions) object;
            gamePanelView.init(currentPositions);
            viewModel_activity3.startGame();
        }
        else if (object instanceof Point) {
            // Change in position was evaluated from sensor data.
            updateBallPosition ((Point) object);
            if (viewModel_activity3.hasWon(currentPositions))
                gamePanelView.update(currentPositions.getBallPosition(), false, getString(R.string.score) + ++currentScore);
            else if (viewModel_activity3.hasLost(currentPositions))
                gamePanelView.update(currentPositions.getBallPosition(), false, getString(R.string.over));
            else gamePanelView.update(currentPositions.getBallPosition(), true, null);
        }
    }


    /**
     * This method is used to find the new corrected position of the ball on screen taking into account sensor updates.
     *
     * @param changeInPosition contains the value of the change in the position due movement of the phone.
     */
    private void updateBallPosition (Point changeInPosition) {
        Point currentBallPos = currentPositions.getBallPosition();
        currentBallPos.x = MovementRestricter.correctX(getResources().getDisplayMetrics(),
                currentBallPos.x + changeInPosition.x, AppConstants.BALL_SIZE);
        currentBallPos.y = MovementRestricter.correctY(getResources().getDisplayMetrics(),
                currentBallPos.y - changeInPosition.y, AppConstants.BALL_SIZE);
    }


    /**
     * This method is automatically called when user touches the screen.
     * We allow touches only when the user has to accept the some game related messages on screen.
     *
     * @param motionEvent contains information about the user touch event.
     * @return a boolean value indicating is the touch event was processed by this method.
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel_activity3.getState() == STATE.WON) startNewScene ();
            if (viewModel_activity3.getState() == STATE.LOST) onBackPressed();
        }
        return true;
    }


    /**
     * This method handles the back button press, stores the current score to disk if it the
     * highest score so far, and it removes any default animation attached to this activity before ending it.
     *
     * This method may be manually trigger by the user on pressing the back button.
     * Or it may be called by the application when the user has lost the game.
     */
    @Override
    public void onBackPressed() {
        viewModel_activity3.endGame(currentScore);
        super.onBackPressed();
    }


    /**
     * If the user wins the current game scene, the next scene with random game artifacts is started.
     */
    private void startNewScene () {
        threatBallScene.clearScene();
        gamePanelView.addNewSceneToManager(threatBallScene);
        viewModel_activity3.initGame();
    }


}