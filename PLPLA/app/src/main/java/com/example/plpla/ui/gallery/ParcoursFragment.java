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

import com.example.plpla.Client;
import com.example.plpla.CourseActivity;
import com.example.plpla.R;
import com.example.plpla.ui.home.PortailFragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class ParcoursFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private TextView monParcours;
    private Button reinitialiser;
    private static final String FILE_NAME = "parcours.txt";
    private TextView finalText;
    private TextView parcoursVide;



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
        try {
            //Afficher le parcours enregistré
            if (!PortailFragment.getSelectionUE().isEmpty()) {
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
                    PortailFragment.getSelectionUE().clear();
                    Toast.makeText(getActivity(), "Parcours réinitialisé", Toast.LENGTH_LONG).show();
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