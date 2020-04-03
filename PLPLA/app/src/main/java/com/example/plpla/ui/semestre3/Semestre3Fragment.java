package com.example.plpla.ui.semestre3;

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

public class Semestre3Fragment extends Fragment {

    //COMPOSANT DU LAYOUT


    private ExpansionView expansionView;
    private Button enregistrer;
    private Socket mSocket;
    private String serverAdress;
    private ArrayList<UE> blocEtSaMatiere;
    private ArrayList<UE> listeUEBloc;
    private Vue mListener;
    private Button semestre4;



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
        View root = inflater.inflate(R.layout.fragment_semestre3, container, false);

        Client client = (Client) getActivity().getApplication();
        mSocket = client.getUniqueConnexion().getmSocket();
        listeUEBloc = client.getListeUEBlocS3();
        blocEtSaMatiere = client.getBlocEtSaMatiereS3();
        enregistrer = root.findViewById(R.id.boutonEnregistrer3);
        semestre4 = root.findViewById(R.id.boutonSemestre4);



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
                listeUEBloc, blocEtSaMatiere, 1);
        this.expansionView.setDynamicLayoutContainer((ViewGroup) root.findViewById(R.id.dynamicLayoutContainer));

        expansionView.createExpansion();

        /*semestre4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.changeFragment(4);
            }
        });*/

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