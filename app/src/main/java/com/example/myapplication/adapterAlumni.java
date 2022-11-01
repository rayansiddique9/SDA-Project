package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class alumniInfo
{
    String name;
    String company;
    String batch;
    alumniInfo(String n, String c, String b)
    {
        this.name = n;
        this.company = c;
        this.batch = b;
    }
}


public class adapterAlumni extends ArrayAdapter<alumniInfo> {



    public adapterAlumni(@NonNull Context context, int resource, @NonNull alumniInfo[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        alumniInfo obj = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.alumni_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView t1 = (TextView) convertView.findViewById(R.id.alumniName);
        TextView t2 = (TextView) convertView.findViewById(R.id.alumniCompany);
        TextView t3 = (TextView) convertView.findViewById(R.id.alumniBatch);
        t1.setText(obj.name);
        t2.setText(obj.company);
        t3.setText(obj.batch);

        return convertView;
    }

}
