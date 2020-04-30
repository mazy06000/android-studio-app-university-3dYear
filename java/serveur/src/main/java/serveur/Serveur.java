package serveur;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import events.EVENT;
import init.InitBD;
import matière.UE;
import user.User;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

import static serveur.BaseDonnee.*;

public class Serveur {


    private SocketIOServer socketIOServer;
    private Reseau reseau;
    /**
     * Liste des UE
     */
    private ArrayList<UE> listUE;
    /**
     * HashMap des UE avec leur code comme clé
     */
    private  HashMap<String, UE> dict_UE;
    /**
     * Liste des utilisateurs qui se sont connectés au serveur
     */
    private ArrayList<User> listUsers;
    /**
     * La base de donnée : écrit et lit les fichiers
     */
    private BaseDonnee baseDonnee;

    public void setListUsers(ArrayList<User> listUsers) {
        this.listUsers = listUsers;
    }
    public void setDict_UE(HashMap<String, UE> dict_UE) {
        this.dict_UE = dict_UE;
    }


    public BaseDonnee getBaseDonnee() {
        return baseDonnee;
    }

    public Serveur(SocketIOServer socketIOServer) {
        //Initialisation de la base de donnée
        baseDonnee = new BaseDonnee();

        this.listUE = baseDonnee.loadingUE();

        /**
         * Initialisation des Users
         */
        this.listUsers = baseDonnee.loadingUsers();

        dict_UE = UtilServeur.initDictUE(this.listUE);

        this.socketIOServer = socketIOServer;
        this.reseau = new Reseau(this);

    }


    /**
     * On Ajoute le choix de la matière du client socketIOClient dans la liste (stockée in dans le serveur1) de ses choix
     * @param user Le client qui envoie son choix
     * @param code_choix_matière Le code de la matière envoyée par le client
     */
    protected void save_code(User user, String code_choix_matière) {
        int index_user = listUsers.indexOf(user);
        if (index_user>=0){
            UE ue = dict_UE.get(code_choix_matière);
            listUsers.get(index_user).getListe_choix().add(ue);
            System.out.println("Le serveur a enregistré le choix "+ ue.getDiscipline() + " " + ue.getNomUE()+" du client "+user.getNom() + " listUE"+ listUsers.get(index_user).getListe_choix());
            reseau.sendSave(user);
        }
        else {
            System.out.println("Erreur lors l'enregistrement de la matière, l'utilisateur " +
                    user.getNom() +" n'a pas été trouvé dans le Serveur");
        }

    }


    /**
     * Ajoute un nouvel user (objet User) avec l'objet user envoyé par le client.
     * (Ou pas s'il existe deja dans la base de données (La liste des users connectés au moins une fois))
     */
    public void ajouteUser(User user) {
        System.out.println("Ajout d'un nouvel utilisateur : "+user.getNom() +" "+ user.getPrenom());
        if (UtilServeur.userExist(this.getListUsers(), user)){
            System.out.println(user.getNom()+" a déja été ajouté");
            reseau.sendListUE(user);
        }
        else {
            System.out.println("Ajout de "+user.getNom()+" avec succès !");
            User new_user = new User(user.getNom(), user.getPrenom(), user.getListe_choix());
            this.getListUsers().add(new_user);
            reseau.sendListUE(user);
        }
    }

    public void initParcoursUser(User user) {
        int index_user = listUsers.indexOf(user);
        if (index_user >= 0){
            this.getListUsers().get(index_user).getListe_choix().clear();
            System.out.println("Parcours réinitialisé pour "+user.getNom());
            reseau.sendInitParcours(user);
        }
        else {
            System.out.println("Erreur lors de la réinitialisation de parcours, l'utilisateur " +
                    user.getNom() +" n'a pas été trouvé dans le Serveur");
        }

    }

    private void démarre() {
        this.reseau.start();
    }

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



    public static final void main(String[] args) {
        InitBD initBD = new InitBD();
        initBD.main(null);
        Configuration configuration = new Configuration();
        configuration.setHostname("localhost");
        configuration.setPort(4444);


        /*Création du serveur*/
        Serveur server = new Serveur(new SocketIOServer(configuration));
        server.démarre();
    }


    public void enregistreSession(User user) {
        getBaseDonnee().saveUser(user);
    }
}
