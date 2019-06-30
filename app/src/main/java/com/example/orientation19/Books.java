package com.example.orientation19;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Books extends Fragment {
    ListView department;
    String[] array_department = {"ECE","EEE","MECH","ICE","CIVIL","CHEM","PROD","META","CSE"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        final String[] semester = {"Semester 1", "Semester 2"};
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                builder.setTitle("Choose Semester");
                builder.setItems(semester, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getContext(), semester[which], Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        department = view.findViewById(R.id.list_dept);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,array_department);
        department.setAdapter(arrayAdapter);

        department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dept = ((TextView) view).getText().toString();
                Intent intent = new Intent(getContext(),Book_links.class);
                intent.putExtra("department",dept);
                startActivity(intent);
            }
        });
        return view;


    }
}
