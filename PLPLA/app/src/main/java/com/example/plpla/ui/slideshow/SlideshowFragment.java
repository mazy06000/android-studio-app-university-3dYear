package com.example.plpla.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plpla.Matieres;
import com.example.plpla.R;
import com.example.plpla.RecyclerAdapter;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    RecyclerView recyclerView;

    public static RecyclerAdapter getRecyclerAapter() {
        return recyclerAapter;
    }

    static RecyclerAdapter recyclerAapter;
    List<String> exemple = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_recyclerview, container, false);
        setHasOptionsMenu(true);

        //////////////
        ArrayList<Matieres> okokokok=listesdesmatieres();
        ////////////////
        exemple.add("electonique");
        exemple.add("maths");
        exemple.add("anglais");
        exemple.add("histoire");
        exemple.add("physique");
        exemple.add("algo");


        recyclerView = root.findViewById(R.id.recyclerView);
        //recyclerAapter=new RecyclerAdapter(exemple);


        ///////////////////////////////////////////////
        recyclerAapter=new RecyclerAdapter(okokokok);
        ///////////////////////////////////////////////


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAapter);

        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);



        return root;
    }


    private ArrayList<Matieres> listesdesmatieres() {
        ArrayList<Matieres> list = new ArrayList<>();

        list.add(new Matieres("maths"));
        list.add(new Matieres("histoire"));
        list.add(new Matieres("anglais"));
        list.add(new Matieres("electronique"));

        return list;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_navigation, menu);
        MenuItem item  = menu.findItem(R.id.action_search);
        item.setVisible(true);
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
        super.onCreateOptionsMenu(menu,inflater);
    }

}