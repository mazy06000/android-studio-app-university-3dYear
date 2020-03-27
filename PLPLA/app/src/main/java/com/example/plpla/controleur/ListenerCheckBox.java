package com.example.plpla.controleur;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.plpla.MainNavigation;
import com.example.plpla.R;
import com.example.plpla.ui.home.HomeFragment;

import io.socket.client.Socket;

public class ListenerCheckBox implements View.OnClickListener {

    private final Socket socket;
    private HomeFragment activity;


    public ListenerCheckBox(Socket socket, HomeFragment activity) {
        this.socket = socket;
        this.activity = activity;
    }



    @Override
    public void onClick(View v) {
        // vérifie que la checkbox est cochée
        boolean checked = ((CheckBox) v).isChecked();


    }
}
