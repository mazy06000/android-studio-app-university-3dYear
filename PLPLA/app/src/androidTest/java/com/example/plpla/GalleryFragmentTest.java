package com.example.plpla;
import android.content.Intent;

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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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




        /*On clique sur le bouton semestre 1*/
        onView(withId(R.id.nav_host_fragment)).perform(click());

        /*On vérifie que les checkbox sont visible après le click sur le bouton*/
        onView(withId(R.id.checkBoxEmplacement1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.checkBoxEmplacement2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        /*Test des checkbox, on ne peut normalement pas cocher les 2 checkbox à la foix*/
        /*Selection de la première checkbox*/
        onView(withId(R.id.checkBoxEmplacement1)).perform(click());

        /*On verifie qu'on ne peut plus selectionner le deuxième choix*/
        onView(withId(R.id.checkBoxEmplacement2)).check(matches(not(isEnabled())));

        /*Deselection de la 1ère checkbox*/
        onView(withId(R.id.checkBoxEmplacement1)).perform(click());

        /*On vérifie qu'on peut sélectionner le 2ème choix maintenant*/
        onView(withId(R.id.checkBoxEmplacement2)).check(matches(isEnabled()));

        /*On fait maintenant le test inverse*/
        onView(withId(R.id.checkBoxEmplacement2)).perform(click());

        /*On verifie qu'on ne peut plus selectionner le 1er choix*/
        onView(withId(R.id.checkBoxEmplacement1)).check(matches(not(isEnabled())));

        /*Deselection de la 2ème checkbox*/
        onView(withId(R.id.checkBoxEmplacement2)).perform(click());

        /*On vérifie qu'on peut sélectionner le 1er choix maintenant*/
        onView(withId(R.id.checkBoxEmplacement1)).check(matches(isEnabled()));


    }

}
