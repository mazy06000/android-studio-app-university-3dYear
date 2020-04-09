package user;

import matière.UE;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class User implements ToJSON {
    private String Nom;
    private String prenom;
    private String address_ip;
    private ArrayList<UE> liste_choix;

    public ArrayList<UE> getListe_choix() {
        return liste_choix;
    }

    public void setListe_choix(ArrayList<UE> liste_choix) {
        this.liste_choix = liste_choix;
    }

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

    public User(){
//        Nom = "Baroudi";
//        prenom ="Ibrahim";
//        address_ip = "none";
//        liste_choix = new ArrayList<UE>();
        this("defaultName", "defaultSurname", "none", new ArrayList<UE>());
    }


    public User(String nom, String prenom, String address_ip, ArrayList<UE> liste_choix) {
        Nom = nom;
        this.prenom = prenom;
        this.address_ip = address_ip;
        this.liste_choix = liste_choix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getNom().equals(user.getNom()) &&
                getPrenom().equals(user.getPrenom()) &&
                getAddress_ip().equals(user.getAddress_ip()) &&
                getListe_choix().equals(user.getListe_choix());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getPrenom(), getAddress_ip(), getListe_choix());
    }

    @Override
    public String toString() {
        return "User{" +
                "Nom='" + Nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", address_ip='" + address_ip + '\'' +
                '}';
    }

    @Override
    public JSONObject toJSON() {
        JSONObject user = new JSONObject();
        try {
            user.put("nom", getNom());
            user.put("prenom", getPrenom());
            user.put("address_ip", getAddress_ip());
            //user.put("liste_choix", getListe_choix());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
