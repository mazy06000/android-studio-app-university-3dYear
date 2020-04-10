package serveur;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import matière.UE;
import user.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static serveur.BaseDonnee.*;

public class UtilServeur {

    /**
     * Fais une hashmap avec les code de matières comme clé et l'Objet UE qui correspond à la matière
     * @param listUE La liste des UE
     * @return HashMap de type <"CodeUE", UE>
     */
    public static final HashMap<String, UE> initDictUE(ArrayList<UE> listUE) {
        HashMap<String, UE> dicoUE = new HashMap<>();
        for (UE ue : listUE) {
            dicoUE.put(ue.getCode(), ue);
        }
        return dicoUE;
    }

    /**initialisation de la liste de toutes les matières*/
    public static final ArrayList<UE> initListeUE(Object... list){
        ArrayList<UE> listUE = new ArrayList<>();
        for (Object l: list){
            listUE.addAll((ArrayList<UE>) l);
        }
        return listUE;
    }


    /**
     * Cherche si un utilisateur s'est deja connecté une fois
     * @param listUsers Une liste d'UE
     * @param user Un User
     * @return Un booléen vrai si l'user est dans la liste, faux dans le cas contraire
     */
    public static final boolean userExist(ArrayList<User> listUsers, User user) {
        for (User utilisateur: listUsers) {
            if (utilisateur.equals(user)){
                return true;
            }
        }
        return false;
    }


    /**
     * crée un fichier nomDefichier.json (dans serveur/target/generated-sources/ ), convertit l'objet en json dans le fichier et le renvoie .
     * @param nomDeFichier nom du ficher json (Ajouter l'extension .json !!)
     * @param object l'objet à convertir
     * @return Le fichier json créé
     */
    public static final  File writeToJSON(String nomDeFichier, Object object){
        File file = new File("./serveur/target/generated-sources/"+nomDeFichier);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("Le fichier "+nomDeFichier+" a été créé avec succès !");
//            else {
//                System.out.println(nomDeFichier+".json existe et sera écrasé");
//            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, object);
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public static final ArrayList<UE> JSONFileToListUE(File fileName){
        ArrayList<UE> ueArrayList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            ueArrayList = mapper.readValue(fileName, new TypeReference<ArrayList<UE>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ueArrayList;
    }
    public static final ArrayList<User> JSONFileToListUsers(File fileName){
        ArrayList<User> userArrayList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            userArrayList = mapper.readValue(fileName, new TypeReference<ArrayList<User>>(){});
        } catch (IOException e) {
            System.out.println(fileName+" existe mais est vide !");
            //e.printStackTrace();
        }
        return userArrayList;
    }

}
