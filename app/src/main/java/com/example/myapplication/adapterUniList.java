package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class adapterUniList extends BaseAdapter {

    Context context;
    String[] listuni;
    int[] picuni;
    LayoutInflater inflator;



    public adapterUniList(Context ctx, String[] unilist, int[] unipic)
    {
        this.context = ctx;
        this.listuni = unilist;
        this.picuni = unipic;
        inflator = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
        return listuni.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        v = inflator.inflate(R.layout.activity_uni_list_row, null);
        TextView tview = (TextView) v.findViewById(R.id.uniname);
        ImageView iview = (ImageView) v.findViewById(R.id.unipicture);
        tview.setText(listuni[i]);
        iview.setImageResource(picuni[i]);
        return v;
    }
}
