package developer.subhadeep.threatball.view;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import developer.subhadeep.threatball.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class Activity2_LoadingTest {

    // PLEASE CHANGE THE VALUE OF AppConstants.MIN_LOADING_DELAY_MILLIS TO A HIGH VALUE BEFORE TESTING

    @Rule
    public ActivityScenarioRule<Activity2_Loading> activityRule = new ActivityScenarioRule<>(Activity2_Loading.class);

    @Test
    public void backgroundTest () {
        onView(withId(R.id.background)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void appBarTest () {
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()));
    }

    @Test
    public void progressBarTest () {
        onView(withId(R.id.progress_circular)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void loadingTextTest () {
        onView(withId(R.id.loading_text)).check(matches(isCompletelyDisplayed()));
    }


}