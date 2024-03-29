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

import java.util.ArrayList;

public class Books extends Fragment {
    ListView department;
    String[] array_department = {"CHEM","CIVIL","CSE","ECE","EEE","ICE","MECH","META","PROD"};
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

        final ArrayList<department> departments = new ArrayList<department>();
        departments.add(new department(array_department[0],  R.drawable.chem));
        departments.add(new department(array_department[1],  R.drawable.civil));
        departments.add(new department(array_department[2],  R.drawable.cse));
        departments.add(new department(array_department[3],  R.drawable.ece));
        departments.add(new department(array_department[4],  R.drawable.eee));
        departments.add(new department(array_department[5],  R.drawable.ice));
        departments.add(new department(array_department[6],  R.drawable.mech));
        departments.add(new department(array_department[7],  R.drawable.prod));

        departmentadapter flavorAdapter = new departmentadapter(view.getContext(), departments);
        department.setAdapter(flavorAdapter);

        department.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Intent intent = new Intent(getContext(),Book_links.class);
                intent.putExtra("department",array_department[i]);
                startActivity(intent);
            }
        });
        return view;


    }
}
