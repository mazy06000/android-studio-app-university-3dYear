package com.example.plpla.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
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
import com.example.plpla.ComposantPortail;
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

    //COMPOSANT DU LAYOUT

    private ComposantPortail blocFondement, blocMethode, choixGeo, ueGeo1, ueGeo2, ueDisciplinaire1, choixInfo,
            ueWeb, ueBase, choixFondementS1,  ueComplement, ueMethode, choixSV, ueGenetique, ueOrganique,
            ueChimieS1, ueElectroniqueS1, ueMIASH, choixEcueMIASH, uePhysique, ueTerre;






    //BOUTONS
    private Button Enregistrer ;


    //TEXTS
    private String serverAdress;

    //CONNEXION
    private Socket socket;
    private TextView textEnjeux;
    private TextView textCompetence;


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


        blocFondement = new ComposantPortail(root.findViewById(R.id.bouton_fondement),root.findViewById(R.id.expansionLayout),
                root.findViewById(R.id.text_fondement),root.findViewById(R.id.radio_fondement),null);
        blocMethode = new ComposantPortail(root.findViewById(R.id.bouton_methode),root.findViewById(R.id.expansionLayout2),
                root.findViewById(R.id.text_methode),root.findViewById(R.id.radio_methode),null);
        choixGeo = new ComposantPortail(root.findViewById(R.id.bouton_geo1),root.findViewById(R.id.expansionGeo),
                root.findViewById(R.id.text_geo), null, root.findViewById(R.id.headerIndicator));
        ueGeo1 = new ComposantPortail(root.findViewById(R.id.bouton_geo1),null,
                root.findViewById(R.id.text_geo1),root.findViewById(R.id.checkbox_geo1),null);
        ueGeo2 = new ComposantPortail(root.findViewById(R.id.bouton_geo2),null,
                root.findViewById(R.id.text_geo2),root.findViewById(R.id.checkbox_geo2),null);
        ueDisciplinaire1 = new ComposantPortail();
        choixInfo = new ComposantPortail();
        ueWeb = new ComposantPortail();
        ueBase = new ComposantPortail();
        choixFondementS1 = new ComposantPortail();
        ueComplement = new ComposantPortail();
        ueMethode = new ComposantPortail();
        choixSV = new ComposantPortail();
        ueGenetique = new ComposantPortail();
        ueOrganique = new ComposantPortail();
        ueChimieS1 = new ComposantPortail();
        ueElectroniqueS1 = new ComposantPortail();
        ueMIASH = new ComposantPortail();
        choixEcueMIASH = new ComposantPortail();
        uePhysique = new ComposantPortail();
        ueTerre = new ComposantPortail();


        //BOUTONS
        Enregistrer = root.findViewById(R.id.Enregistrer);


        //TEXTS
        textEnjeux = root.findViewById(R.id.text_enjeux);
        textCompetence = root.findViewById(R.id.text_competence);


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

        //LISTENER
        radioFondement.setOnClickListener(listenerCheckBox);
        radioMethode.setOnClickListener(listenerCheckBox);
        checkboxGeo1.setOnClickListener(listenerCheckBox);


        boutonGeo1.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                Log.d("Bouton enregistrer", "Parcours enregistre");
                if (checkboxGeo1.isChecked()){
                    Enregistrer.setEnabled(true);
                    selectionItem.add(.getText().toString());
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


        expansionGeo.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {
                Log.d("Bouton enregistrer", "Parcours enregistre");
                if (expansionGeo.isExpanded()) indicatorHeader.setRotation(90);
                else indicatorHeader.setRotation(0);
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