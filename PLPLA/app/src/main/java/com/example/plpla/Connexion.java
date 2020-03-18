package com.example.plpla;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class Connexion {

    private Socket mSocket;
    private String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Connexion(String serverAddress){
        this.serverAddress = serverAddress;
        initConnexion();
    }

    public void initConnexion(){
        try {
            /*Il y des problèmes de connection lorsqu'on utilise pas un émulateur*/
            /*Pour l'instant on redéfinit serverAddress*/
            serverAddress = "http://10.0.2.2:4444";
            mSocket = IO.socket(serverAddress);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void connecte(){
        mSocket.connect();
    }

    public void envoyerEvent(String evenement){
        mSocket.emit(evenement);
    }

    public void deconnecte(){
        if (mSocket != null) mSocket.disconnect();
    }
}
