package algonquin.cst2335;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * This class is responsible for testing the functionality of the password complexity
 * check in the MainActivity class.
 *
 * @author Lei Luo
 * @version 1.0
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class
MainActivityTest {

    /**
     * Rule to launch MainActivity before running any test methods.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * This test method inputs a password and checks
     * if the correct error message "You shall not pass!" is displayed.
     */
    @Test
    public void mainActivityTest() {
//        find the view with id R.id.editText;
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
//        perform typing "12345" into that view, then close the keyboard.

        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

//        find the button with id R.id.button.
        ViewInteraction materialButton = onView(
              withId(R.id.button));
//        perform clicking that button
        materialButton.perform(click());
//        find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
//        check that its text matches "You shall not pass!"
        textView.check(matches(withText("You shall not pass!")));
    }
    /**
     * This test method inputs a password missing a digit  and checks
     * if the correct error message "You shall not pass!" is displayed.
     */
    @Test
    public void testFindMissingDigit() {


        ViewInteraction appCompatEditText = onView(withId(R.id.editText));

        appCompatEditText.perform(replaceText("Password#$"));

//        find the button with id R.id.button.
        ViewInteraction materialButton = onView(
                withId(R.id.button));
//        perform clicking that button
        materialButton.perform(click());
//        find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
//        check that its text matches "You shall not pass!"
        textView.check(matches(withText("You shall not pass!")));
    }



    /**
     * This test method inputs a password missing an lower letter and checks
     * if the correct error message "You shall not pass!" is displayed.
     */
    @Test
    public void testFindMissingLowerCase() {


        ViewInteraction appCompatEditText = onView(withId(R.id.editText));

        appCompatEditText.perform(replaceText("PPP12#$"));

//        find the button with id R.id.button.
        ViewInteraction materialButton = onView(
                withId(R.id.button));
//        perform clicking that button
        materialButton.perform(click());
//        find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
//        check that its text matches "You shall not pass!"
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * This test method inputs a password missing an Special Character and checks
     * if the correct error message "You shall not pass!" is displayed.
     */
    @Test
    public void testFindMissingSpecialCharacter() {


        ViewInteraction appCompatEditText = onView(withId(R.id.editText));

        appCompatEditText.perform(replaceText("PPPabc12"));

//        find the button with id R.id.button.
        ViewInteraction materialButton = onView(
                withId(R.id.button));
//        perform clicking that button
        materialButton.perform(click());
//        find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
//        check that its text matches "You shall not pass!"
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * This test method inputs a password missing an uppercase letter and checks
     * if the correct error message "You shall not pass!" is displayed.
     */
    @Test
    public void testFindMissingUpperCase(){


        ViewInteraction appCompatEditText = onView(withId(R.id.editText));

        appCompatEditText.perform(replaceText("password123#$"));

//        find the button with id R.id.button.
        ViewInteraction materialButton = onView(
                withId(R.id.button));
//        perform clicking that button
        materialButton.perform(click());
//        find the view with id R.id.textView
        ViewInteraction textView = onView(withId(R.id.textView));
//        check that its text matches "You shall not pass!"
        textView.check(matches(withText("You shall not pass!")));

    }

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
