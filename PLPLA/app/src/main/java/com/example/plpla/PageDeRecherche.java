package com.example.plpla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageDeRecherche extends AppCompatActivity  {

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

        exemple.add("electonique");
        exemple.add("maths");
        exemple.add("anglais");
        exemple.add("histoire");
        exemple.add("physique");
        exemple.add("algo");


        recyclerView = findViewById(R.id.recyclerView);
        //recyclerAapter=new RecyclerAdapter(exemple);


        ///////////////////////////////////////////////
        recyclerAapter=new RecyclerAdapter(okokokok);
        ///////////////////////////////////////////////


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAapter);

        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    //////////////////////////////
    private ArrayList<Matieres> listesdesmatieres() {
        ArrayList<Matieres> list = new ArrayList<>();

        list.add(new Matieres("maths"));
        list.add(new Matieres("histoire"));
        list.add(new Matieres("anglais"));
        list.add(new Matieres("electronique"));

        return list;
    }
    //////////////////////////////////////////////////

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
}
