package developer.subhadeep.threatball.view;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import developer.subhadeep.threatball.R;


@RunWith(AndroidJUnit4.class)
public class Activity4_HelpTest {

    @Rule
    public ActivityScenarioRule<Activity4_Help> activityRule = new ActivityScenarioRule<>(Activity4_Help.class);

    @Test
    public void backgroundTest () {
        onView(withId(R.id.background)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void appBarTest () {
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()));
    }

    @Test
    public void helpTextTest () {
        onView(withId(R.id.help_text)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void buttonTest () {
        onView(withId(R.id.button_ok)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.button_ok)).check(matches(isClickable()));
    }

}