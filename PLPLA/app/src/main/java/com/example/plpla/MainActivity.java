package com.example.plpla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    /**
     * Splash screen, le message d'acceuil au lancement de l'app
     * 1000 millisecondes par défaut (1 seconde)
     * */
    private static int TEMPS_MESSAGE_ACCEUIL = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Passe à l'activity Home*/
        passeAHome();
    }

    private void passeAHome(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, TEMPS_MESSAGE_ACCEUIL);
    }
}
