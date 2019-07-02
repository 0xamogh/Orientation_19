package com.example.orientation19;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ResourceBundle;

public class t_shirt_reg extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_shirt_reg, container, false);
        Spinner spinner=(Spinner)view.findViewById(R.id.spn_size);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.activity_list_item_1, getResources().getStringArray(R.array.spinner_names));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        return view;


    }
}
