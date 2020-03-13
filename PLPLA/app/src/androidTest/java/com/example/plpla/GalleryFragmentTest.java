package com.example.plpla;
import android.content.Intent;
import android.view.Gravity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.plpla.MainActivity;
import com.example.plpla.R;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
public class GalleryFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);





    @Test
    public void allerAuParcours(){

        Intent startIntent = new Intent();
        mActivityRule.launchActivity(startIntent);
        MockitoAnnotations.initMocks(this);


        onView(withId(R.id.button)).perform(click());


        ///FIRST ACTION

        /*On vérifie que le menu est fermé puis on clique sur le bouton menu*/
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        /*On clique sur Mon parcours*/
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery));

        /*On verifie qu'on ne peut pas cliquer sur reinitialiser*/
        onView(withId(R.id.reinitialiser_button)).check(matches(not(isEnabled())));

        /*Le parcours est vide*/
        onView(withId(R.id.parcours_vide)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //SECOND ACTION

        /*On vérifie que le menu est fermé puis on clique sur le bouton menu*/
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        /*On clique sur Portail*/
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_home));

        /*On clique sur le bouton semestre 1*/
        onView(withId(R.id.BoutonSemestre)).perform(click());

        /*Selection de la première checkbox*/
        onView(withId(R.id.checkBoxEmplacement1)).perform(click());

        // On clique sur enregister
        onView(withId(R.id.Enregistrer)).perform(click());

        /*On vérifie que le menu est fermé puis on clique sur le bouton menu*/
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        /*On clique sur Mon parcours*/
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery));

        /*On verifie qu'on peut cliquer sur reinitialiser*/
        onView(withId(R.id.reinitialiser_button)).check(matches(isEnabled()));

        /*Le parcours n'est pas vide*/
        onView(withId(R.id.parcours_vide)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        /*On verifie qu'il y a eu écriture*/
        onView(withId(R.id.parcours_final)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // On clique sur reinitialiser
        onView(withId(R.id.reinitialiser_button)).perform(click());

        /*Le parcours est vide*/
        onView(withId(R.id.parcours_vide)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

}
