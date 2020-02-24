package com.example.plpla.controleur;

import android.util.Log;
import android.view.View;

import com.example.plpla.R;

import io.socket.client.Socket;

public class ListenerButton implements View.OnClickListener{

    private final Socket socket;

    public ListenerButton(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BoutonSemestre:
                Log.d("Bouton semestre", "Pression sur le bouton semestre");
                socket.emit("touche");
                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}