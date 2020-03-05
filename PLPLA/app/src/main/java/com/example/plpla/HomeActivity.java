package com.example.plpla;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.controleur.ListenerCheckBox;
import com.example.plpla.vue.Vue;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class HomeActivity extends Activity implements Vue {

    private Button bouton;
    private String serverAdress;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private TextView textView1;
    private TextView textView2;
    private Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
            bouton = findViewById(R.id.BoutonSemestre);
            checkBox1 = findViewById(R.id.checkBoxEmplacement1);
            checkBox2 = findViewById(R.id.checkBoxEmplacement2);
            textView1 = findViewById(R.id.emplacement1S1);
            textView2 = findViewById(R.id.emplacement2S1);
            ListenerButton listenerButton = new ListenerButton(socket,this);
            ListenerCheckBox listenerCheckBox = new ListenerCheckBox(socket, this);
            bouton.setOnClickListener(listenerButton);
            checkBox1.setOnClickListener(listenerCheckBox);
            checkBox2.setOnClickListener(listenerCheckBox);
            socket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public CheckBox getCheckBox1() {
        return checkBox1;
    }

    public CheckBox getCheckBox2() {
        return checkBox2;
    }

    public TextView getTextView1() {
        return textView1;
    }

    public TextView getTextView2() {
        return textView2;
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
