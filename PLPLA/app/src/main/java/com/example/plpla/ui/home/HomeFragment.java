package com.example.plpla.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.plpla.CourseActivity;
import com.example.plpla.R;
import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.controleur.ListenerCheckBox;
import com.github.florent37.expansionpanel.ExpansionLayout;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private String serverAdress;
    private CheckBox radioFondement;
    private CheckBox radioMethode;
    private TextView textFondement;
    private TextView textMethode;
    private TextView textEnjeux;
    private TextView textCompetence;
    private Socket socket;
    private Button Enregistrer ;
    private ExpansionLayout expansionFondement;
    private ExpansionLayout expansionMethode;

    public static ArrayList<String> getSelectionItem() {
        return selectionItem;
    }

    private static ArrayList<String> selectionItem = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        radioFondement = root.findViewById(R.id.radio_fondement);
        radioMethode = root.findViewById(R.id.radio_methode);
        textFondement = root.findViewById(R.id.text_fondement);
        textMethode = root.findViewById(R.id.text_methode);
        textEnjeux = root.findViewById(R.id.text_enjeux);
        textCompetence = root.findViewById(R.id.text_competence);
        expansionFondement = root.findViewById(R.id.expansionLayout);
        expansionMethode = root.findViewById(R.id.expansionLayout2);
        Enregistrer = root.findViewById(R.id.Enregistrer);

        /*Par défaut Enregistrer n'est pas cliquable*/
        Enregistrer.setClickable(false);
        Enregistrer.setEnabled(false);


        ListenerButton listenerButton = new ListenerButton(socket,this);
        ListenerCheckBox listenerCheckBox = new ListenerCheckBox(socket, this);
        radioFondement.setOnClickListener(listenerCheckBox);
        radioMethode.setOnClickListener(listenerCheckBox);
        expansionFondement.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                Log.d("Bouton enregistrer", "Parcours enregistre");
                if ((radioFondement.isChecked() && expansionFondement.isExpanded()) || (!radioFondement.isChecked() && expansionFondement.isExpanded())){
                    radioFondement.setChecked(true);
                    radioMethode.setEnabled(false);
                    expansionMethode.setEnabled(false);
                    selectionItem.add(textFondement.getText().toString());
                    }
                else {
                    radioFondement.setChecked(false);
                    radioMethode.setEnabled(true);
                    expansionMethode.setEnabled(true);
                    selectionItem.remove(textFondement.getText().toString());
                    }
                }});

        expansionMethode.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                Log.d("Bouton enregistrer", "Parcours enregistre");
                if ((radioMethode.isChecked() && expansionMethode.isExpanded()) || (!radioMethode.isChecked() && expansionMethode.isExpanded())){
                    radioMethode.setChecked(true);
                    radioFondement.setEnabled(false);
                    expansionFondement.setEnabled(false);
                    selectionItem.add(textMethode.getText().toString());
                    }
                else {
                    radioMethode.setChecked(false);
                    radioFondement.setEnabled(true);
                    expansionFondement.setEnabled(true);
                    selectionItem.remove(textMethode.getText().toString());
                    }
                }});
        Enregistrer.setOnClickListener(listenerButton);
        return root;
    }




    public TextView getTextEnjeux() {
        return textEnjeux;
    }

    public TextView getTextCompetence() {
        return textCompetence;
    }

    public ExpansionLayout getExpansionFondement() {
        return expansionFondement;
    }

    public ExpansionLayout getExpansionMethode() {
        return expansionMethode;
    }

    public CheckBox getRadioFondement() {
        return radioFondement;
    }

    public CheckBox getRadioMethode() {
        return radioMethode;
    }

    public TextView getTextFondement() {
        return textFondement;
    }

    public TextView getTextMethode() {
        return textMethode;
    }

    public Button getEnregistrer() {
        return Enregistrer;
    }


}