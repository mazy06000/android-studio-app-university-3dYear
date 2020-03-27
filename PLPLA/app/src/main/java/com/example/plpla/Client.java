package com.example.plpla;

import android.app.Application;

import java.util.ArrayList;

import matière.UE;
import user.User;


public class Client extends Application {

    private Connexion uniqueConnexion;
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        /*on initialise l'addresse du serveur dans le MainActivity avec le setServerAddress*/
        uniqueConnexion = new Connexion();
        user = new User();
    }

    public Connexion getUniqueConnexion() {
        return uniqueConnexion;
    }

    public User getUser() {
        return user;
    }
}
