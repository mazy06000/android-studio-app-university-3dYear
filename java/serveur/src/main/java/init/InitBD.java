package init;

import matière.UE;
import serveur.BaseDonnee;
import serveur.UtilServeur;

import java.io.File;
import java.util.ArrayList;

public class InitBD {
    private static final String PATH_FILES_SEMESTRE = "./serveur/donnees/semestre/";
    private static final String PATH_FILES_USER = "./serveur/donnees/users/";
    private static final String SEMESTRE1_FILENAME = "semestre1.json";
    private static final String SEMESTRE2_FILENAME = "semestre2.json";
    private static final String SEMESTRE3_FILENAME = "semestre3.json";

    private File semestre = new File(PATH_FILES_SEMESTRE);;
    private File users = new File(PATH_FILES_USER);;



    public static void main(String[] args) {
        InitBD initBD = new InitBD();
        BaseDonnee baseDonnee = new BaseDonnee();
        initBD.initAll();
        initBD.init_semestre(baseDonnee.getListeUES1(), SEMESTRE1_FILENAME);
        initBD.init_semestre(baseDonnee.getListeUES1(), SEMESTRE2_FILENAME);
        initBD.init_semestre(baseDonnee.getListeUES1(), SEMESTRE3_FILENAME);
    }

    void init_semestre(ArrayList<UE> list, String nomFichierSemestre){
        UtilServeur.writeToJSON(PATH_FILES_SEMESTRE+nomFichierSemestre, list);
    }


        /*Rien à écrire juste créer les dossier/reinitialiser les données */


    void deleteAll(File dir){
        if (dir != null) {
            for (File f : dir.listFiles()) {
                f.delete();
            }
        }
    }
    void initDir(File dir){
        if (dir != null) {
            if (dir.exists()){
                deleteAll(dir);
            }
            else {
                dir.mkdirs();
            }
        }
    }

    void initAll(){
        initDir(semestre);
        initDir(users);
    }
}
