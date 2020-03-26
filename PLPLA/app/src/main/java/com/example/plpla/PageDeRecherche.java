package com.example.plpla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class PageDeRecherche extends AppCompatActivity implements RecyclerAdapter.SelectedMatiere  {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAapter;
    List<String> exemple = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        //////////////
        ArrayList<Matieres> okokokok=listesdesmatieres();
        ////////////////

        recyclerView = findViewById(R.id.recyclerView);
        ///////////////////////////////////////////////
        recyclerAapter=new RecyclerAdapter(okokokok,this);
        ///////////////////////////////////////////////


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAapter);

        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    //////////////////////////////
    private ArrayList<Matieres> listesdesmatieres() {
        ArrayList<Matieres> list = new ArrayList<>();

        list.add(new Matieres("maths", "Mathematique L1 General"));
        list.add(new Matieres("histoire", "Histoire des epoques anciennes"));
        list.add(new Matieres("anglais", "Langue Vivante generaliser "));
        list.add(new Matieres("electronique", "l'electronique de la vie "));

        return list;
    }
    //////////////////////////////////////////////////


    @Override
    public void selectedMatiere(Matieres matiere) {

        startActivity(new Intent(PageDeRecherche.this, SelectedMatiereActivity.class).putExtra("data", matiere));



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_navigation, menu);
        MenuItem item  = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_slideshow){
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}
