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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.plpla.Client;
import com.example.plpla.CourseActivity;
import com.example.plpla.R;
import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.controleur.ListenerCheckBox;
import com.github.florent37.expansionpanel.ExpansionHeader;
import com.github.florent37.expansionpanel.ExpansionLayout;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

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
    private ExpansionHeader boutonMethode;
    private ExpansionHeader boutonFondement;

    public static ArrayList<String> getSelectionItem() {
        return selectionItem;
    }

    private static ArrayList<String> selectionItem = new ArrayList<>();
    private ArrayList<String> selectionCode = new ArrayList<>();

    public ArrayList<String> getSelectionCode() {
        return selectionCode;
    }

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
        boutonMethode = root.findViewById(R.id.bouton_methode);
        boutonFondement = root.findViewById(R.id.bouton_fondement);

        /*Par défaut Enregistrer n'est pas cliquable*/
        Enregistrer.setClickable(false);
        Enregistrer.setEnabled(false);

        ((Client)getActivity().getApplicationContext()).getUniqueConnexion().getmSocket().on("Saved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), R.string.saved_on_server, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


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
                    boutonMethode.setEnabled(false);
                    Enregistrer.setEnabled(true);
                    selectionItem.add(textFondement.getText().toString());
                    selectionCode.add("SPUM14");
                    }
                else {
                    radioFondement.setChecked(false);
                    radioMethode.setEnabled(true);
                    boutonMethode.setEnabled(true);
                    Enregistrer.setEnabled(false);
                    selectionItem.remove(textFondement.getText().toString());
                    selectionCode.remove("SPUM14");
                }
                }});

        expansionMethode.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                Log.d("Bouton enregistrer", "Parcours enregistre");
                if ((radioMethode.isChecked() && expansionMethode.isExpanded()) || (!radioMethode.isChecked() && expansionMethode.isExpanded())){
                    radioMethode.setChecked(true);
                    radioFondement.setEnabled(false);
                    boutonFondement.setEnabled(false);
                    Enregistrer.setEnabled(true);
                    selectionItem.add(textMethode.getText().toString());
                    selectionCode.add("SPUM12");
                }
                else {
                    radioMethode.setChecked(false);
                    radioFondement.setEnabled(true);
                    boutonFondement.setEnabled(true);
                    Enregistrer.setEnabled(false);
                    selectionItem.remove(textMethode.getText().toString());
                    selectionCode.remove("SPUM12");
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