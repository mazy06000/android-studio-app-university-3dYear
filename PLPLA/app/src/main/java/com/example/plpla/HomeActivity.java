package com.example.plpla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.plpla.controleur.ListenerButton;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class HomeActivity extends AppCompatActivity {

    private Button bouton;
    private Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        Socket mSocket = null;
        try {
            mSocket = IO.socket("http://10.0.2.2:4444");
            bouton = findViewById(R.id.BoutonSemestre);
            ListenerButton listenerButton = new ListenerButton(socket);
            bouton.setOnClickListener(listenerButton);
            mSocket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
