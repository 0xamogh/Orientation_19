package com.example.orientation19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class contacts extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        TextView txt_sahil=(TextView)view.findViewById(R.id.txt_sahil);
        TextView txt_diksha=(TextView)view.findViewById(R.id.txt_diksha);
        TextView txt_neel=(TextView)view.findViewById(R.id.txt_neel);
        TextView txt_nilesh=(TextView)view.findViewById(R.id.txt_nilesh);
        txt_sahil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7973703540"));
                startActivity(intent);
            }
        });


        txt_diksha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:700807140"));
                startActivity(intent);
            }
        });


        txt_neel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7558722946"));
                startActivity(intent);
            }
        });


        txt_nilesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8602520816"));
                startActivity(intent);
            }
        });
        return view;
    }
}

