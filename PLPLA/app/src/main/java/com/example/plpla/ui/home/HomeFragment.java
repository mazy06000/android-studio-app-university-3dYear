package com.example.plpla.ui.home;

import android.graphics.Color;
import android.os.Bundle;
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
import com.example.plpla.R;
import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.viewgroup.ExpansionLayoutCollection;

import java.util.ArrayList;
import java.util.Arrays;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import matière.UE;

import static com.example.plpla.Utils.dpToPx;

public class HomeFragment extends Fragment {

    //COMPOSANT DU LAYOUT

    ViewGroup dynamicLayoutContainer;
    private int compteur = 0;
    private Button enregistrer;
    private Socket socket;
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
    UE macro1 = new UE("SPEA10","Choix ECUE MIASHS S1","ECUE MIASHS S1 : Macroeconomie 1",1,3,111);
    UE physique = new UE("SPUP10",null,"UE PHYSIQUE S1 : Mecanique 1",1,6,203);
    UE terre = new UE("SPUT10",null,"UE TERRE S1 : Decouverte des sciences de la terre",1,6,56);
    UE fondement1 = new UE("SPEA10",null,"UE MATHS S1 : Fondements 1",1,6,322);
    UE methodeBlocM = new UE("SPUM12",null,"UE MATHS S1 : Methodes - approche continue",1,6,280);


    ArrayList<ExpansionHeader> listeHeaderTotal =  new ArrayList<>();

    ArrayList<UE> listeUEBlocFondement = new ArrayList<>(Arrays.asList(geo1,geo2,disciplinaire1,base,web,complement1,methodeBlocF,genetique,organique,chimie,electronique,
            culture1,analyse,macro1,physique,terre,fondement1));

    ArrayList<UE> listeUEBlocMethode = new ArrayList<>(Arrays.asList(geo1,geo2,disciplinaire1,base,web,methodeBlocM,genetique,organique,chimie,electronique,
            culture1,analyse,macro1,physique,terre,fondement1));


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

        this.dynamicLayoutContainer = root.findViewById(R.id.dynamicLayoutContainer);


        final ArrayList<ArrayList> listeHeader = new ArrayList(Arrays.asList(addDynamicLayout(blocFondement, listeUEBlocFondement),addDynamicLayout(blocMethode, listeUEBlocMethode)));

        //example of how to add a listener
        final com.example.plpla.ExpansionLayout fondementLayout = (com.example.plpla.ExpansionLayout) listeHeader.get(0).get(1);
        fondementLayout.addListener(new com.example.plpla.ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(com.example.plpla.ExpansionLayout expansionLayout, boolean expanded) {
                actionOnSelection((ExpansionHeader) listeHeader.get(0).get(0), listeHeader, fondement1);
            }
        });

        final com.example.plpla.ExpansionLayout methodeLayout = (com.example.plpla.ExpansionLayout) listeHeader.get(1).get(1);
        methodeLayout.addListener(new com.example.plpla.ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(com.example.plpla.ExpansionLayout expansionLayout, boolean expanded) {
                actionOnSelection((ExpansionHeader) listeHeader.get(1).get(0), listeHeader, methodeBlocM);
            }
        });

        final ExpansionLayoutCollection expansionLayoutCollection = new ExpansionLayoutCollection();
        expansionLayoutCollection.add((com.example.plpla.ExpansionLayout) listeHeader.get(0).get(1)).add((com.example.plpla.ExpansionLayout) listeHeader.get(1).get(1));
        expansionLayoutCollection.openOnlyOne(true);

        //BOUTON ENREGISTRER
        enregistrer = root.findViewById(R.id.boutonEnregistrer);

        /*Par défaut Enregistrer n'est pas cliquable*/
        enregistrer.setClickable(false);
        enregistrer.setEnabled(false);

        ListenerButton listenerButton = new ListenerButton(socket,this);
        enregistrer.setOnClickListener(listenerButton);

        ((Client)getActivity().getApplicationContext()).getUniqueConnexion().getmSocket().on("Saved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), R.string.saved_on_server, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        return root;
    }

    public ArrayList addDynamicLayout(UE bloc, ArrayList<UE> listeBloc) {

        final ExpansionHeader expansionHeader = createExpansionHeader(bloc);
        dynamicLayoutContainer.addView(expansionHeader, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final com.example.plpla.ExpansionLayout expansionLayout = createExpansionLayout(listeBloc);
        dynamicLayoutContainer.addView(expansionLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        expansionHeader.setExpansionLayout(expansionLayout);

        return new ArrayList(Arrays.asList(expansionHeader,expansionLayout));

    }



    @NonNull
    private com.example.plpla.ExpansionLayout createExpansionLayout(ArrayList<UE> listeBloc) {
        final com.example.plpla.ExpansionLayout expansionLayout = new com.example.plpla.ExpansionLayout(getActivity());
        final ArrayList<ArrayList> listeHeader = new ArrayList<>();

        final LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        expansionLayout.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(getActivity(), 48)); //equivalent to addView(linearLayout)


        final ArrayList<UE> listeDiscipline = new ArrayList<>();
        ArrayList<String> listeNomDiscipline = new ArrayList<>();

        for (int i = 0; i < listeBloc.size(); i++) {
            if ((listeBloc.get(i).getDiscipline() == null)) {
                listeDiscipline.add(listeBloc.get(i));
                listeNomDiscipline.add(listeBloc.get(i).getDiscipline());
            } else if (!(listeBloc.get(i).getDiscipline() == null) && !listeNomDiscipline.contains(listeBloc.get(i).getDiscipline())) {
                listeDiscipline.add(listeBloc.get(i));
                listeNomDiscipline.add(listeBloc.get(i).getDiscipline());
            }
        }

        for (int i = 0; i < listeDiscipline.size(); i++) {
            final ExpansionHeader headerchoix  = choixHeader(listeDiscipline.get(i));
            final com.example.plpla.ExpansionLayout layoutchoix = choixLayout(listeDiscipline.get(i),listeBloc);
            headerchoix.setExpansionLayout(layoutchoix);
            layout.addView(headerchoix);
            layout.addView(layoutchoix);
            if (!(headerchoix.getWithoutCheck()) && !(headerchoix.getCheckboxHeader().isChecked())) {
                listeHeader.add(new ArrayList<>(Arrays.asList(headerchoix, layoutchoix, listeDiscipline.get(i))));
                listeHeaderTotal.add(headerchoix);

            }
        }

        for (int i = 0; i < listeHeader.size(); i++) {
            com.example.plpla.ExpansionLayout layoutDeListe = (com.example.plpla.ExpansionLayout) listeHeader.get(i).get(1);
            final ExpansionHeader headerDeListe = (ExpansionHeader) listeHeader.get(i).get(0);
            final int finalI = i;
            layoutDeListe.addListener(new com.example.plpla.ExpansionLayout.Listener() {
                @Override
                public void onExpansionChanged(com.example.plpla.ExpansionLayout expansionLayout, boolean expanded) {
                    actionOnSelection(headerDeListe,listeHeader, listeDiscipline.get(finalI));
                }
            });
        }


        return expansionLayout;
    }

    @NonNull
    private ExpansionHeader createExpansionHeader(UE bloc) {
        final ExpansionHeader expansionHeader = new ExpansionHeader(getActivity());
        expansionHeader.setBackgroundColor(Color.parseColor("#00afd7"));
        expansionHeader.setPadding(dpToPx(getActivity(), 16), dpToPx(getActivity(), 16), dpToPx(getActivity(), 16), dpToPx(getActivity(), 16));

        final RelativeLayout layout = new RelativeLayout(getActivity());
        expansionHeader.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //equivalent to addView(linearLayout)
        expansionHeader.setText(bloc.getDiscipline());
        //checkbox
        //final CheckBox checkboxHeader = new CheckBox(this);
        final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(expansionHeader.getCheckboxHeader(), imageLayoutParams);
        /*bloc.setId(id);
        checkboxHeader.setId(bloc.getId());
        id++;*/


        /*//image
        final ImageView expansionIndicator = new AppCompatImageView(this);
        expansionIndicator.setImageResource(R.drawable.ic_expansion_header_indicator_grey_24dp);
        final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(expansionIndicator, imageLayoutParams);*/

        //label
        final TextView text = new TextView(getActivity());
        text.setText(bloc.getDiscipline());
        text.setTextColor(Color.WHITE);

        final RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        layout.addView(text, textLayoutParams);

        //expansionHeader.setExpansionHeaderIndicator(expansionIndicator);
        return expansionHeader;
    }




    @NonNull
    private ExpansionHeader choixHeader(UE matiere) {
        final ExpansionHeader expansionHeader = new ExpansionHeader(getActivity());
        if (matiere.getDiscipline() == null) {
            expansionHeader.setBackgroundColor(Color.WHITE);
        }
        else expansionHeader.setBackgroundColor(Color.parseColor("#B6B8B9"));
        expansionHeader.setPadding(dpToPx(getActivity(), 22), dpToPx(getActivity(), 16), dpToPx(getActivity(), 16), dpToPx(getActivity(), 16));

        final RelativeLayout layout = new RelativeLayout(getActivity());
        expansionHeader.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //equivalent to addView(linearLayout)

        if (matiere.getDiscipline() == null){
            //checkbox
            //final CheckBox checkboxHeader = new CheckBox(this);
            final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layout.addView(expansionHeader.getCheckboxHeader(), imageLayoutParams);
            expansionHeader.setNoChoix(true);
            expansionHeader.setText(matiere.getNomUE());
            if (matiere.getCode() == "SPEA10" || ((matiere.getCode() == "SPUM12")&&(matiere.getDiscipline() == null))) {
                expansionHeader.getCheckboxHeader().setChecked(true);
                expansionHeader.getCheckboxHeader().setEnabled(false);
                expansionHeader.setEnabled(false);
                selectionUE.add(matiere.getNomUE());
                selectionCode.add(matiere.getCode());

            }
        }
        else {
            //image
            expansionHeader.setWithoutCheck(true);
            final ImageView expansionIndicator = new AppCompatImageView(getActivity());
            expansionIndicator.setImageResource(R.drawable.ic_expansion_header_indicator_grey_24dp);
            final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layout.addView(expansionIndicator, imageLayoutParams);
            expansionHeader.setExpansionHeaderIndicator(expansionIndicator);
        }



        //label
        final TextView text = new TextView(getActivity());
        if (matiere.getDiscipline() == null) {
            text.setText(matiere.getNomUE());
        } else text.setText(matiere.getDiscipline());
        text.setTextColor(Color.parseColor("#3E3E3E"));

        final RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        layout.addView(text, textLayoutParams);

        return expansionHeader;
    }

    @NonNull
    private com.example.plpla.ExpansionLayout choixLayout(UE matiere, final ArrayList<UE> listeBloc) {
        final com.example.plpla.ExpansionLayout expansionLayout = new com.example.plpla.ExpansionLayout(getActivity());
        final ArrayList<ArrayList> listeHeader = new ArrayList<>();

        final LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        expansionLayout.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(getActivity(), 48)); //equivalent to addView(linearLayout)

        /*final TextView text = new TextView(this);
        text.setText("Content");
        text.setGravity(Gravity.CENTER);
        text.setTextColor(Color.parseColor("#3E3E3E"));
        text.setBackgroundColor(Color.parseColor("#EEEEEE"));
        layout.addView(text, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(this, 200));

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View child = new View(ExpansionPanelSampleActivityProgrammatically.this);
                child.setBackgroundColor(Color.BLACK);
                layout.addView(child, ViewGroup.LayoutParams.MATCH_PARENT, 100);
            }
        });*/

        for (int i = 0; i < listeBloc.size(); i++) {
            if ((listeBloc.get(i).getDiscipline() == matiere.getDiscipline()) && !(listeBloc.get(i).getDiscipline() == null)) {
                ExpansionHeader headerue  = matiereHeader(listeBloc.get(i));
                com.example.plpla.ExpansionLayout layoutue = matiereLayout();
                headerue.setExpansionLayout(layoutue);
                layout.addView(headerue);
                layout.addView(layoutue);
                listeHeader.add(new ArrayList<>(Arrays.asList(headerue, layoutue, listeBloc.get(i))));
                listeHeaderTotal.add(headerue);
            }

        }

        for (int i = 0; i < listeHeader.size(); i++) {
            com.example.plpla.ExpansionLayout layoutDeListe = (com.example.plpla.ExpansionLayout) listeHeader.get(i).get(1);
            final ExpansionHeader headerDeListe = (ExpansionHeader) listeHeader.get(i).get(0);
            final int finalI = i;
            layoutDeListe.addListener(new com.example.plpla.ExpansionLayout.Listener() {
                @Override
                public void onExpansionChanged(com.example.plpla.ExpansionLayout expansionLayout, boolean expanded) {
                    actionOnSelection(headerDeListe,listeHeader, listeBloc.get(finalI));
                }
            });
        }


        return expansionLayout;
    }


    @NonNull
    private ExpansionHeader matiereHeader(UE matiere) {
        final ExpansionHeader expansionHeader = new ExpansionHeader(getActivity());
        expansionHeader.setBackgroundColor(Color.WHITE);
        expansionHeader.setPadding(dpToPx(getActivity(), 28), dpToPx(getActivity(), 16), dpToPx(getActivity(), 16), dpToPx(getActivity(), 16));

        final RelativeLayout layout = new RelativeLayout(getActivity());
        expansionHeader.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT); //equivalent to addView(linearLayout)
        expansionHeader.setText(matiere.getNomUE());

        //checkbox
        //final CheckBox checkboxHeader = new CheckBox(this);
        final RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        imageLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(expansionHeader.getCheckboxHeader(), imageLayoutParams);

        if (matiere.getCode() == "SPEA10") {
            expansionHeader.getCheckboxHeader().setChecked(true);
            expansionHeader.getCheckboxHeader().setEnabled(false);
        }

        //label
        final TextView text = new TextView(getActivity());
        text.setText(matiere.getNomUE());
        text.setTextColor(Color.parseColor("#3E3E3E"));

        final RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        layout.addView(text, textLayoutParams);

        return expansionHeader;
    }

    @NonNull
    private com.example.plpla.ExpansionLayout matiereLayout() {
        final com.example.plpla.ExpansionLayout expansionLayout = new ExpansionLayout(getActivity());

        final LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        expansionLayout.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(getActivity(), 48)); //equivalent to addView(linearLayout)


        return expansionLayout;
    }

    public void actionOnSelection(ExpansionHeader header, ArrayList<ArrayList> header2, UE matiere){
        if ((header.getCheckboxHeader().isChecked())){
            compteur++;
            if (compteur >= 4){
                for (int i = 0; i < listeHeaderTotal.size(); i++) {
                    if ((header != listeHeaderTotal.get(i)) && !(listeHeaderTotal.get(i).getCheckboxHeader().isChecked())) {
                        listeHeaderTotal.get(i).getCheckboxHeader().setEnabled(false);
                        listeHeaderTotal.get(i).setEnabled(false);
                        enregistrer.setEnabled(true);
                    }
                }
            }
            else {
                for (int i = 0; i < header2.size(); i++) {
                    ExpansionHeader headerf = (ExpansionHeader) header2.get(i).get(0);
                    if ((header != headerf) && !(headerf.getCheckboxHeader().isChecked()) && (header.getNoChoix() == false)) {
                        headerf.getCheckboxHeader().setEnabled(false);
                        headerf.setEnabled(false);

                    }

                }
            }

            selectionUE.add(header.getText());
            selectionCode.add(matiere.getCode());

        }
        else {
            compteur--;
            if (compteur < 4){
                for (int i = 0; i < listeHeaderTotal.size(); i++) {
                    if ((header != listeHeaderTotal.get(i)) && !(listeHeaderTotal.get(i).getCheckboxHeader().isChecked())) {
                        listeHeaderTotal.get(i).getCheckboxHeader().setEnabled(true);
                        listeHeaderTotal.get(i).setEnabled(true);

                    }
                }
            }
            else {
                for (int i = 0; i < header2.size(); i++) {
                    ExpansionHeader headerf = (ExpansionHeader) header2.get(i).get(0);
                    if (header != headerf) {
                        headerf.getCheckboxHeader().setEnabled(true);
                        headerf.setEnabled(true);
                    }
                }
            }

            enregistrer.setEnabled(false);
            selectionUE.remove(header.getText());
            selectionCode.remove(matiere.getCode());
        }
    }

    public int nbChecked(){
        int compteur=0;
        for (int i = 0; i < listeHeaderTotal.size(); i++) {
            if (listeHeaderTotal.get(i).getCheckboxHeader().isChecked()) compteur++;

        }
        return compteur;
    }


}