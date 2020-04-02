package com.example.plpla.controleur;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.plpla.Client;
import com.example.plpla.R;
import com.example.plpla.ui.home.PortailFragment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.socket.client.Socket;

import static android.content.Context.MODE_PRIVATE;

public class ListenerButton implements View.OnClickListener{

    private final Socket socket;
    private PortailFragment activity;
    private int compteurTouche = 0;

    public ListenerButton(Socket socket, PortailFragment activity) {
        this.socket = socket;
        this.activity = activity;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.boutonEnregistrer:
                Log.d("Bouton enregistrer", "Parcours enregistre");
                //activity.getSelectionItem().add(activity.getTextEnjeux().getText().toString());
                //activity.getSelectionItem().add(activity.getTextCompetence().getText().toString());
                String fileName = "mon_parcours_S1";
                String final_selection = "SEMESTRE 1\n";
                for (String selections : activity.getSelectionUE()){
                    Log.d("WRITEFILE", "ecriture de "+activity.getSelectionUE().toString());
                    final_selection += selections + "\n";
                    Log.d("WRITEFILE", "Valeur de final_selection "+final_selection);

                }
                try {
                    FileOutputStream ecriture = activity.getActivity().openFileOutput(fileName, MODE_PRIVATE);
                    ecriture.write(final_selection.getBytes());
                    ecriture.close();
                    Toast.makeText(activity.getActivity(), "Parcours enregistré", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (String code_ue: activity.getSelectionCode()) {
                    Log.d("SAVE_SERVER", "Envoie de la matière de code "+code_ue+ " au serveur pour enregistrement");
                    ((Client)activity.getActivity().getApplicationContext()).getUniqueConnexion().getmSocket().emit("Save", code_ue);
                }
                break;




        }
    }


}