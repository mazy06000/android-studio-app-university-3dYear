package com.example.plpla.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.example.plpla.Client;
import com.example.plpla.ExpansionHeader;
import com.example.plpla.ExpansionLayout;
import com.example.plpla.ExpansionView;
import com.example.plpla.R;
import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.Arrays;

import events.EVENT;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import matière.UE;

import static com.example.plpla.Utils.dpToPx;

public class PortailFragment extends Fragment {

    //COMPOSANT DU LAYOUT


    private ExpansionView expansionView;
    private Button enregistrer;
    private Socket mSocket;
    private String serverAdress;
    UE blocFondement = new UE(null,"BLOC MATH S1 : Fondements 1",null,1,30,0);
    UE blocMethode = new UE(null,"BLOC MATH S1 : Methodes. approche continue",null,1,30,0);

    UE geo1 = new UE("SPUGDE10","Choix geographie S1","UE GEOGRAPHIE : Decouverte 1",1,6,4);
    UE geo2 = new UE("SPUGDC10","Choix geographie S1","UE GEOGRAPHIE : Decouverte 2",1,6,3);
    UE disciplinaire1 = new UE("SPUGDI10","Choix geographie S1","UE GEOGRAPHIE : Disciplinaire 1",1,6,3);
    UE base = new UE("SPUF10","Choix informatique S1","UE INFO S1 : Bases de l'informatique",1,6,164);
    UE web = new UE("SPUF11","Choix informatique S1","UE INFO S1 : Introduction a l'informatique par le web",1,6,134);
    UE complement1 = new UE("SPUM13","Choix Math Fondements 1 S1","UE MATHS S1 : Complements 1",1,6,100);
    UE methodeBlocF = new UE("SPUM12","Choix Math Fondements 1 S1","UE MATHS S1 : Methodes - approche continue",1,6,280);
    UE genetique = new UE("SPUV101","Choix SV - PO1 SITE","UE SV-S1 : Genetique. evolution. origine vie & biodiversite",1,6,43);
    UE organique = new UE("SPUV100","Choix SV - PO1 SITE","UE SV-S1 : Org et mecan. moleculaires - cellules eucaryotes",1,6,26);
    UE chimie = new UE("SPUC10",null,"UE CHIMIE S1 : Structure microscopique de la matiere",1,6,167);
    UE electronique = new UE("SPUE10",null,"UE ELECTRONIQUE S1 : Electronique numerique - Bases",1,6,154);
    UE culture1 = new UE("SPEA11","Choix ECUE MIASHS S1","ECUE MIASHS S1 : Culture economie 1",1,3,49);
    UE analyse = new UE("SPEA12","Choix ECUE MIASHS S1","ECUE MIASHS S1 : Introduction a l'analyse economique",1,3,61);
    UE macro1 = new UE("SPEA10","Choix ECUE MIASHS S1","ECUE MIASHS S1 : Macroeconomie 1",1,3,111, true);
    UE physique = new UE("SPUP10",null,"UE PHYSIQUE S1 : Mecanique 1",1,6,203);
    UE terre = new UE("SPUT10",null,"UE TERRE S1 : Decouverte des sciences de la terre",1,6,56);
    UE fondement1 = new UE("SPEA10",null,"UE MATHS S1 : Fondements 1",1,6,322, true);
    UE methodeBlocM = new UE("SPUM12",null,"UE MATHS S1 : Methodes - approche continue",1,6,280, true);


    ArrayList<ArrayList<UE>> blocEtSaMatiere = new ArrayList<>(Arrays.asList(new ArrayList<UE>(Arrays.asList(blocFondement,fondement1)),
            new ArrayList<UE>(Arrays.asList(blocMethode,methodeBlocM))));

    ArrayList<UE> listeUEBlocFondement = new ArrayList<>(Arrays.asList(geo1,geo2,disciplinaire1,base,web,complement1,methodeBlocF,genetique,organique,chimie,electronique,
            culture1,analyse,macro1,physique,terre,fondement1));

    ArrayList<UE> listeUEBlocMethode = new ArrayList<>(Arrays.asList(geo1,geo2,disciplinaire1,base,web,methodeBlocM,genetique,organique,chimie,electronique,
            culture1,analyse,macro1,physique,terre));


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
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Client client = (Client) getActivity().getApplication();
        mSocket = client.getUniqueConnexion().getmSocket();

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

        expansionView = new ExpansionView(this, root, mSocket, getActivity(), enregistrer,selectionUE, selectionCode,
                listeUEBlocFondement, listeUEBlocMethode, blocEtSaMatiere);
        this.expansionView.setDynamicLayoutContainer((ViewGroup) root.findViewById(R.id.dynamicLayoutContainer));

        expansionView.createExpansion();


        return root;
    }

}