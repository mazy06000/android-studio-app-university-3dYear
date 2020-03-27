package com.example.plpla;

import android.os.Bundle;

import com.example.plpla.ui.home.PortailFragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import matière.UE;
import user.User;

public class MainNavigation extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        PortailFragment portailFragment = new PortailFragment();
        portailFragment.setArguments(extras);

        User baroudi = new User();
        baroudi.setNom("BAR");
        baroudi.setPrenom("OU");
        baroudi.setAddress_ip("DI");
        baroudi.setListe_choix(new ArrayList<UE>());

        Log.d("EVENT_SOCKET", "envoie de l'utilisateur "+ ((Client)getApplicationContext()).getUser().getNom() +" au serveur");
        Log.d("USER EVENT", "object baroudi : "+baroudi.getNom()+ " "+ baroudi.getPrenom());
        ((Client)getApplicationContext()).getUniqueConnexion().getmSocket().on(EVENT.ADD_USER, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String ip_add = (String) args[0];
                ((Client)getApplicationContext()).getUser().setAddress_ip(ip_add);
                Log.d("EVENT_SOCKET", "ADD_USER : Reception du server de l'addresse ip de "+ ((Client)getApplicationContext()).getUser().getNom() +
                        " --> " + ((Client)getApplicationContext()).getUser().getAddress_ip());
            }
        });

        ((Client)getApplicationContext()).getUniqueConnexion().getmSocket().emit(EVENT.ADD_USER, baroudi);




        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         */


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_tools)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
