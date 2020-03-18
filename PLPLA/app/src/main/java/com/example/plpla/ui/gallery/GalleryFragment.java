package com.example.plpla.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.plpla.CourseActivity;
import com.example.plpla.R;
import com.example.plpla.ui.home.HomeFragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private String serverAdress;

    private TextView monParcours;
    private Socket socket;
    private Button reinitialiser;
    private static final String FILE_NAME = "parcours.txt";
    private TextView finalText;
    private TextView parcoursVide;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_course, container, false);

        /*Get the value of the ipAddress from the mainActivity*/
        //serverAdress = getArguments().getString("url");
        Log.d("SERVEUR", "Adresse du serveur :"+serverAdress);
        socket = null;
        try {
            /*Si vous utilisez l'emulateur, utilisez la ligne suivante*/
            //socket = IO.socket("http://10.0.2.2:4444");
            /*Sinon remplacez par l'addresse IP de votre serveur (votre pc normalement)*/
            //socket = IO.socket("http://192.168.0.23:4444");
            //socket = IO.socket(serverAdress);

            monParcours = root.findViewById(R.id.monParcours);
            parcoursVide = root.findViewById(R.id.parcours_vide);
            finalText = root.findViewById(R.id.parcours_final);
            reinitialiser = root.findViewById(R.id.reinitialiser_button);

            //VISIBILITE PAR DEFAUT
            finalText.setVisibility(View.INVISIBLE);
            parcoursVide.setVisibility(View.VISIBLE);
            reinitialiser.setEnabled(false);

            //Afficher le parcours enregistré
            if (!HomeFragment.getSelectionItem().isEmpty()) {
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
                    HomeFragment.getSelectionItem().clear();
                    Toast.makeText(getActivity(), "Parcours réinitialisé", Toast.LENGTH_LONG).show();
                    reinitialiser.setEnabled(false);
                }
            });


            //socket.connect();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }
}