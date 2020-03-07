package com.example.plpla.controleur;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.example.plpla.CourseActivity;
import com.example.plpla.HomeActivity;
import com.example.plpla.R;
import com.example.plpla.vue.Vue;

import io.socket.client.Socket;

public class ListenerButton implements View.OnClickListener{

    private final Socket socket;
    private HomeActivity activity;
    private int compteurTouche = 0;

    public ListenerButton(Socket socket, HomeActivity activity) {
        this.socket = socket;
        this.activity = activity;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BoutonSemestre:
                compteurTouche++;
                Log.d("Bouton semestre", "Pression sur le bouton semestre");
                activity.getEnregistrer().setEnabled(false);
                if (compteurTouche%2 != 0) {
                    activity.getCheckBox1().setVisibility(View.VISIBLE);
                    activity.getCheckBox2().setVisibility(View.VISIBLE);
                    activity.getTextView1().setVisibility(View.VISIBLE);
                    activity.getTextView2().setVisibility(View.VISIBLE);
                    activity.getEnregistrer().setVisibility(View.VISIBLE);
                    activity.getAccordeon().setText(R.string.deroulementMoins);
                    /*Le serveur ne semble pas recevoir l'event*/
                    socket.emit("touche");
                }
                else {
                    activity.getCheckBox1().setVisibility(View.INVISIBLE);
                    activity.getCheckBox2().setVisibility(View.INVISIBLE);
                    activity.getTextView1().setVisibility(View.INVISIBLE);
                    activity.getTextView2().setVisibility(View.INVISIBLE);
                    activity.getEnregistrer().setVisibility(View.INVISIBLE);
                    activity.getAccordeon().setText(R.string.deroulementPlus);
                }
                break;
            case R.id.Enregistrer:
                Log.d("Bouton enregistrer", "Parcours enregistre");
                Toast.makeText(activity, "Parcours enregistré", Toast.LENGTH_LONG).show();


        }
    }


}