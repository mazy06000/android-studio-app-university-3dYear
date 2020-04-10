package serveur;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import events.EVENT;
import matière.UE;
import user.User;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

public class Serveur {

    private SocketIOServer socketIOServer;
    /**
     * Liste des UE
     */
    private Reseau reseau;
    private ArrayList<UE> listUE;
    /**
     * HashMap des UE avec leur code comme clé
     */
    private  HashMap<String, UE> dict_UE;
    /**
     * Liste des utilisateurs qui se sont connectés au serveur
     */
    private ArrayList<User> listUsers;


    public SocketIOServer getServer() {
        return socketIOServer;
    }

    public ArrayList<UE> getListUE() {
        return listUE;
    }

    public HashMap<String, UE> getDict_UE() {
        return dict_UE;
    }

    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    public Serveur(Configuration configuration) {
        //Initialisation de la base de donnée
        BaseDonnee baseDonnee = new BaseDonnee();
        File s1 = new File("./serveur/target/generated-sources/semestre1.json");
        File s2 = new File("./serveur/target/generated-sources/semestre2.json");
        File s3 = new File("./serveur/target/generated-sources/semestre3.json");
        this.listUE = loadingUE(s1,s2,s3,baseDonnee);

        /**
         * Initialisation des Users
         */
        File utilisateurs = new File("./serveur/target/generated-sources/utilisateurs.json");
        this.listUsers = loadingUsers(utilisateurs);

        dict_UE = UtilServeur.initDictUE(this.listUE);

        this.socketIOServer = new SocketIOServer(configuration);
        this.reseau = new Reseau(socketIOServer, this);

    }

    private ArrayList<User> loadingUsers(File utilisateurs) {
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

    private ArrayList<UE> loadingUE(File s1, File s2, File s3, BaseDonnee baseDonnee){
        try {
            if (s1.createNewFile()){
                //Création des fichiers
                System.out.println("Création de la base de donnée.");
                UtilServeur.writeToJSON("semestre1.json", baseDonnee.getListeUES1());
                UtilServeur.writeToJSON("semestre2.json", baseDonnee.getListeUES2());
                UtilServeur.writeToJSON("semestre3.json", baseDonnee.getListeUES3());
                return UtilServeur.initListeUE(baseDonnee.getListeUES1(), baseDonnee.getListeUES2(), baseDonnee.getListeUES3());
            }
            else{
                System.out.println("Chargement des UE de la base de donnée...");
                ArrayList<UE> listeUES1 = UtilServeur.JSONFileToListUE(s1);
                ArrayList<UE> listeUES2 = UtilServeur.JSONFileToListUE(s2);
                ArrayList<UE> listeUES3 = UtilServeur.JSONFileToListUE(s3);
                return UtilServeur.initListeUE(listeUES1, listeUES2, listeUES3);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des UE");
            e.printStackTrace();
        }
        return null;
    }



    /**
     * On Ajoute le choix de la matière du client socketIOClient dans la liste (stockée in dans le serveur1) de ses choix
     * @param socketIOClient Le client qui envoie son choix
     * @param code_choix_matière Le code de la matière envoyée par le client
     */
    protected void save_code(SocketIOClient socketIOClient, String code_choix_matière) {
        System.out.println("Le client "+""+socketIOClient.getRemoteAddress()+" Enregistre l'UE de code : "+ code_choix_matière);
        UE ue = dict_UE.get(code_choix_matière);
        listUsers.get(0).getListe_choix().add(ue);
        System.out.println("Le serveur a enregistré "+ ue.getDiscipline() + " " + ue.getNomUE());
        socketIOClient.sendEvent(EVENT.SAVE);
    }

    private void démarre() {
        this.socketIOServer.start();
    }

    public static final void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setHostname("localhost");
        configuration.setPort(4444);


        /*Création du serveur*/
        Serveur server = new Serveur(configuration);
        server.démarre();
    }




}
