package serveur;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import matière.UE;

import java.util.ArrayList;

public class Serveur {

    private final SocketIOServer server;
    private final ArrayList<UE> listUE;


    public Serveur(SocketIOServer server, ArrayList<UE> listUE) {
        this.server = server;
        this.listUE = listUE;

        this.server.addEventListener("touche", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                traitement(socketIOClient);
            }
        });
    }

    protected void traitement(SocketIOClient socketIOClient) {
        System.out.println("Le client clique sur Semestre 1");
    }


    public static final void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setHostname("127.0.0.1");
        configuration.setPort(4444);

        /*Création du serveur*/
        SocketIOServer server = new SocketIOServer(configuration);

        Serveur serveur = new Serveur(server, Serveur.initListeUE());
        server.start();


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
