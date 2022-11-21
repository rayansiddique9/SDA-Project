package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class adapterReview extends ArrayAdapter<String> {
    String []reviews;

    public adapterReview(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String obj = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_list_row, parent, false);
        }

        // all views defined in list cell design
        TextView t1 = (TextView) convertView.findViewById(R.id.review);
        t1.setText(obj);

        return convertView;
    }
}
