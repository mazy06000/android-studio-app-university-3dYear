package com.example.plpla;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.view.MenuItem;
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
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;

public class HomeActivity extends AppCompatActivity implements Vue {

    private Button bouton;
    private String serverAdress;
    private CheckBox checkBox1;

    private Button parcours;
    private CheckBox checkBox2;
    private TextView textView1;
    private TextView textView2;
    private TextView accordeon;
    private Socket socket;
    private Button Enregistrer ;

    public static ArrayList<String> getSelectionItem() {
        return selectionItem;
    }

    private static ArrayList<String> selectionItem = new ArrayList<>();


    /*
    private DrawerLayout mrDrawerLayout ;
    private ActionBarDrawerToggle mToggle ;
    private Toolbar mToolbar ;
     */



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
            accordeon = findViewById(R.id.accordeonsPlus);
            Enregistrer = findViewById(R.id.Enregistrer);
            //parcours = findViewById(R.id.parcours_button);





            // Toolbar marche pas encore :
            /*
            mToolbar = findViewById(R.id.nav_action);
            setSupportActionBar(mToolbar);
            mrDrawerLayout = findViewById(R.id.drawerLayout);
            mToggle = new ActionBarDrawerToggle(this, mrDrawerLayout, R.string.open , R.string.close);

            mrDrawerLayout.addDrawerListener(mToggle);
            mToggle.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
             */


            ListenerButton listenerButton = new ListenerButton(socket,this);
            ListenerCheckBox listenerCheckBox = new ListenerCheckBox(socket, this);
            bouton.setOnClickListener(listenerButton);
            checkBox1.setOnClickListener(listenerCheckBox);
            checkBox2.setOnClickListener(listenerCheckBox);
            Enregistrer.setOnClickListener(listenerButton);
            /*parcours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent homeIntent = new Intent(HomeActivity.this, CourseActivity.class);
                    startActivity(homeIntent);
                }
            });*/
            socket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }




/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;

        }

        return super.onOptionsItemSelected(item);
    }*/





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

    public TextView getAccordeon() {
        return accordeon;
    }

    public Button getEnregistrer() {
        return Enregistrer;
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
