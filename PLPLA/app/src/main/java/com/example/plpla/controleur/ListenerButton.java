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
    private int compteurToucheCHimie = 0;

    public ListenerButton(Socket socket, PortailFragment activity) {
        this.socket = socket;
        this.activity = activity;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
<<<<<<< HEAD
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
                    activity.getChimieS1().setVisibility(View.VISIBLE);



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
                    activity.getChimieS1().setVisibility(View.INVISIBLE);

                }
                break;
            case R.id.chimieS1:
                compteurToucheCHimie++;
                Log.d("Bouton semestre", "Pression sur le bouton ChimieS1");
                activity.getEnregistrer().setEnabled(false);
                if (compteurTouche%2 != 0) {

                    activity.getStructMicro().setVisibility(View.VISIBLE);
                    activity.getCheckBoxStructMicro().setVisibility(View.VISIBLE);
                }
                else
                {
                    activity.getStructMicro().setVisibility(View.INVISIBLE);
                    activity.getCheckBoxStructMicro().setVisibility(View.INVISIBLE);
                }
=======
//            case R.id.BoutonSemestre:
//                compteurTouche++;
//                Log.d("Bouton semestre", "Pression sur le bouton semestre");
//                activity.getEnregistrer().setEnabled(false);
//                if (compteurTouche%2 != 0) {
//                    activity.getCheckBox1().setVisibility(View.VISIBLE);
//                    activity.getCheckBox2().setVisibility(View.VISIBLE);
//                    activity.getTextView1().setVisibility(View.VISIBLE);
//                    activity.getTextView2().setVisibility(View.VISIBLE);
//                    activity.getAccordeon().setText(R.string.deroulementMoins);
//                    ((Client)activity.getActivity().getApplicationContext()).getUniqueConnexion().envoyerEvent("touche");
//                }
//                else {
//                    activity.getCheckBox1().setVisibility(View.INVISIBLE);
//                    activity.getCheckBox2().setVisibility(View.INVISIBLE);
//                    activity.getTextView1().setVisibility(View.INVISIBLE);
//                    activity.getTextView2().setVisibility(View.INVISIBLE);
//                    activity.getAccordeon().setText(R.string.deroulementPlus);
//                }
//                break;
>>>>>>> dda17dcfc097a41f2a91ef165cd69eec5b828d83

            case R.id.Enregistrer:
                Log.d("Bouton enregistrer", "Parcours enregistre");
                activity.getSelectionItem().add(activity.getTextEnjeux().getText().toString());
                activity.getSelectionItem().add(activity.getTextCompetence().getText().toString());
                String fileName = "mon_parcours";
                String final_selection = "";
                for (String selections : activity.getSelectionItem()){
                    Log.d("WRITEFILE", "ecriture de "+activity.getSelectionItem().toString());
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




        }
    }


}