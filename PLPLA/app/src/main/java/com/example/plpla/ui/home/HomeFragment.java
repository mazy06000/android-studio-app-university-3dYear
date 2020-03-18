package com.example.plpla.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.plpla.CourseActivity;
import com.example.plpla.R;
import com.example.plpla.controleur.ListenerButton;
import com.example.plpla.controleur.ListenerCheckBox;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private Button bouton;
    private CheckBox checkBox1;

    private CheckBox checkBox2;
    private TextView textView1;
    private TextView textView2;
    private TextView accordeon;
    private Socket socket;
    private Button Enregistrer ;

    public static ArrayList<String> getSelectionItem() {
        return selectionItem;
    }

    private static ArrayList<String> selectionItem = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        bouton = root.findViewById(R.id.BoutonSemestre);
        checkBox1 = root.findViewById(R.id.checkBoxEmplacement1);
        checkBox2 = root.findViewById(R.id.checkBoxEmplacement2);
        textView1 = root.findViewById(R.id.emplacement1S1);
        textView2 = root.findViewById(R.id.emplacement2S1);
        accordeon = root.findViewById(R.id.accordeonsPlus);
        Enregistrer = root.findViewById(R.id.Enregistrer);

        /*On les mets Invisibles ici pour faciliter les design*/
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        checkBox1.setVisibility(View.INVISIBLE);
        checkBox2.setVisibility(View.INVISIBLE);
        Enregistrer.setVisibility(View.VISIBLE);

        /*Par défaut Enregistrer n'est pas cliquable*/
        Enregistrer.setClickable(false);
        Enregistrer.setEnabled(false);


        ListenerButton listenerButton = new ListenerButton(socket,this);
        ListenerCheckBox listenerCheckBox = new ListenerCheckBox(socket, this);
        bouton.setOnClickListener(listenerButton);
        checkBox1.setOnClickListener(listenerCheckBox);
        checkBox2.setOnClickListener(listenerCheckBox);
        Enregistrer.setOnClickListener(listenerButton);

        return root;
    }




    public CheckBox getCheckBox1() {
        return checkBox1;
    }

    public CheckBox getCheckBox2() {
        return checkBox2;
    }

    public TextView getTextView1() {
        return textView1;
    }

    public TextView getTextView2() {
        return textView2;
    }

    public TextView getAccordeon() {
        return accordeon;
    }

    public Button getEnregistrer() {
        return Enregistrer;
    }


}