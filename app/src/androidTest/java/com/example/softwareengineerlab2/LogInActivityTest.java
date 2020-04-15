package com.example.softwareengineerlab2;

import android.content.Context;
import android.service.autofill.Validator;
import android.support.test.InstrumentationRegistry;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LogInActivityTest {
    String toast_error, username_empty;
    public static final String USERNAME_TYPED = "aklilu";


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.softwareengineerlab2", appContext.getPackageName());
    }

    @Rule
    //public ActivityTestRule<MainActivity> mainActivityActivityScenarioRule = new ActivityTestRule<>(MainActivity.class);
    // public ActivityScenarioRule<LogInActivity> logInActivityActivityScenarioRule = new ActivityScenarioRule<>(LogInActivity.class);
    public ActivityTestRule<LogInActivity> logInActivityActivityTestRule = new ActivityTestRule<>(LogInActivity.class);

    @Test
    public void testLaunch() {

        //find view and perform action on view
        onView(withId(R.id.login_name)).perform(replaceText("helo"), closeSoftKeyboard());
        onView(withId(R.id.login_password)).perform(replaceText("helo"), closeSoftKeyboard());

        //perform action on view
        onView(withId(R.id.loginButton)).perform(click());

        // check if the view does what we expected
        onView(withId(R.id.login_name)).check(matches(withText("helo")));
        onView(withId(R.id.login_password)).check(matches(withText("helo")));


    }

    // toast error
    @Test
    public void shouldShowToastError(){
        String toast_error;
        onView(withId(R.id.login_name))
                .perform(typeText(USERNAME_TYPED));
        onView(withId(R.id.login_password))
                .perform(typeText("123456"));

        onView(withId(R.id.loginButton)).perform(click());
//toast test must match this toast_error
        onView(withText(R.string.toast_error)).inRoot(withDecorView((Matcher<View>) not((Validator) is(logInActivityActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    // toast error when either user name or password is empty
    @Test
    public void shouldShowToastUsernameEmpty() {
        onView(withId(R.id.login_name))
                .perform(typeText(USERNAME_TYPED));
        onView(withId(R.id.login_password))
                .perform(typeText("12345"));

        onView(withId(R.id.loginButton)).perform(click());
//toast test must match this username_empty
        onView(withText(R.string.username_empty)).inRoot(withDecorView((Matcher<View>) not((Validator) is(logInActivityActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }


}