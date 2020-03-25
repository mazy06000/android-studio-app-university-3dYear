package com.example.plpla;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Connexion {

    private Socket mSocket;
    private String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }

    public Socket getmSocket() {
        return mSocket;
    }


    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Connexion(){

    }

    public void initConnexion(){
        try {
            //Si vous utilisez l'emulateur, utilisez la ligne suivante
            // serverAddress = "http://10.0.2.2:4444";
            //Sinon remplacez par l'addresse IP de votre serveur (votre pc normalement)
            //serverAddress = "http://192.168.0.23:4444";
            /*Il y des problèmes de connection lorsqu'on utilise pas un émulateur*/
            /*Pour l'instant on redéfinit serverAddress*/
            //serverAddress = "http://10.0.2.2:4444";
            mSocket = IO.socket(serverAddress);
            mSocket.on("Saved", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    // @TODO faire afficher un message (Toast)
                }
            });

            mSocket.on("INIT_PARCOURS", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                   // @TODO faire afficher un message (Toast)
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void connecte(){
        System.out.println("--Connexion au server--");
        mSocket.connect();
    }

    public void envoyerEvent(String evenement){
        mSocket.emit(evenement);
    }

    public void deconnecte(){
        if (mSocket != null) mSocket.disconnect();
    }

}
