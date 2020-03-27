package com.example.plpla;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.controleur.ListenerCheckBox;
import com.example.plpla.ui.home.HomeFragment;
import com.example.plpla.vue.Vue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;

import io.socket.client.IO;
import io.socket.client.Socket;

public class CourseActivity extends AppCompatActivity implements Vue {


    private String serverAdress;

    private TextView monParcours;
    private Socket socket;
    private Button reinitialiser;
    private static final String FILE_NAME = "parcours.txt";
    private TextView finalText;
    private TextView parcoursVide;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Bundle extras = getIntent().getExtras();

        /*Get the value of the ipAddress from the mainActivity*/
        serverAdress = extras.getString("url");
        Log.d("SERVEUR", "Adresse du serveur :"+serverAdress);
        socket = null;
        try {
            /*Si vous utilisez l'emulateur, utilisez la ligne suivante*/
            socket = IO.socket("http://10.0.2.2:4444");
            /*Sinon remplacez par l'addresse IP de votre serveur (votre pc normalement)*/
            //socket = IO.socket("http://192.168.0.23:4444");
            //socket = IO.socket(serverAdress);

            monParcours = findViewById(R.id.monParcours);
            parcoursVide = findViewById(R.id.parcours_vide);
            finalText = (TextView) findViewById(R.id.parcours_final);
            reinitialiser = findViewById(R.id.reinitialiser_button);

            //VISIBILITE PAR DEFAUT
            finalText.setVisibility(View.INVISIBLE);
            parcoursVide.setVisibility(View.VISIBLE);
            reinitialiser.setEnabled(false);

            //Afficher le parcours enregistré
            if (!HomeFragment.getSelectionUE().isEmpty()) {
                reinitialiser.setEnabled(true);
                parcoursVide.setVisibility(View.INVISIBLE);

                //Lecture du fichier enregistré
                String parcours;
                FileInputStream fichierLecture = openFileInput("mon_parcours");
                InputStreamReader lecteur = new InputStreamReader(fichierLecture);
                BufferedReader bfr = new BufferedReader(lecteur);
                StringBuffer stringBuffer = new StringBuffer();
                while ((parcours = bfr.readLine()) != null){
                    stringBuffer.append(parcours + "\n");
                }

                //On affiche la lecture
                finalText.setText(stringBuffer.toString());
                finalText.setVisibility(View.VISIBLE);
            }

            //LORSQUE JE CLIQUE SUR REINITIALISER
            reinitialiser.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finalText.setVisibility(View.INVISIBLE);
                    parcoursVide.setVisibility(View.VISIBLE);
                    finalText.setText("");
                    HomeFragment.getSelectionUE().clear();
                    Toast.makeText(CourseActivity.this, "Parcours réinitialisé", Toast.LENGTH_LONG).show();
                    reinitialiser.setEnabled(false);
                }
            });


            socket.connect();


        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        bouton = findViewById(R.id.BoutonSemestre);
//        socket = null;
//        final ListenerButton listenerButton = new ListenerButton(socket, this);
//        bouton.setOnClickListener(listenerButton);
//    }

}
