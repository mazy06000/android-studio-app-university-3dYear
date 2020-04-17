package com.example.plpla.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.plpla.R;
import com.example.plpla.edit;

import java.util.ArrayList;
import java.util.List;

public class ShareFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.partage_windows_list, container, false);


        ListView listView = (ListView) root.findViewById(R.id.listView);

        ArrayList<String> items = new ArrayList<String>();

        PartagerAdapter adapter = new PartagerAdapter(items);
        listView.setAdapter(adapter);

        listView.setEmptyView(root.findViewById(R.id.emptyElement));




        return root;
    }

}