package com.example.plpla.controleur;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.plpla.HomeActivity;
import com.example.plpla.R;
import com.example.plpla.vue.Vue;

import io.socket.client.Socket;

public class ListenerButton implements View.OnClickListener{

    private final Socket socket;
    private HomeActivity activity;

    public ListenerButton(Socket socket, HomeActivity activity) {
        this.socket = socket;
        this.activity = activity;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BoutonSemestre:
                Log.d("Bouton semestre", "Pression sur le bouton semestre");
                activity.getCheckBox1().setVisibility(View.VISIBLE);
                activity.getCheckBox2().setVisibility(View.VISIBLE);
                activity.getTextView1().setVisibility(View.VISIBLE);
                activity.getTextView2().setVisibility(View.VISIBLE);
                /*Le serveur ne semble pas recevoir l'event*/
                socket.emit("touche");
                break;


            case R.id.checkBoxEmplacement1:

                break;


            case R.id.checkBoxEmplacement2 :
                break;
        }
    }
}