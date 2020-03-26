package com.example.plpla.controleur;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.plpla.R;
import com.example.plpla.ui.home.PortailFragment;

import io.socket.client.Socket;

public class ListenerCheckBox implements View.OnClickListener {

    private final Socket socket;
    private PortailFragment activity;


    public ListenerCheckBox(Socket socket, PortailFragment activity) {
        this.socket = socket;
        this.activity = activity;
    }



    @Override
    public void onClick(View v) {
        // vérifie que la checkbox est cochée
        boolean checked = ((CheckBox) v).isChecked();

        boolean choixMatiereDansModule1 =false ;

        // vérifie quelle checkbox a été cliquée.
        switch(v.getId()) {
            case R.id.radio_fondement:
                if (checked) {
                    Log.d("CHECKBOX", "Checkbox cochée : "+activity.getTextFondement().getText());
                    //activity.getCheckBox2().setClickable(false);
                    activity.getExpansionFondement().toggle(true);
                    /*activity.getExpansionMethode().setEnabled(false);
                    activity.getRadioMethode().setEnabled(false);
                    activity.getEnregistrer().setEnabled(true);
                    activity.getSelectionItem().add(activity.getTextFondement().getText().toString());*/
                }
                else {
                    Log.d("CHECKBOX", "Checkbox décochée : "+activity.getTextFondement().getText());
                    //activity.getCheckBox2().setClickable(true);
                    activity.getExpansionFondement().toggle(true);
                    /*activity.getExpansionMethode().setEnabled(true);
                    activity.getRadioMethode().setEnabled(true);
                    activity.getEnregistrer().setEnabled(false);
                    activity.getSelectionItem().remove(activity.getTextFondement().getText().toString());*/
                }
                break;

            case R.id.radio_methode:
                if (checked){
                    Log.d("CHECKBOX", "Checkbox cochée : "+activity.getTextMethode().getText());
                    //activity.getCheckBox1().setClickable(false);
                    activity.getExpansionMethode().toggle(true);
               /*     activity.getExpansionFondement().setEnabled(false);
                    activity.getRadioFondement().setEnabled(false);
                    activity.getEnregistrer().setEnabled(true);
                    activity.getSelectionItem().add(activity.getTextMethode().getText().toString());*/
                }
                else {
                    Log.d("CHECKBOX", "Checkbox décochée : "+activity.getTextMethode().getText());
                    //activity.getCheckBox1().setClickable(true);
                    activity.getExpansionMethode().toggle(true);
               /*     activity.getRadioFondement().setEnabled(true);
                    activity.getExpansionFondement().setEnabled(true);
                    activity.getEnregistrer().setEnabled(false);
                    activity.getSelectionItem().remove(activity.getTextMethode().getText().toString());*/
                }
                break;
//            case R.id.checkBoxChimieStructMicro:
//                if (checked){
//                    Log.d("CHECKBOX", "Checkbox structure Chimie cochée : "+activity.getStructMicro().getText());
//                    //activity.getCheckBox1().setClickable(false);
//                    activity.getEnregistrer().setEnabled(true);
//                    activity.getSelectionItem().add(activity.getStructMicro().getText().toString());
//                }
//                else {
//                    Log.d("CHECKBOX", "Checkbox structure Chimie cochée  : "+activity.getStructMicro().getText());
//                    //activity.getCheckBox1().setClickable(true);
//
//                    activity.getEnregistrer().setEnabled(false);
//                    activity.getSelectionItem().remove(activity.getStructMicro().getText().toString());
//                }
//                break;
        }



    }
}
