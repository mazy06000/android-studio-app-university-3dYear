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

import java.util.ArrayList;
import java.util.HashMap;

public class Serveur {

    private final SocketIOServer server;
    private final ArrayList<UE> listUE;
    private final HashMap<String, UE> dict_UE;

    private ArrayList<User> listUsers;

    public Serveur(SocketIOServer server, ArrayList<UE> listUE, HashMap<String, UE> dict_UE) {
        this.server = server;
        /**
         * Liste des UE
         */
        this.listUE = listUE;
        /**
         * HashMap des UE avec leur code comme clé
         */
        this.dict_UE = dict_UE;
        /**
         * Liste des utilisateurs qui se sont connectés au serveur
         */
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
                if (userExist(listUsers, user)){
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

//        this.server.addEventListener(EVENT.ADD_USER, String.class, new DataListener<String>() {
//            @Override
//            public void onData(SocketIOClient socketIOClient, String user, AckRequest ackRequest) throws Exception {
//                System.out.println("Ajout de : "+user);
//            }
//        });

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
     * Cherche si un utilisateur s'est deja connecté une fois
     * @param listUsers Une liste d'UE
     * @param user Un User
     * @return Un booléen vrai si l'user est dans la liste, faux dans le cas contraire
     */
    private boolean userExist(ArrayList<User> listUsers, User user) {
        for (User utilisateur: listUsers) {
            if (utilisateur.equals(user)){
                return true;
            }
        }
        return false;
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

        /*Création du serveur*/
        SocketIOServer server = new SocketIOServer(configuration);
        ArrayList<UE> listeUE = Serveur.initListeUE();
        HashMap<String, UE> dict_UE = initDictUE(listeUE);

        Serveur serveur = new Serveur(server, listeUE, dict_UE);
        server.start();
    }

    /**
     * Fais une hashmap avec les code de matières comme clé et l'Objet UE qui correspond à la matière
     * @param listUE La liste des UE
     * @return HashMap de type <"CodeUE", UE>
     */
    private static HashMap<String, UE> initDictUE(ArrayList<UE> listUE) {
        HashMap<String, UE> dicoUE = new HashMap<>();
        for (UE ue : listUE) {
            dicoUE.put(ue.getCode(), ue);
        }
        return dicoUE;
    }

    /**initialisation des matières et de la liste des matières*/
    private static ArrayList<UE> initListeUE(){
        UE ueMath1 = new UE("SPUM13", "MATHS", "Complément 1", 1,6,100);
        UE ueMath2 = new UE("SPUM12", "MATHS", "Méthodes - approche continue", 1,6,280);
        UE ueMath3 = new UE("SPUM14", "MATHS", "Fondement 1", 1,6,100);
        ArrayList<UE> listUE = new ArrayList<>();
        listUE.add(ueMath1);
        listUE.add(ueMath2);
        listUE.add(ueMath3);
        return listUE;
    }

}
