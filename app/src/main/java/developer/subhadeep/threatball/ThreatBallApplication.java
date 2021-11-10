package developer.subhadeep.threatball;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import developer.subhadeep.threatball.model.datastore.GameData;


/**
 *
 * <h1> ThreadBallApplication </h1>
 *
 * <p> We use this class to inform the Android OS that we will be taking advantage of Dependency Injection using Dagger Hilt.  </>
 *
 * @author  Subhadeep Patra
 * @version 1.0
 * @since   2021-09-20
 *
 */

@HiltAndroidApp
public class ThreatBallApplication extends Application {}
