package com.example.plpla;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import user.User;

public class MainActivity extends AppCompatActivity {
    /**
     * Splash screen, le message d'acceuil au lancement de l'app
     * 1000 millisecondes par défaut (1 seconde)
     * */
    private static int TEMPS_MESSAGE_ACCEUIL = 600;
    private String ipAddress;
    private Button nextActivity;
    private EditText ipAddressUsr;

    /*L'objet client qui existe pendant toute l'application*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextActivity = findViewById(R.id.button);
        ipAddressUsr = findViewById(R.id.editText);

        ipAddressUsr.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});


        /*Passe au Fragment Home (L'activity MainNavigation) */
        passeAHome();
    }


    private void passeAHome(){

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(homeIntent);
//                finish();
//            }
//        }, TEMPS_MESSAGE_ACCEUIL);

        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipAddress = "http://"+ipAddressUsr.getText()+":4444";
                ((Client)getApplicationContext()).getUniqueConnexion().setServerAddress(ipAddress);
                ((Client)getApplicationContext()).getUniqueConnexion().initConnexion();
                //@TODO Ajouter dans le layout des edittext ou autre pour rentrer ses champs :
                ((Client)getApplicationContext()).getUser().setNom("Baroudi");
                ((Client)getApplicationContext()).getUser().setPrenom("Ibrahim");
                //-------------------------------------------------------------------
                ((Client)getApplicationContext()).getUniqueConnexion().connecte();

                ((Client)getApplicationContext()).getUniqueConnexion().getmSocket().on(EVENT.ADD_USER, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        String ip_add = (String) args[0];
                        ((Client)getApplicationContext()).getUser().setAddress_ip(ip_add);
                    }
                });

                ((Client)getApplicationContext()).getUniqueConnexion().getmSocket().on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), R.string.connect, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                Intent homeIntent = new Intent(MainActivity.this, MainNavigation.class);
                /*Passer la variable ipAddress au Fragment Home (L'activity MainNavigation) */
                homeIntent.putExtra("url", ipAddress);
                startActivity(homeIntent);
                finish();
            }
        });

    }



}
