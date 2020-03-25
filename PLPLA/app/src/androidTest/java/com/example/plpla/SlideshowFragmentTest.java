package com.example.plpla;

import android.content.Intent;
import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class SlideshowFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);





    @Test
    public void allerARecherche(){

        Intent startIntent = new Intent();
        mActivityRule.launchActivity(startIntent);
        MockitoAnnotations.initMocks(this);


        onView(withId(R.id.button)).perform(click());


        ///FIRST ACTION

        /*On vérifie que le menu est fermé puis on clique sur le bouton menu*/
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        /*On clique sur Recherche*/
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_slideshow));

        /*On verifie qu'on peut cliquer sur la loupe*/
        onView(withId(R.id.action_search)).check(matches(isEnabled()));

        //On clique sur la loupe
        onView(withId(R.id.action_search)).perform(click());


    }

}