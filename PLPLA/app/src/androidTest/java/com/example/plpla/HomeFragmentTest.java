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
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;


public class HomeFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);





    @Test
    public void afficheCheckBox(){

        Intent startIntent = new Intent();
        mActivityRule.launchActivity(startIntent);
        MockitoAnnotations.initMocks(this);


        onView(withId(R.id.button)).perform(click());



        /*Les checkbox sont normalent invisible avant le clique sur le bouton semestre*/
        onView(withId(R.id.checkBoxEmplacement1)).check(matches(not(isDisplayed())));
        onView(withId(R.id.checkBoxEmplacement2)).check(matches(not(isDisplayed())));

        /*On clique sur le bouton semestre 1*/
        onView(withId(R.id.BoutonSemestre)).perform(click());

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
    @Test
    public void testToucheEnregistrer() {
        Intent startIntent = new Intent();
        mActivityRule.launchActivity(startIntent);
        MockitoAnnotations.initMocks(this);


        onView(withId(R.id.button)).perform(click());


        /*Le Bouton est normalent invisible avant le clique sur une checkbox
         */
        onView(withId(R.id.Enregistrer)).check(matches(not(isDisplayed())));

        /*On clique sur le bouton semestre 1
         */

        onView(withId(R.id.BoutonSemestre)).perform(click());


        // on Vérifie que le bouton enregistrer visible et non clickable
        onView(withId(R.id.Enregistrer)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));

        /*Selection de la première checkbox
         */
        onView(withId(R.id.checkBoxEmplacement1)).perform(click());


        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));


        // On déselectionne la première checkbox
        onView(withId(R.id.checkBoxEmplacement1)).perform(click());


        // on revérifie si le bouton enregistrer et non cliclable :
        onView(withId(R.id.Enregistrer)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));
        //onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));


        // on sélectionne la deuxième checkbox :
        onView(withId(R.id.checkBoxEmplacement2)).perform(click());


        // on vérifie que c'est clickable et not enabled :
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));

        // On déselectionne la deuxieme checkbox
        onView(withId(R.id.checkBoxEmplacement2)).perform(click());


        // on revérifie si le bouton enregistrer et non cliclable :
        onView(withId(R.id.Enregistrer)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));


    }
}
