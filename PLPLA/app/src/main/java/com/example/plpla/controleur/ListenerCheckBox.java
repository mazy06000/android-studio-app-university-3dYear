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

        // vérifie quelle checkbox a été cliquée.
        switch(v.getId()) {
            case R.id.radio_fondement:
                if (checked) {
                    Log.d("CHECKBOX", "Checkbox cochée : "+activity.getTextFondement().getText());
                    //activity.getCheckBox2().setClickable(false);
                    activity.getExpansionFondement().expand(true);
                    activity.getExpansionMethode().setEnabled(false);
                    activity.getRadioMethode().setEnabled(false);
                    activity.getEnregistrer().setEnabled(true);
                    activity.getSelectionItem().add(activity.getTextFondement().getText().toString());
                }
                else {
                    Log.d("CHECKBOX", "Checkbox décochée : "+activity.getTextFondement().getText());
                    //activity.getCheckBox2().setClickable(true);
                    activity.getExpansionFondement().expand(false);
                    activity.getExpansionMethode().setEnabled(true);
                    activity.getRadioMethode().setEnabled(true);
                    activity.getEnregistrer().setEnabled(false);
                    activity.getSelectionItem().remove(activity.getTextFondement().getText().toString());
                }
                break;

            case R.id.radio_methode:
                if (checked){
                    Log.d("CHECKBOX", "Checkbox cochée : "+activity.getTextMethode().getText());
                    //activity.getCheckBox1().setClickable(false);
                    activity.getExpansionMethode().expand(true);
                    activity.getExpansionFondement().setEnabled(false);
                    activity.getRadioMethode().setEnabled(false);
                    activity.getEnregistrer().setEnabled(true);
                    activity.getSelectionItem().add(activity.getTextMethode().getText().toString());
                }
                else {
                    Log.d("CHECKBOX", "Checkbox décochée : "+activity.getTextMethode().getText());
                    //activity.getCheckBox1().setClickable(true);
                    activity.getExpansionMethode().expand(false);
                    activity.getRadioFondement().setEnabled(true);
                    activity.getExpansionFondement().setEnabled(true);
                    activity.getEnregistrer().setEnabled(false);
                    activity.getSelectionItem().remove(activity.getTextMethode().getText().toString());
                }
                break;
        }

    }
}
