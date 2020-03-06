package com.example.plpla.controleur;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.plpla.HomeActivity;
import com.example.plpla.R;

import io.socket.client.Socket;

public class ListenerCheckBox implements View.OnClickListener {

    private final Socket socket;
    private HomeActivity activity;


    public ListenerCheckBox(Socket socket, HomeActivity activity) {
        this.socket = socket;
        this.activity = activity;
    }



    @Override
    public void onClick(View v) {
        // vérifie que la checkbox est cochée
        boolean checked = ((CheckBox) v).isChecked();

        // vérifie quelle checkbox a été cliquée.
        switch(v.getId()) {
            case R.id.checkBoxEmplacement1:
                if (checked) {
                    Log.d("CHECKBOX", "Checkbox cochée : "+activity.getTextView1().getText());
                    //activity.getCheckBox2().setClickable(false);
                    activity.getCheckBox2().setEnabled(false);
                    activity.getEnregistrer().setEnabled(true);
                    activity.getSelectionItem().add(activity.getTextView1().getText().toString());
                }
                else {
                    Log.d("CHECKBOX", "Checkbox décochée : "+activity.getTextView1().getText());
                    //activity.getCheckBox2().setClickable(true);
                    activity.getCheckBox2().setEnabled(true);
                    activity.getEnregistrer().setEnabled(false);
                    activity.getSelectionItem().remove(activity.getTextView1().getText().toString());
                }
                break;

            case R.id.checkBoxEmplacement2:
                if (checked){
                    Log.d("CHECKBOX", "Checkbox cochée : "+activity.getTextView2().getText());
                    //activity.getCheckBox1().setClickable(false);
                    activity.getCheckBox1().setEnabled(false);
                    activity.getEnregistrer().setEnabled(true);
                    activity.getSelectionItem().add(activity.getTextView2().getText().toString());
                }
                else {
                    Log.d("CHECKBOX", "Checkbox décochée : "+activity.getTextView2().getText());
                    //activity.getCheckBox1().setClickable(true);
                    activity.getCheckBox1().setEnabled(true);
                    activity.getEnregistrer().setEnabled(false);
                    activity.getSelectionItem().remove(activity.getTextView1().getText().toString());
                }
                break;
        }

    }
}
