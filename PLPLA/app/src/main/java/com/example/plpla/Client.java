package com.example.plpla;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

import matière.UE;
import user.User;


public class Client extends Application {

    private Connexion uniqueConnexion;
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        /*on initialise l'addresse du serveur dans le MainActivity avec le setServerAddress*/
        uniqueConnexion = new Connexion();
        user = new User();
    }

    public Connexion getUniqueConnexion() {
        return uniqueConnexion;
    }

    public User getUser() {
        return user;
    }




    //UE SEMESTRE 1

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


    //LA LISTE DES 2 BLOCS AVEC LEUR MATIERE PRE-CHECKED
    ArrayList<ArrayList<UE>> blocEtSaMatiere = new ArrayList<>(Arrays.asList(new ArrayList<UE>(Arrays.asList(blocFondement,fondement1)),
            new ArrayList<UE>(Arrays.asList(blocMethode,methodeBlocM))));
    //LA LISTE DES UE DANS LE BLOC FONDEMENT
    ArrayList<UE> listeUEBlocFondement = new ArrayList<>(Arrays.asList(geo1,geo2,disciplinaire1,base,web,complement1,methodeBlocF,genetique,organique,chimie,electronique,
            culture1,analyse,macro1,physique,terre,fondement1));
    //LA LISTE DES UE DANS LE BLOC METHODE
    ArrayList<UE> listeUEBlocMethode = new ArrayList<>(Arrays.asList(geo1,geo2,disciplinaire1,base,web,methodeBlocM,genetique,organique,chimie,electronique,
            culture1,analyse,macro1,physique,terre));

    public ArrayList<ArrayList<UE>> getBlocEtSaMatiere() {
        return blocEtSaMatiere;
    }

    public ArrayList<UE> getListeUEBlocFondement() {
        return listeUEBlocFondement;
    }

    public ArrayList<UE> getListeUEBlocMethode() {
        return listeUEBlocMethode;
    }



    //UE SEMESTRE 2

    UE blocFondementS2 = new UE(null,"BLOC MATH S2 : Fondements 2",null,2,30,0);
    UE blocMethodeS2 = new UE(null,"BLOC MATH S2 : Methodes. approche discrete",null,2,30,0);

    UE geo3 = new UE("SPUGDE20","Choix geographie S2","UE GEOGRAPHIE : Decouverte 3",2,6,3);
    UE geo4 = new UE("SPUGDC20","Choix geographie S2","UE GEOGRAPHIE : Decouverte 4",2,6,3);
    UE disciplinaire2 = new UE("SPUGDI20","Choix geographie S2","UE GEOGRAPHIE : Disciplinaire 2",2,6,1);
    UE diversite = new UE("SPUV201","Choix SV S2 - PO1 SITE","UE SV S2 : Diversite du vivant",2,6,13);
    UE physiologie = new UE("SPUV200","Choix SV S2 - PO1 SITE","UE SV S2 : Physiologie - neurologie - enzymologie",2,6,27);
    UE chimieS2Reaction = new UE("SPUC20",null,"UE CHIMIE S2 : Reactions et reactivites chimiques",2,6,84);
    UE chimieS2Thermo = new UE("SPEC22","UE CHIMIE S2 : Thermodynamique chimique / Options","ECUE CHIMIE Thermodynamique Chimie",2,3,84, true);
    UE chimieS2Pollution = new UE("SPEC23","UE CHIMIE S2 : Thermodynamique chimique / Options","ECUE CHIMIE S2 : Chimie et pollution",2,3,23);
    UE chimieS2Complement = new UE("SPEC25","UE CHIMIE S2 : Thermodynamique chimique / Options","ECUE CHIMIE S2 : Complement de thermodynamique physique",2,3,16);
    UE chimieS2TChimique = new UE("SPEC24","UE CHIMIE S2 : Thermodynamique chimique / Options","ECUE CHIMIE S2 : Sens chimique",2,3,35);
    UE electroniqueSansFil = new UE("SPUE21",null,"UE ELECTRONIQUE S2 : Communication sans fil",2,6,39);
    UE electroniqueAnalogique = new UE("SPUE20",null,"UE ELECTRONIQUE S2 : Electronique analogique",2,6,44);
    UE infoImperative = new UE("SPUF21",null,"UE INFO S2 : Programmation imperative",2,6,226);
    UE infoShell = new UE("SPUF20",null,"UE INFO S2 : Systeme 1 unix et programmation shell",2,6,111);
    UE mathComplement2 = new UE("SPUM23",null,"UE MATH S2 : Complements 2",2,6,83);
    UE mathMethodeDiscrete = new UE("SPUM22",null,"UE MATH S2 : Methodes Maths-Approche discrete",2,6,242);
    UE mathFondement2 = new UE("SPUM21",null,"UE MATH S2 : Fondements 2",2,6,261);
    UE entreprise1 = new UE("SPEA21","Choix ECUE MIASHS S2","ECUE MIASHS S2 : Economie d'entreprise 1",2,3,55);
    UE information = new UE("SPEA22","Choix ECUE MIASHS S2","ECUE MIASHS S2 : Economie de l'information",2,3,28);
    UE macro1S2 = new UE("SPEA20","Choix ECUE MIASHS S2","ECUE MIASHS S2 : Macroeconomie 1",2,3,100, true);

    UE physiqueS2Mecanique = new UE("SPUP21",null,"UE PHYSIQUE S2 : Mecanique - complements",2,6,86);
    UE physiqueS2Optique = new UE("SPUP20",null,"UE PHYSIQUE S2 : Optique",2,6,117);
    UE terreS2Atmosphere = new UE("SPUT22",null,"UE TERRE S2 : Atmosphere. ocean. climats",2,6,35);
    UE terreS2Structure = new UE("SPUT20",null,"UE TERRE S2 : Structure et dynamique de la terre",2,6,31);

    UE fondementS2BlocF = new UE("SPUM21",null,"UE MATH S2 : Fondements 2",2,6,261, true);
    UE methodesS2BlocM = new UE("SPUM22",null,"UE MATH S2 : Methodes Maths-Approche discrete",2,6,242,true);


    //LA LISTE DES 2 BLOCS AVEC LEUR MATIERE PRE-CHECKED
    ArrayList<ArrayList<UE>> blocEtSaMatiereS2 = new ArrayList<>(Arrays.asList(new ArrayList<UE>(Arrays.asList(blocFondement,fondement1)),
            new ArrayList<UE>(Arrays.asList(blocFondementS2,blocMethodeS2))));
    //LA LISTE DES UE DANS LE BLOC FONDEMENT
    ArrayList<UE> listeUEBlocFondementS2 = new ArrayList<>(Arrays.asList(geo3, geo4, disciplinaire2, diversite, physiologie, chimieS2Reaction, chimieS2Thermo,
            chimieS2Pollution, chimieS2Complement, chimieS2TChimique, electroniqueSansFil, electroniqueAnalogique, infoImperative, infoShell, mathComplement2,
            mathMethodeDiscrete, entreprise1, information, macro1S2, physiqueS2Mecanique, physiqueS2Optique, terreS2Atmosphere, terreS2Structure, fondementS2BlocF));
    //LA LISTE DES UE DANS LE BLOC METHODE
    ArrayList<UE> listeUEBlocMethodeS2 = new ArrayList<>(Arrays.asList(geo3, geo4, disciplinaire2, diversite, physiologie, chimieS2Reaction, chimieS2Thermo,
            chimieS2Pollution, chimieS2Complement, chimieS2TChimique, electroniqueSansFil, electroniqueAnalogique, infoImperative, infoShell, mathFondement2,
            mathMethodeDiscrete, entreprise1, information, macro1S2, physiqueS2Mecanique, physiqueS2Optique, terreS2Atmosphere, terreS2Structure, methodesS2BlocM));

    public ArrayList<ArrayList<UE>> getBlocEtSaMatiereS2() {
        return blocEtSaMatiere;
    }

    public ArrayList<UE> getListeUEBlocFondementS2() {
        return listeUEBlocFondement;
    }

    public ArrayList<UE> getListeUEBlocMethodeS2() {
        return listeUEBlocMethode;
    }
}
