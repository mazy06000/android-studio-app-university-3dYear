package com.example.plpla.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.widget.Button;


import com.example.plpla.R;
import com.example.plpla.edit;

import java.util.Objects;


public class ToolsFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tools, container, false);


        SharedPreferences prefs = getActivity().getSharedPreferences("MY_DATA", getActivity().MODE_PRIVATE);

        String name = prefs.getString("MY_NAME", "no name");
        int tel = prefs.getInt("MY_TEL", 0);
        String email = prefs.getString("MY_EMAIL", "no email");

        // Set values
        ((TextView) root.findViewById(R.id.nameLabel)).setText(name);
        ((TextView) root.findViewById(R.id.telLabel)).setText(tel + "");
        ((TextView) root.findViewById(R.id.emailLabel)).setText(email);
        return root ;
    }

    public void edit(View view) {
        startActivity(new Intent(getActivity().getApplicationContext(), edit.class));
   }
}
