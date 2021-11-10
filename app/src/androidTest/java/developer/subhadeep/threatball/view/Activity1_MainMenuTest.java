package developer.subhadeep.threatball.view;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import developer.subhadeep.threatball.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class Activity1_MainMenuTest {

    @Rule
    public ActivityScenarioRule<Activity1_MainMenu> activityRule = new ActivityScenarioRule<>(Activity1_MainMenu.class);

    @Test
    public void backgroundTest () {
        onView(withId(R.id.background)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void appBarTest () {
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()));
    }

    @Test
    public void scoreTest () {
        onView(withId(R.id.score_heading)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.score)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void playButtonTest () {
        onView(withId(R.id.button_play)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.button_play)).check(matches(isClickable()));
    }

    @Test
    public void helpButtonTest () {
        onView(withId(R.id.button_help)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.button_help)).check(matches(isClickable()));
    }


}