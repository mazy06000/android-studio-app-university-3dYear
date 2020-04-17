package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import events.EVENT;
import org.graalvm.compiler.lir.LIRInstruction;
import user.User;

import java.util.HashMap;
import java.util.Map;

public class Reseau {
    private SocketIOServer socket;
    private Serveur serveur;
    private HashMap<User, SocketIOClient> dict_client;

    public SocketIOServer getSocket() {
        return socket;
    }

    public void setSocket(SocketIOServer socket) {
        this.socket = socket;
    }

    public Serveur getServeur() {
        return serveur;
    }

    public void setServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    public Reseau(Serveur serveur){
        dict_client = new HashMap<>();
        this.serveur = serveur;
        this.socket = serveur.getServer();


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
                serveur.ajouteUser(user);
            }
        });

        /**
         * Le client enregistre une matière de code code_choix_matière
         */
        this.socket.addEventListener(EVENT.SAVE, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String code_choix_matière, AckRequest ackRequest) throws Exception {
                User client = leClient(socketIOClient);
                serveur.save_code(client, code_choix_matière);
            }
        });

        /**
         * Le client reinitialise son parcours :
         * on clear la liste de ses choix
         */
        this.socket.addEventListener(EVENT.INIT_PARCOURS, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                serveur.initParcoursUser(socketIOClient);
            }
        });

        this.socket.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                serveur.enregistreSession();
            }
        });

    }

    private User leClient(SocketIOClient socketIOClient) {
        for (Map.Entry<User, SocketIOClient> pair : dict_client.entrySet()) {
            if (socketIOClient.equals(pair.getValue())){
                return pair.getKey();
            }
        }
        return null;
    }


    /**
     * Envoie l'event INIT_PARCOURS au client
     * @param socketIOClient
     */
    public void sendInitParcours(SocketIOClient socketIOClient) {
        socketIOClient.sendEvent(EVENT.INIT_PARCOURS);
    }


    /**
     * Envoie l'adresse ip à l'utilisateur.
     * @param socketIOClient L'utilisateur
     */
    public void sendRemoteAddressUser(SocketIOClient socketIOClient) {
        socketIOClient.sendEvent(EVENT.ADD_USER, socketIOClient.getRemoteAddress());
    }

    public void associe(User user, SocketIOClient socketIOClient){
        dict_client.put(user, socketIOClient);
    }

    public void start() {
        socket.start();
    }


    public void sendEvent(String event, User user) {
        SocketIOClient socketIOClient = dict_client.get(user);
        socketIOClient.sendEvent(event);
    }
}
