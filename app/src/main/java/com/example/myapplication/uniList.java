package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class uniList extends AppCompatActivity {

    String unilist[] = {"FAST-NU Lahore", "LUMS", "GIKI", "NUST Islamabad"};
    String filter[] = {"Degree", "City", "Rating"};
    String sort[] = {"Ranking (Asc)", "Ranking (Dsc)"};
    Spinner s1, s2;
    String item;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    int unipic[] = {R.drawable.fastnulhr, R.drawable.lums, R.drawable.giki, R.drawable.nust};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_list);
        s1 = findViewById(R.id.spinner3);
        s2 = findViewById(R.id.spinner2);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filter);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sort);
        s1.setAdapter(adapter1);
        s2.setAdapter(adapter2);


        listView = findViewById(R.id.unilist);
        adapterUniList ad = new adapterUniList(getApplicationContext(), unilist, unipic);
        listView.setAdapter(ad);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
            //    Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}