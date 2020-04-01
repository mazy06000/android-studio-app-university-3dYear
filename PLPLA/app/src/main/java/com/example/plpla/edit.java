package com.example.plpla;

import android.widget.EditText;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.plpla.ui.tools.ProfilFragment;

public class edit extends AppCompatActivity {
    private EditText nameInput;
    private EditText telInput;
    private EditText emailInput;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        String name = prefs.getString("MY_NAME", "");
        int tel = prefs.getInt("MY_TEL", 0);
        String email = prefs.getString("MY_EMAIL", "");

        nameInput = findViewById(R.id.nameInput);
        telInput = findViewById(R.id.telInput);
        emailInput = findViewById(R.id.emailInput);

        // Set default value.
        nameInput.setText(name);
        telInput.setText(tel+"");
        emailInput.setText(email);

    }

    public void saveData(View view) {
        // Get input text.
        String name = nameInput.getText().toString();
        int tel = Integer.parseInt(telInput.getText().toString());
        String email = emailInput.getText().toString();

        // Save data.
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("MY_NAME", name);
        editor.putInt("MY_TEL", tel);
        editor.putString("MY_EMAIL", email);
        editor.apply();

        // Return to main activity.
        startActivity(new Intent(getApplicationContext(), ProfilFragment.class));

    }
}
