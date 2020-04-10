package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import events.EVENT;
import io.netty.channel.unix.Socket;
import user.User;

public class Reseau {
    private SocketIOServer socket;
    private Serveur serveur;

    public Reseau(SocketIOServer socket, Serveur serveur){
        this.socket = socket;
        this.serveur = serveur;

        // on accept une connexion
        this.socket.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("Connexion de "+socketIOClient.getRemoteAddress());
            }
        });


        /**
         * Listener de l'event ADD_USER :
         * Créé un nouvel user (objet User) avec l'objet user envoyé par le client.
         * (Ou pas s'il existe deja dans la base de données (La liste des users connectés au moins une fois))
         */
        this.socket.addEventListener(EVENT.ADD_USER, User.class, new DataListener<User>() {
            @Override
            public void onData(SocketIOClient socketIOClient, User user, AckRequest ackRequest) throws Exception {
                System.out.println("Ajout d'un nouvel utilisateur : "+user.getNom() +" "+ user.getPrenom());
                if (UtilServeur.userExist(serveur.getListUsers(), user)){
                    System.out.println(user.getNom()+" a déja été ajouté");
                }
                else {
                    System.out.println("Ajout de "+user.getNom()+" avec succès !");
                    User new_user = new User(user.getNom(), user.getPrenom(), user.getAddress_ip(), user.getListe_choix());
                    user.setAddress_ip(socketIOClient.getRemoteAddress().toString());
                    serveur.getListUsers().add(new_user);
                    System.out.println("user.getAddress_ip => "+ user.getAddress_ip());
                    System.out.println("Envoi de l'addresse ip à l'utilisateur "+user.getNom());
                    socketIOClient.sendEvent(EVENT.ADD_USER, socketIOClient.getRemoteAddress());
                }
            }
        });

        /**
         * Le client enregistre une matière de code code_choix_matière
         */
        this.socket.addEventListener(EVENT.SAVE, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String code_choix_matière, AckRequest ackRequest) throws Exception {
                serveur.save_code(socketIOClient, code_choix_matière);
            }
        });

        /**
         * Le client reinitialise son parcours :
         * on clear la liste de ses choix
         */
        this.socket.addEventListener(EVENT.INIT_PARCOURS, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                serveur.getListUsers().get(0).getListe_choix().clear();
                System.out.println("Parcours réinitialisé pour "+socketIOClient.getRemoteAddress());
                socketIOClient.sendEvent(EVENT.INIT_PARCOURS);
            }
        });


    }
}
