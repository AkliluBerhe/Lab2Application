package com.example.softwareengineerlab2;

import android.widget.Button;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class) // run with junit4
public class MainActivityTest {

    //specifies this activity is launched, only single activity
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);
    public IntentsTestRule<LogInActivity> intentsTestRule1 = new IntentsTestRule<>(LogInActivity.class);

    //when we click button
    @Test
    public void clickButton() {
        //find view and perform action on view
        onView(withId(R.id.openLoginActivity)).perform(click());

        // if main activity approaches login activity
        intended(hasComponent(LogInActivity.class.getName()));

        // lets put the logic test of login activity heare.


        // check if the view does what we expected
        //onView(withId(R.id.login_name)).check(matches(withText("Hello world")));

    }

    /*@Test
    public void testLaunch() {
        //find view and perform action on view
        onView(withId(R.id.loginButton)).perform(click());

        // check if the view does what we expected
        onView(withId(R.id.login_name)).check(matches(withText("Hello world")));
        onView(withId(R.id.login_password)).check(matches(withText("Hello world")));*/

}