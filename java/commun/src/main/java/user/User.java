package user;

import matière.UE;

import java.util.ArrayList;

public class User {
    private String Nom;
    private String prenom;
    private String address_ip;

    public ArrayList<UE> getListe_choix() {
        return liste_choix;
    }

    public void setListe_choix(ArrayList<UE> liste_choix) {
        this.liste_choix = liste_choix;
    }

    private ArrayList<UE> liste_choix;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddress_ip() {
        return address_ip;
    }

    public void setAddress_ip(String address_ip) {
        this.address_ip = address_ip;
    }

    /* A faire évoluer*/
    public User(){
        this.Nom = "Nom Par défaut";
        this.prenom = "prenom Par défaut";
    }

}
