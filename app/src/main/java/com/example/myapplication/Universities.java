package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Universities extends AppCompatActivity {

    String filter[] = {"Degree", "Ranking"};
    String sort[] = {"Ranking (Asc)", "Ranking (Dsc)"};
    uniinfo unilist[]; //= {new uniinfo("FAST-NU Lahore", R.drawable.fastnulhr), new uniinfo("LUMS", R.drawable.lums), new uniinfo("GIKI", R.drawable.giki)};



    Spinner s1, s2;
    String item;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ListView listView;
    CardView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);
        s1 = findViewById(R.id.spinner3);
        s2 = findViewById(R.id.spinner2);
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filter);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sort);
        s1.setAdapter(adapter1);
        s2.setAdapter(adapter2);

        ArrayList<String> unis = (ArrayList<String>) getIntent().getSerializableExtra("allunis");
        unilist = new uniinfo[unis.size()];
        for(int z = 0; z < unis.size(); z++)
        {
           // Toast.makeText(Universities.this,unis.get(z), Toast.LENGTH_SHORT).show();
            unilist[z] = new uniinfo(unis.get(z), R.drawable.fastnulhr);
        }


        listView = findViewById(R.id.unilist);
        adapterUniList ad = new adapterUniList(this, R.layout.uni_list_row, unilist);
        listView.setAdapter(ad);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                uniinfo u = (uniinfo) adapterView.getItemAtPosition(i);
                Toast.makeText(Universities.this, "Item: " + u.uniName, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Universities.this, uniPageStudent.class);
                in.putExtra("uniname", u.uniName);
                startActivity(in);
            }
        });


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                //    Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

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