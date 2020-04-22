package serveur;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import matière.UE;
import user.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class BaseDonnee {

    private static final String PATH_FILES_SEMESTRE = "./serveur/donnees/semestre/";
    private static final String PATH_FILES_USER = "./serveur/donnees/users/";
    public static final String USER_FILENAME = "utilisateurs.json";
    public static final String SEMESTRE1_FILENAME = "semestre1.json";
    public static final String SEMESTRE2_FILENAME = "semestre2.json";
    public static final String SEMESTRE3_FILENAME = "semestre3.json";

    File dirSemestre = new File(PATH_FILES_SEMESTRE);
    File dirUsers = new File(PATH_FILES_USER);


    public ArrayList<UE> loadingUE(){
        ArrayList<UE> listUE = new ArrayList<>();
        if (dirSemestre.exists()){
            System.out.println("Chargement des UE de la base de donnée...");
            for (File f : dirSemestre.listFiles()) {
                listUE.addAll(UtilServeur.JSONFileToListUE(f));
            }
            return listUE;
        }
        else{
            System.out.println("Erreur lors du chargement :" +
                    " Le dossier "+PATH_FILES_SEMESTRE+" n'existe pas !");
        }
        return null;
    }


    public ArrayList<User> loadingUsers(File utilisateurs) {
        try {
            if (utilisateurs.createNewFile()){
                System.out.println("utilisateurs.json a été créé");
            }
            else{
                System.out.println("Chargement de la liste des utilisateurs");
                return UtilServeur.JSONFileToListUsers(utilisateurs);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des utilisateurs");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> loadingUsers(){
        ArrayList<User> listUsers = new ArrayList<>();
        if (dirUsers.exists()){
            System.out.println("Chargement des Users de la base de donnée...");
            for (File f : dirUsers.listFiles()) {
                listUsers.addAll(UtilServeur.JSONFileToListUsers(f));
            }
            return listUsers;
        }
        else{
            System.out.println("Erreur lors du chargement : " +
                    " Le dossier "+PATH_FILES_USER+" n'existe pas !");
        }
        return null;
    }


    public void saveUser(User user) {
        UtilServeur.writeToJSON(PATH_FILES_USER+user.getNom()+".json", user);
    }
}
