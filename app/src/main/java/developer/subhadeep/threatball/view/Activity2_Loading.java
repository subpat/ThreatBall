package developer.subhadeep.threatball.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import dagger.hilt.android.AndroidEntryPoint;
import developer.subhadeep.threatball.R;
import developer.subhadeep.threatball.viewmodel.GameViewModel;
import developer.subhadeep.threatball.viewmodel.ViewModel_Activity2;


/**
 *
 * <h1> Activity2_Loading </h1>
 *
 * <p> This class helps to show a loading screen for at least 500 ms, after which it loads the game.
 * Note that a minimum amount of time has been fixed since the loading screen may not be displayed
 * at all if the game screen starts up too quickly. </>
 *
 * <p> And if the game screen takes too much time to load, without the loading screen, it may result
 * in a black screen. </p>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */


@AndroidEntryPoint
public class Activity2_Loading extends AppCompatActivity implements Observer<Boolean>, GameActivity <Boolean> {

    /**
     * This method automatically called when the activity is first created.
     * First a layout that is to be displayed by this activity is set.
     * Later we make use of the relevant view model to start observing for a boolean object
     * that indicates that 500 ms has passed and we should start the game activity.
     *
     * @param savedInstanceState contains any data that was passed to {@link android.app.Activity#onSaveInstanceState(Bundle)}
     *                           which may be used to previous state of the activity if it previously shutdown.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_loading);
        startObserver (getViewModel(), this, this);
    }


    /**
     * This method is called when the boolean object contained in the {@link androidx.lifecycle.LiveData}
     * container is changed, indicating that 500 ms has passed.
     *
     * @param booleanObject contains an irrelevant value.
     */
    @Override
    public void onChanged(Boolean booleanObject) {
        startNextActivity ();
    }


    /**
     * This method is used to get the relevant view model which allows communication with data layer.
     *
     * @return a {@link GameViewModel}
     */
    @Override
    public GameViewModel getViewModel() {
        return new ViewModelProvider(this).get(ViewModel_Activity2.class);
    }


    /**
     * This method handles the back button press and it removes any default animation attached to this activity.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }


    /**
     * This method allows us to start the {@link Activity3_GamePanel} activity.
     */
    private void startNextActivity () {
        Intent intent = new Intent (this, Activity3_GamePanel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


}