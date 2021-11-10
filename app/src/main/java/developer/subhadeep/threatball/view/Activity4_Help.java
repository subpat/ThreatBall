package developer.subhadeep.threatball.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import developer.subhadeep.threatball.R;


/**
 *
 * <h1> Activity4_Help </h1>
 *
 * <p> This is screen displays some information related to the game. It consists of a text and a button.</>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-26
 *
 */


public class Activity4_Help extends AppCompatActivity {


    /**
     * This method automatically called when the activity is first created.
     * Here only the layout that is to be displayed by this activity is set.
     *
     * @param savedInstanceState contains any data that was passed to {@link android.app.Activity#onSaveInstanceState(Bundle)}
     *                           which may be used to previous state of the activity if it previously shutdown.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_help);
    }


    /**
     * This method handles user interactions with the OK button.
     *
     * @param view is used to identify which item on screen was clicked on.
     *             Since the OK button is the only clickable item displayed, we perform the action
     *             without any further checks.
     */
    public void onClick(View view) {
        onBackPressed();
    }


    /**
     * This method handles the back button press and it removes any default animation attached to this activity.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }


}