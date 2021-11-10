package developer.subhadeep.threatball.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import developer.subhadeep.threatball.R;
import developer.subhadeep.threatball.model.datastore.Data;
import developer.subhadeep.threatball.viewmodel.GameViewModel;
import developer.subhadeep.threatball.viewmodel.ViewModel_Activity1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



/**
 *
 * <h1> Activity1_MainMenu </h1>
 *
 * <p> This is the first screen in the application that displays the main menu.
 * It provides the option to launch a new game, show a help screen and also
 * display the overall high score.</>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */

@AndroidEntryPoint
public class Activity1_MainMenu extends AppCompatActivity implements Observer<Data>, View.OnClickListener, GameActivity<Data> {


    /**
     * This method automatically called when the activity is first created.
     * First a layout that is to be displayed by this activity is set.
     * Later we make use of the relevant view model to start observing for previous high score values
     * that was store to the local datastore and display that information on screen.
     *
     * @param savedInstanceState contains any data that was passed to {@link android.app.Activity#onSaveInstanceState(Bundle)}
     *                           which may be used to previous state of the activity if it previously shutdown.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main_menu);
        startObserver (getViewModel(), this, this);
    }


    /**
     * This method is called when a high score data contained in the {@link androidx.lifecycle.LiveData}
     * container is changed. This information is displayed on screen.
     *
     * @param data contains the previously saved high score.
     */
    @Override
    public void onChanged(Data data) {
        ((TextView) findViewById(R.id.score)).setText(String.valueOf(data.getHighScore()));
    }


    /**
     * This method handles user interactions with the PLAY and HELP buttons.
     *
     * @param view is used to identify which button was clicked.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_play:
                Intent intent = new Intent(this, Activity2_Loading.class);
                intent.setFlags (Intent.FLAG_ACTIVITY_NO_HISTORY);
                startNextActivity (intent);

                break;
            case R.id.button_help:
                startNextActivity (new Intent(this, Activity4_Help.class));
                break;
        }
    }


    /**
     * Depending on which button was clicked, a new activity is started. This method allows us to do so.
     *
     * @param intent helps launch the new activity.
     */
    private void startNextActivity (Intent intent) {
        intent.addFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    /**
     * This method is used to get the relevant view model which allows communication with data layer.
     *
     * @return a {@link GameViewModel}
     */
    @Override
    public GameViewModel getViewModel() {
        return new ViewModelProvider(this).get(ViewModel_Activity1.class);
    }


}