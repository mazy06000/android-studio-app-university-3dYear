package serveur;

import matière.UE;
import user.User;

import java.util.ArrayList;
import java.util.HashMap;

import static serveur.BaseDonnee.*;

public class UtilServeur {

    /**
     * Fais une hashmap avec les code de matières comme clé et l'Objet UE qui correspond à la matière
     * @param listUE La liste des UE
     * @return HashMap de type <"CodeUE", UE>
     */
    public static HashMap<String, UE> initDictUE(ArrayList<UE> listUE) {
        HashMap<String, UE> dicoUE = new HashMap<>();
        for (UE ue : listUE) {
            dicoUE.put(ue.getCode(), ue);
        }
        return dicoUE;
    }

    /**initialisation de la liste de toutes les matières*/
    public static ArrayList<UE> initListeUE(){
        ArrayList<UE> listUE = new ArrayList<>();
        listUE.addAll(listeUEBlocFondement);
        listUE.addAll(listeUEBlocMethode);
        listUE.addAll(listeUEBlocFondementS2);
        listUE.addAll(listeUEBlocMethodeS2);
        listUE.addAll(listeUEBlocS3);
        return listUE;
    }


    /**
     * Cherche si un utilisateur s'est deja connecté une fois
     * @param listUsers Une liste d'UE
     * @param user Un User
     * @return Un booléen vrai si l'user est dans la liste, faux dans le cas contraire
     */
    public static boolean userExist(ArrayList<User> listUsers, User user) {
        for (User utilisateur: listUsers) {
            if (utilisateur.equals(user)){
                return true;
            }
        }
        return false;
    }


}
