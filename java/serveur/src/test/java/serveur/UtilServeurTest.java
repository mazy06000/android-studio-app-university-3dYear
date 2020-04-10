package serveur;

import matière.UE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UtilServeurTest {

    UE geo1;
    UE geo2;
    UE base;

    ArrayList<UE> choix;
    ArrayList<UE> choix2;
    ArrayList<UE> choixVide;
    ArrayList<UE> listUE;

    User user1;
    User user2;
    User user3 ;
    ArrayList<User> listUsers;

    @BeforeEach
    void setUp() {
        geo1 = new UE("SPUGDE10","Geographie S1","UE GEOGRAPHIE : Decouverte 1",1,6,4);
        geo2 = new UE("SPUGDC10","Geographie S1","UE GEOGRAPHIE : Decouverte 2",1,6,3);
        base = new UE("SPUF10","Informatique S1","UE INFO S1 : Bases de l'informatique",1,6,164);

        choix = new ArrayList<>(Arrays.asList(geo1, base));
        choix2 = new ArrayList<>(Arrays.asList(base));
        choixVide = new ArrayList<>();
        listUE = new ArrayList<>(Arrays.asList(geo1, geo2, base));

        user1 = new User("Name1", "prenom1", "/192.168.0.5", choix);
        user2 = new User("Name2", "prenom2", "/192.168.0.6", choix2);
        user3 = new User("Name3", "prenom3", "/192.168.0.-99", choixVide);

        listUsers = new ArrayList<>(Arrays.asList(user1,user2,user3));
    }


    @Test
    void initDictUE() {

        HashMap<String, UE> dico_UE_attendu;
        HashMap<String, UE> dico_UE;

        /**
         * 1er cas : On verifie qu'on a bien le dictionnaire des UE avec :
         * key = code de l'UE et valeur = UE (l'objet)
         * pour les UEs du setUp et la liste listeUE du setUp
         */
        dico_UE_attendu = new HashMap<>();
        dico_UE_attendu.put(geo1.getCode(), geo1);
        dico_UE_attendu.put(geo2.getCode(), geo2);
        dico_UE_attendu.put(base.getCode(), base);


        dico_UE = UtilServeur.initDictUE(this.listUE);
        assertEquals(dico_UE_attendu,dico_UE);

        /**
         * 2ème cas : On verifie qu'on a bien le dictionnaire des UE avec :
         * key = code de l'UE et valeur = UE (l'objet)
         * pour les UEs du setUp et la liste listeUE du setUp
         * AVEC plusieurs fois la même UE dans listeUE
         */
        dico_UE_attendu = new HashMap<>();
        dico_UE_attendu.put(geo1.getCode(), geo1);
        dico_UE_attendu.put(geo2.getCode(), geo2);
        dico_UE_attendu.put(base.getCode(), base);

        this.listUE.addAll(listUE);
        dico_UE = UtilServeur.initDictUE(listUE);
        assertEquals(dico_UE_attendu,dico_UE);

        /**
         * 3ème cas : On verifie qu'on a bien le dictionnaire vide avec :
         * lorsque la liste est null
         */
        dico_UE = UtilServeur.initDictUE(null);
        dico_UE_attendu = new HashMap<>();
        assertEquals(dico_UE_attendu,dico_UE);
    }

    @Test
    void initListeUE() {
        ArrayList<UE> listeUE_attendu;
        ArrayList<UE> listdesUE;

        /**
         * 1er cas : On vérifie que dans la liste attendu on a bien
         * toutes nos UEs
         * ici : geo1, geo2
         */
        ArrayList<UE> list1 = new ArrayList<>(Arrays.asList(geo1,base));
        ArrayList<UE> list2 = new ArrayList<>(Arrays.asList(geo2));
        ArrayList<UE> list3 = new ArrayList<>(Arrays.asList(geo1));
        listeUE_attendu = UtilServeur.initListeUE(list1,list2);
        listdesUE = new ArrayList<>(Arrays.asList(geo1,base,geo2));

        assertEquals(listeUE_attendu, listdesUE);

        /**
         * 2eme cas : On vérifie que la liste attendu les objets null sont ignorés
         *
         */
        listeUE_attendu = UtilServeur.initListeUE(list2, null, list3);
        listdesUE = new ArrayList<>(Arrays.asList(geo2,geo1));

        assertEquals(listeUE_attendu, listdesUE);

        /**
         * 3ème cas : On vérifie que la liste attendu est vide lorsque qu'il n'y pas d'UE passé en paramètre
         */
        listeUE_attendu = UtilServeur.initListeUE();
        listdesUE = new ArrayList<>();


        assertEquals(listeUE_attendu, listdesUE);

    }

    @Test
    void userExist() {
    }

    @Test
    void writeToJSON() {
    }

    @Test
    void JSONFileToListUE() {
    }

    @Test
    void JSONFileToListUsers() {
    }

    @Test
    void getIndexUser() {
    }
}