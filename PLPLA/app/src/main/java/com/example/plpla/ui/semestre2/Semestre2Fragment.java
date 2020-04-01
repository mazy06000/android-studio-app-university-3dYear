package com.example.plpla.ui.semestre2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.plpla.Client;
import com.example.plpla.Expansion.ExpansionView;
import com.example.plpla.R;
import com.example.plpla.vue.Vue;

import java.util.ArrayList;

import events.EVENT;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import matière.UE;

public class Semestre2Fragment extends Fragment {

    //COMPOSANT DU LAYOUT


    private ExpansionView expansionView;
    private Button enregistrer;
    private Socket mSocket;
    private String serverAdress;
    private ArrayList<ArrayList<UE>> blocEtSaMatiere;
    private ArrayList<UE> listeUEBlocFondement;
    private ArrayList<UE> listeUEBlocMethode;
    private Vue mListener;



    public static ArrayList<String> getSelectionUE() {
        return selectionUE;
    }

    private static ArrayList<String> selectionUE = new ArrayList<>();
    private ArrayList<String> selectionCode = new ArrayList<>();

    public ArrayList<String> getSelectionCode() {
        return selectionCode;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_semestre2, container, false);

        Client client = (Client) getActivity().getApplication();
        mSocket = client.getUniqueConnexion().getmSocket();
        listeUEBlocFondement = client.getListeUEBlocFondementS2();
        listeUEBlocMethode = client.getListeUEBlocMethodeS2();
        blocEtSaMatiere = client.getBlocEtSaMatiereS2();
        enregistrer = root.findViewById(R.id.boutonEnregistrer2);


        mSocket.on(EVENT.SAVE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("EVENT_SOCKET", "Socket event SAVE received");
                        Toast.makeText(getActivity().getApplicationContext(), R.string.saved_on_server, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        expansionView = new ExpansionView(root, mSocket, getActivity(), enregistrer,selectionUE, selectionCode,
                listeUEBlocFondement, listeUEBlocMethode, blocEtSaMatiere);
        this.expansionView.setDynamicLayoutContainer((ViewGroup) root.findViewById(R.id.dynamicLayoutContainer));

        expansionView.createExpansion();


        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (Vue) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

}