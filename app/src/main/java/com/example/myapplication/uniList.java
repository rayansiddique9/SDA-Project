package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class uniList extends AppCompatActivity {

    String unilist[] = {"FAST-NU Lahore", "LUMS", "GIKI", "NUST Islamabad"};
    int unipic[] = {R.drawable.fastnulhr, R.drawable.lums, R.drawable.giki, R.drawable.nust};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_list);
        listView = findViewById(R.id.unilist);
        adapterUniList ad = new adapterUniList(getApplicationContext(), unilist, unipic);
        listView.setAdapter(ad);
    }
}