/*
package com.example.plpla;

import android.content.Intent;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.plpla.MainActivity;
import com.example.plpla.R;
import com.github.florent37.expansionpanel.ExpansionHeader;
import com.github.florent37.expansionpanel.ExpansionLayout;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;


public class PortailFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void afficheCheckBox(){ 

        Intent startIntent = new Intent();
        mActivityRule.launchActivity(startIntent);
        MockitoAnnotations.initMocks(this);

        //On clique sur le bouton pour le serveur
        onView(withId(R.id.button)).perform(click());

        */
/**
         * INITIALISATION
         *//*


        */
/*Les checkbox fondement et methode sont visibles et cliquable*//*

        onView(withId(R.id.radio_fondement)).check(matches(isDisplayed()));
        onView(withId(R.id.radio_methode)).check(matches(isDisplayed()));
        onView(withId(R.id.radio_fondement)).check(matches(isEnabled()));
        onView(withId(R.id.radio_methode)).check(matches(isEnabled()));

        */
/*Les checkbox enjeux et competence sont visibles et incliquable*//*

        onView(withId(R.id.checkbox_competence)).check(matches(isDisplayed()));
        onView(withId(R.id.checkbox_enjeux)).check(matches(isDisplayed()));
        onView(withId(R.id.checkbox_competence)).check(matches(not(isEnabled())));
        onView(withId(R.id.checkbox_enjeux)).check(matches(not(isEnabled())));

        //Les expensions ne sont pas visible
        onView(withId(R.id.expansionLayout)).check(matches(not(isDisplayed())));
        onView(withId(R.id.expansionLayout2)).check(matches(not(isDisplayed())));

        //Enregistré n'est pas cliquable mais visible
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));
        onView(withId(R.id.Enregistrer)).check(matches(isDisplayed()));


        */
/**
         * lORSQU'ON COCHE LA CHECKBOX FONDEMENT
         *//*


        //On clique sur checkbox fondement
        onView(withId(R.id.radio_fondement)).perform(click());

        //La checkbox methode n'est pas cliquable
        onView(withId(R.id.radio_methode)).check(matches(not(isEnabled())));

        //Le bouton methode n'est pas cliquable
        onView(withId(R.id.bouton_methode)).check(matches(not(isEnabled())));

        //L'accordeon de fondement ouvre
        onView(withId(R.id.expansionLayout)).check(matches(isDisplayed()));

        //Enregistrer est cliquable
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));

        */
/**
         * lORSQU'ON DECOCHE LA CHECKBOX FONDEMENT
         *//*


        //On clique sur checkbox fondement
        onView(withId(R.id.radio_fondement)).perform(click());

        //La checkbox methode est cliquable
        onView(withId(R.id.radio_methode)).check(matches(isEnabled()));

        //L'accordeon de fondement ferme
        onView(withId(R.id.expansionLayout)).check(matches(not(isDisplayed())));

        //La checkbox fondement est déchoché
        onView(withId(R.id.radio_fondement)).check(matches(not(isChecked())));

        //Enregistré n'est pas cliquable
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));



        */
/**
         * lORSQU'ON CLIQUE LA BOUTON FONDEMENT
         *//*


        //On clique sur fondement
        onView(withId(R.id.bouton_fondement)).perform(click());

        //La checkbox fondement est checké
        onView(withId(R.id.radio_fondement)).check(matches(isChecked()));

        //La checkbox methode n'est pas cliquable
        onView(withId(R.id.radio_methode)).check(matches(not(isEnabled())));

        //Le bouton methode n'est pas cliquable
        onView(withId(R.id.bouton_methode)).check(matches(not(isEnabled())));

        //L'accordeon de fondement ouvre
        onView(withId(R.id.expansionLayout)).check(matches(isDisplayed()));

        //Enregistrer est cliquable
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));


        */
/**
         * lORSQU'ON RECLIQUE LA BOUTON FONDEMENT
         *//*


        //On clique sur fondement
        onView(withId(R.id.bouton_fondement)).perform(click());

        //La checkbox fondement n'est pas checké
        onView(withId(R.id.radio_fondement)).check(matches(not(isChecked())));

        //La checkbox methode est cliquable
        onView(withId(R.id.radio_methode)).check(matches(isEnabled()));

        //Le bouton methode est cliquable
        onView(withId(R.id.bouton_methode)).check(matches(isEnabled()));

        //L'accordeon de fondement ferme
        onView(withId(R.id.expansionLayout)).check(matches(not(isDisplayed())));

        //Enregistré n'est pas cliquable
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));




        //--------------------------------------------------------------------

        */
/**
         * lORSQU'ON COCHE LA CHECKBOX METHODE
         *//*


        //On clique sur checkbox methode
        onView(withId(R.id.radio_methode)).perform(click());

        //La checkbox fondement n'est pas cliquable
        onView(withId(R.id.radio_fondement)).check(matches(not(isEnabled())));

        //Le bouton fondement n'est pas cliquable
        onView(withId(R.id.bouton_fondement)).check(matches(not(isEnabled())));

        //L'accordeon de methode ouvre
        onView(withId(R.id.expansionLayout2)).check(matches(isDisplayed()));

        //Enregistrer est cliquable
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));

        */
/**
         * lORSQU'ON DECOCHE LA CHECKBOX FONDEMENT
         *//*


        //On clique sur checkbox methode
        onView(withId(R.id.radio_methode)).perform(click());

        //La checkbox fondement est cliquable
        onView(withId(R.id.radio_fondement)).check(matches(isEnabled()));

        //L'accordeon de methode ferme
        onView(withId(R.id.expansionLayout2)).check(matches(not(isDisplayed())));

        //La checkbox methode est déchoché
        onView(withId(R.id.radio_methode)).check(matches(not(isChecked())));

        //Enregistré n'est pas cliquable
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));



        */
/**
         * lORSQU'ON CLIQUE LA BOUTON METHODE
         *//*


        //On clique sur methode
        onView(withId(R.id.bouton_methode)).perform(click());

        //La checkbox methode est checké
        onView(withId(R.id.radio_methode)).check(matches(isChecked()));

        //La checkbox fondement n'est pas cliquable
        onView(withId(R.id.radio_fondement)).check(matches(not(isEnabled())));

        //Le bouton fondement n'est pas cliquable
        onView(withId(R.id.bouton_fondement)).check(matches(not(isEnabled())));

        //L'accordeon de methode ouvre
        onView(withId(R.id.expansionLayout2)).check(matches(isDisplayed()));

        //Enregistrer est cliquable
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));


        */
/**
         * lORSQU'ON RECLIQUE LA BOUTON METHODE
         *//*


        //On clique sur METHODE
        onView(withId(R.id.bouton_methode)).perform(click());

        //La checkbox methode n'est pas checké
        onView(withId(R.id.radio_methode)).check(matches(not(isChecked())));

        //La checkbox fondement est cliquable
        onView(withId(R.id.radio_fondement)).check(matches(isEnabled()));

        //Le bouton fondement est cliquable
        onView(withId(R.id.bouton_fondement)).check(matches(isEnabled()));

        //L'accordeon de methode ferme
        onView(withId(R.id.expansionLayout2)).check(matches(not(isDisplayed())));

        //Enregistré n'est pas cliquable
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));


        //------------------------------------------------------


        */
/**
         * lORSQU'ON COCHE LA CHECKBOX FONDEMENT
         *//*


        //On clique sur checkbox fondement
        onView(withId(R.id.radio_fondement)).perform(click());

        //La checkbox methode n'est pas cliquable
        onView(withId(R.id.radio_methode)).check(matches(not(isEnabled())));

        //Le bouton methode n'est pas cliquable
        onView(withId(R.id.bouton_methode)).check(matches(not(isEnabled())));

        //L'accordeon de fondement ouvre
        onView(withId(R.id.expansionLayout)).check(matches(isDisplayed()));

        //Enregistrer est cliquable
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));

        */
/**
         * lORSQU'ON CLIQUE SUR BOUTON FONDEMENT
         *//*


        //On clique sur fondement
        onView(withId(R.id.bouton_fondement)).perform(click());

        //La checkbox fondement n'est pas checké
        onView(withId(R.id.radio_fondement)).check(matches(not(isChecked())));

        //La checkbox methode est cliquable
        onView(withId(R.id.radio_methode)).check(matches(isEnabled()));

        //Le bouton methode est cliquable
        onView(withId(R.id.bouton_methode)).check(matches(isEnabled()));

        //L'accordeon de methode ferme
        onView(withId(R.id.expansionLayout2)).check(matches(not(isDisplayed())));

        //Enregistré n'est pas cliquable
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));



        */
/**
         * lORSQU'ON CLIQUE LA BOUTON FONDEMENT
         *//*


        //On clique sur fondement
        onView(withId(R.id.bouton_fondement)).perform(click());

        //La checkbox fondement est checké
        onView(withId(R.id.radio_fondement)).check(matches(isChecked()));

        //La checkbox methode n'est pas cliquable
        onView(withId(R.id.radio_methode)).check(matches(not(isEnabled())));

        //Le bouton methode n'est pas cliquable
        onView(withId(R.id.bouton_methode)).check(matches(not(isEnabled())));

        //L'accordeon de fondement ouvre
        onView(withId(R.id.expansionLayout)).check(matches(isDisplayed()));

        //Enregistrer est cliquable
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));


        */
/**
         * lORSQU'ON DECOCHE LA CHECKBOX FONDEMENT
         *//*


        //On clique sur checkbox fondement
        onView(withId(R.id.radio_fondement)).perform(click());

        //La checkbox methode est cliquable
        onView(withId(R.id.radio_methode)).check(matches(isEnabled()));

        //L'accordeon de fondement ferme
        onView(withId(R.id.expansionLayout)).check(matches(not(isDisplayed())));

        //La checkbox fondement est déchoché
        onView(withId(R.id.radio_fondement)).check(matches(not(isChecked())));

        //Enregistré n'est pas cliquable
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));



        //------------------------------------------------------------------------------


        */
/**
         * lORSQU'ON COCHE LA CHECKBOX METHODE
         *//*


        //On clique sur checkbox methode
        onView(withId(R.id.radio_methode)).perform(click());

        //La checkbox fondement n'est pas cliquable
        onView(withId(R.id.radio_fondement)).check(matches(not(isEnabled())));

        //Le bouton fondement n'est pas cliquable
        onView(withId(R.id.bouton_fondement)).check(matches(not(isEnabled())));

        //L'accordeon de methode ouvre
        onView(withId(R.id.expansionLayout2)).check(matches(isDisplayed()));

        //Enregistrer est cliquable
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));


        */
/**
         * lORSQU'ON RECLIQUE LA BOUTON METHODE
         *//*


        //On clique sur METHODE
        onView(withId(R.id.bouton_methode)).perform(click());

        //La checkbox methode n'est pas checké
        onView(withId(R.id.radio_methode)).check(matches(not(isChecked())));

        //La checkbox fondement est cliquable
        onView(withId(R.id.radio_fondement)).check(matches(isEnabled()));

        //Le bouton fondement est cliquable
        onView(withId(R.id.bouton_fondement)).check(matches(isEnabled()));

        //L'accordeon de methode ferme
        onView(withId(R.id.expansionLayout2)).check(matches(not(isDisplayed())));

        //Enregistré n'est pas cliquable
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));



        */
/**
         * lORSQU'ON CLIQUE LA BOUTON METHODE
         *//*


        //On clique sur methode
        onView(withId(R.id.bouton_methode)).perform(click());

        //La checkbox methode est checké
        onView(withId(R.id.radio_methode)).check(matches(isChecked()));

        //La checkbox fondement n'est pas cliquable
        onView(withId(R.id.radio_fondement)).check(matches(not(isEnabled())));

        //Le bouton fondement n'est pas cliquable
        onView(withId(R.id.bouton_fondement)).check(matches(not(isEnabled())));

        //L'accordeon de methode ouvre
        onView(withId(R.id.expansionLayout2)).check(matches(isDisplayed()));

        //Enregistrer est cliquable
        onView(withId(R.id.Enregistrer)).check(matches(isEnabled()));


        */
/**
         * lORSQU'ON DECOCHE LA CHECKBOX FONDEMENT
         *//*


        //On clique sur checkbox methode
        onView(withId(R.id.radio_methode)).perform(click());

        //La checkbox fondement est cliquable
        onView(withId(R.id.radio_fondement)).check(matches(isEnabled()));

        //L'accordeon de methode ferme
        onView(withId(R.id.expansionLayout2)).check(matches(not(isDisplayed())));

        //La checkbox methode est déchoché
        onView(withId(R.id.radio_methode)).check(matches(not(isChecked())));

        //Enregistré n'est pas cliquable
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));


    }
 */
/*   @Test
    public void testToucheEnregistrer() {
        Intent startIntent = new Intent();
        mActivityRule.launchActivity(startIntent);
        MockitoAnnotations.initMocks(this);


        onView(withId(R.id.button)).perform(click());


        *//*
*/
/*Le Bouton est normalent invisible avant le clique sur une checkbox
         *//*
*/
/*
        onView(withId(R.id.Enregistrer)).check(matches((isDisplayed())));

        *//*
*/
/*On clique sur le bouton semestre 1
         *//*
*/
/*

        onView(withId(R.id.BoutonSemestre)).perform(click());


        // on Vérifie que le bouton enregistrer visible et non clickable
        onView(withId(R.id.Enregistrer)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.Enregistrer)).check(matches(not(isEnabled())));

        *//*
*/
/*Selection de la première checkbox
         *//*
*/
/*
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


    }*//*

}
*/
