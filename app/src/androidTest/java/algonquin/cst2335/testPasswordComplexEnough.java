package algonquin.cst2335;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

/**
 * This class is responsible for testing the functionality of the password complexity
 * check in the MainActivity class.
 *
 * @author Lei Luo
 * @version 1.0
 */

public class testPasswordComplexEnough {

    /**
     * Rule to launch MainActivity before running any test methods.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This test method inputs a password is complex enough and checks
     * if the correct error message "You shall not pass!" is displayed.
     */
    @Test
    public void testPasswordComplexEnough() {


        ViewInteraction appCompatEditText = onView(withId(R.id.editText));

        appCompatEditText.perform(replaceText("PPPaabc12$#"));

//        find the button with id R.id.button.
        ViewInteraction materialButton = onView(
                withId(R.id.button));
//        perform clicking that button
        materialButton.perform(click());
//        find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
//        check that its text matches "You shall not pass!"
        textView.check(matches(withText("Your password meets the requirements")));
    }
}
