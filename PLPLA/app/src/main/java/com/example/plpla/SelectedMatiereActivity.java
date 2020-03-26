package com.example.plpla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedMatiereActivity extends AppCompatActivity {

    TextView tvMatiere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_matiere);

        tvMatiere = findViewById(R.id.selectedMatiere);

        Intent intent =getIntent();

        if(intent.getExtras() != null){
            Matieres laMatiere = (Matieres) intent.getSerializableExtra("data");

            tvMatiere.setText(laMatiere.getDescription());
        }

    }
}
