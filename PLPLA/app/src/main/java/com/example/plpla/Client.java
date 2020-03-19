package com.example.plpla;

import android.app.Application;


public class Client extends Application {

    private Connexion uniqueConnexion;

    @Override
    public void onCreate() {
        super.onCreate();
        /*Ici serveurAddress est redéfinit dans le constructeur il faudrait récuperer celui du MainActivity*/
        uniqueConnexion = new Connexion("A changer !!!");
    }

    public Connexion getUniqueConnexion() {
        return uniqueConnexion;
    }
}
