package serveur;

import com.corundumstudio.socketio.*;
import io.netty.channel.unix.Socket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ServeurTest {

    Serveur serveur;
    Configuration configuration = new Configuration();
    Socket client;

    @BeforeEach
    void setUp() {
        configuration.setHostname("localhost");
        configuration.setPort(4444);
        serveur = new Serveur(configuration);
        /*@TODO Doit-t-on faire unsocket client de cette manière pour tester le serveur
            Etant donné que les méthode prennent en paramètre un socketioclient
            En plus de ça le serveur une fois lancé le reste du code est en attente ...*/
        //client = IO.socket("http://127.0.0.1:4444");
    }

    @Test
    void save_code() {

    }

    @Test
    void ajouteUser() {
    }

    @Test
    void initParcoursUser() {
    }
}