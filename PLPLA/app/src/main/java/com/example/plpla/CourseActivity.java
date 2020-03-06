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
    private static final String FILE_NAME = "parcours.txt";
    TextView final_text;




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

            final_text = (TextView) findViewById(R.id.parcours_final);
            String final_selection = "";
            for (String selections : HomeActivity.getSelectionItem()) {
                final_selection += selections + "\n";
            }
            final_text.setText(final_selection);
            socket.connect();


        } catch (URISyntaxException e) {
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
