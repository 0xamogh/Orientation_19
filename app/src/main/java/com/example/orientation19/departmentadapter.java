package com.example.orientation19;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class departmentadapter extends ArrayAdapter<department> {
    public departmentadapter(Context context, ArrayList<department> departments){
        super(context,0,departments);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.departmentlist, parent, false);
        }

        department currentdepartment = getItem(position);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.txt_dept);
         nameTextView.setText(currentdepartment.getDept());
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.img_icon);

        iconView.setImageResource(currentdepartment.getMresource());

       return listItemView;
    }
}
