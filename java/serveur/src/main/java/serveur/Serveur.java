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
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

public class Serveur {

    private final SocketIOServer server;
    /**
     * Liste des UE
     */
    private final ArrayList<UE> listUE;
    /**
     * HashMap des UE avec leur code comme clé
     */
    private final HashMap<String, UE> dict_UE;
    /**
     * Liste des utilisateurs qui se sont connectés au serveur
     */
    private ArrayList<User> listUsers;


    public Serveur(SocketIOServer server, ArrayList<UE> listUE, HashMap<String, UE> dict_UE) {
        this.server = server;

        this.listUE = listUE;

        this.dict_UE = dict_UE;

        this.listUsers = new ArrayList<User>();

        // on accept une connexion
        this.server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("Connexion de "+socketIOClient.getRemoteAddress());
            }
        });


        /**
         * Listener de l'event ADD_USER :
         * Créé un nouvel user (objet User) avec l'objet user envoyé par le client.
         * (Ou pas s'il existe deja dans la base de données (La liste des users connectés au moins une fois))
         */
        this.server.addEventListener(EVENT.ADD_USER, User.class, new DataListener<User>() {
            @Override
            public void onData(SocketIOClient socketIOClient, User user, AckRequest ackRequest) throws Exception {
                System.out.println("Ajout d'un nouvel utilisateur : "+user.getNom() +" "+ user.getPrenom());
                if (UtilServeur.userExist(listUsers, user)){
                    System.out.println(user.getNom()+" a déja été ajouté");
                }
                else {
                    System.out.println("Ajout de "+user.getNom()+" avec succès !");
                    User new_user = new User(user.getNom(), user.getPrenom(), user.getAddress_ip(), user.getListe_choix());
                    user.setAddress_ip(socketIOClient.getRemoteAddress().toString());
                    listUsers.add(new_user);
                    System.out.println("user.getAddress_ip => "+ user.getAddress_ip());
                    System.out.println("Envoi de l'addresse ip à l'utilisateur "+user.getNom());
                    socketIOClient.sendEvent(EVENT.ADD_USER, socketIOClient.getRemoteAddress());
                }
            }
        });

        /**
         * Le client enregistre une matière de code code_choix_matière
         */
        this.server.addEventListener(EVENT.SAVE, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String code_choix_matière, AckRequest ackRequest) throws Exception {
                save_code(socketIOClient, code_choix_matière);
            }
        });

        /**
         * Le client reinitialise son parcours :
         * on clear la liste de ses choix
         */
        this.server.addEventListener(EVENT.INIT_PARCOURS, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                listUsers.get(0).getListe_choix().clear();
                System.out.println("Parcours réinitialisé pour "+socketIOClient.getRemoteAddress());
                socketIOClient.sendEvent(EVENT.INIT_PARCOURS);
            }
        });

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


    public static final void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setHostname("localhost");
        configuration.setPort(4444);
        ArrayList<UE> listeUE = null;

        //Initialisation de la base de donnée
        BaseDonnee baseDonnee = new BaseDonnee();
        File s1 = new File("./serveur/target/generated-sources/semestre1.json");
        File s2 = new File("./serveur/target/generated-sources/semestre2.json");
        File s3 = new File("./serveur/target/generated-sources/semestre3.json");
        try {
            if (s1.createNewFile()){ //Chargement des fichiers
                //Création des fichiers
                UtilServeur.writeToJSON("semestre1.json", baseDonnee.getListeUES1());
                UtilServeur.writeToJSON("semestre2.json", baseDonnee.getListeUES2());
                UtilServeur.writeToJSON("semestre3.json", baseDonnee.getListeUES3());
                listeUE = UtilServeur.initListeUE(baseDonnee.getListeUES1(), baseDonnee.getListeUES2(), baseDonnee.getListeUES3());
            }
            else{
                System.out.println("Chargement des UE ...");
                ArrayList<UE> listeUES1 = UtilServeur.JSONFileToListUE(s1);
                ArrayList<UE> listeUES2 = UtilServeur.JSONFileToListUE(s2);
                ArrayList<UE> listeUES3 = UtilServeur.JSONFileToListUE(s3);
                listeUE = UtilServeur.initListeUE(listeUES1, listeUES2, listeUES3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Création du serveur*/
        SocketIOServer server = new SocketIOServer(configuration);
        HashMap<String, UE> dict_UE = UtilServeur.initDictUE(listeUE);

        //Serveur serveur = new Serveur(server, listeUE, dict_UE);
        //server.start();
    }




}
