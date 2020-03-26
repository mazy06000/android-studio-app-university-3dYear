package com.example.plpla.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.plpla.Client;
import com.example.plpla.R;
import com.example.plpla.ui.home.PortailFragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ParcoursFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private TextView monParcours;
    private Button reinitialiser;
    private static final String FILE_NAME = "parcours.txt";
    private TextView finalText;
    private TextView parcoursVide;
    private Socket mSocket;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_course, container, false);

        monParcours = root.findViewById(R.id.monParcours);
        parcoursVide = root.findViewById(R.id.parcours_vide);
        finalText = root.findViewById(R.id.parcours_final);
        reinitialiser = root.findViewById(R.id.reinitialiser_button);

        //VISIBILITE PAR DEFAUT
        finalText.setVisibility(View.INVISIBLE);
        parcoursVide.setVisibility(View.VISIBLE);
        reinitialiser.setEnabled(false);

        Client client = (Client) getActivity().getApplication();
        mSocket = client.getUniqueConnexion().getmSocket();

        mSocket.on(EVENT.INIT_PARCOURS, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("EVENT_SOCKET", "Socket event INIT_PARCOURS received");
                        Toast.makeText(getActivity().getApplicationContext(), R.string.reinitialiser, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        try {
            //Afficher le parcours enregistré
            if (!PortailFragment.getSelectionItem().isEmpty()) {
                reinitialiser.setEnabled(true);
                parcoursVide.setVisibility(View.INVISIBLE);

                //Lecture du fichier enregistré
                String parcours;
                FileInputStream fichierLecture = getActivity().openFileInput("mon_parcours");
                InputStreamReader lecteur = new InputStreamReader(fichierLecture);
                BufferedReader bfr = new BufferedReader(lecteur);
                StringBuffer stringBuffer = new StringBuffer();
                while ((parcours = bfr.readLine()) != null){
                    stringBuffer.append(parcours + "\n");
                }

                //On affiche la lecture
                finalText.setText(stringBuffer.toString());
                finalText.setVisibility(View.VISIBLE);
            }

            //LORSQUE JE CLIQUE SUR REINITIALISER
            reinitialiser.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finalText.setVisibility(View.INVISIBLE);
                    parcoursVide.setVisibility(View.VISIBLE);
                    finalText.setText("");
                    PortailFragment.getSelectionItem().clear();
                    //Toast.makeText(getActivity(), "Parcours réinitialisé", Toast.LENGTH_LONG).show();
                    Log.d("DELETE_PARCOURS_SERVER", "Envoie du message de Réinitialisation au serveur");
                    ((Client)getActivity().getApplicationContext()).getUniqueConnexion().getmSocket().emit("INIT_PARCOURS");
                    reinitialiser.setEnabled(false);
                }
            });



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}