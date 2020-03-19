package serveur;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
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
        this.listUE = listUE;
        this.dict_UE = dict_UE;

        // on accept une connexion
        this.server.addConnectListener(new ConnectListener() {
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("connexion de "+socketIOClient.getRemoteAddress());


            }
        });

        this.server.addEventListener("Save", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                traitement(socketIOClient, s);
            }
        });
    }

    protected void traitement(SocketIOClient socketIOClient, String choix_matière) {
        System.out.println("Le client "+""+socketIOClient.getRemoteAddress()+" Enregistre : "+ choix_matière);
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

    private static HashMap<String, UE> initDictUE(ArrayList<UE> listUE) {
        HashMap<String, UE> dicoUE = new HashMap<>();
        for (UE ue : listUE) {
            dicoUE.put(ue.getCode(), ue);
        }
        return dicoUE;
    }

    /*initialisation des matières et de la liste des matières*/
    private static ArrayList<UE> initListeUE(){
        UE ueMath1 = new UE("SPUM13", "MATHS", "Complément 1", 1,6,100);
        UE ueMath2 = new UE("SPUM12", "MATHS", "Méthodes - approche continue", 1,6,280);
        ArrayList<UE> listUE = new ArrayList<>();
        listUE.add(ueMath1);
        listUE.add(ueMath2);
        return listUE;
    }

}
