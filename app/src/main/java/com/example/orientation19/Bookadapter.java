package com.example.orientation19;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mlsdev.animatedrv.AnimatedRecyclerView;

import java.util.ArrayList;

public class Bookadapter extends AnimatedRecyclerView.Adapter<Bookadapter.MyViewHolder> implements View.OnClickListener {
    private LayoutInflater inflater;
    private ArrayList<Book> dataModelArrayList;

    public Bookadapter(Context ctx,ArrayList<Book> dataModelArrayList){
        inflater=LayoutInflater.from(ctx);
        this.dataModelArrayList=dataModelArrayList;
    }
    @NonNull
    @Override
    public Bookadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.rv_one,viewGroup,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Bookadapter.MyViewHolder holder, int i) {
        holder.name.setText(dataModelArrayList.get(i).getName());
        holder.department.setText(dataModelArrayList.get(i).getDepartment());
        holder.link.setText(dataModelArrayList.get(i).getLink());


    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class MyViewHolder extends AnimatedRecyclerView.ViewHolder{
        TextView department, name, link;


        public MyViewHolder(View itemView) {
            super(itemView);

            department = (TextView) itemView.findViewById(R.id.department);
            name = (TextView) itemView.findViewById(R.id.name);
            link = (TextView) itemView.findViewById(R.id.link);
            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(link.getText().toString());
                    Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                    v.getContext().startActivity(intent);
                }
            });

        }
    }
}
