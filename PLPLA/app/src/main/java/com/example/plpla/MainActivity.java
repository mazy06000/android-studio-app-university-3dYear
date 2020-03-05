package com.example.plpla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    /**
     * Splash screen, le message d'acceuil au lancement de l'app
     * 1000 millisecondes par défaut (1 seconde)
     * */
    private static int TEMPS_MESSAGE_ACCEUIL = 600;
    private String ipAddress;
    private Button nextActivity;
    private EditText ipAddressUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextActivity = findViewById(R.id.button);
        ipAddressUsr = findViewById(R.id.editText);

        ipAddressUsr.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});
        /*Passe à l'activity Home*/
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
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                /*Passer la variable ipAddress à l'activity Home*/
                homeIntent.putExtra("url", ipAddress);
                startActivity(homeIntent);
                finish();
            }
        });

    }



}
