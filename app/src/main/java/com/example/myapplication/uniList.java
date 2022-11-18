
package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
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

public class uniList extends AppCompatActivity {

    String filter[] = {"Degree", "Ranking"};
    String sort[] = {"Ranking (Asc)", "Ranking (Dsc)"};
    uniinfo unilist[]; //= {new uniinfo("FAST-NU Lahore", R.drawable.fastnulhr), new uniinfo("LUMS", R.drawable.lums), new uniinfo("GIKI", R.drawable.giki)};



    Spinner s1, s2;
    String item;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    TextView tv, tvf, tvs;

    ListView listView;
    CardView c;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_list);
        s1 = findViewById(R.id.spinner3);
        s2 = findViewById(R.id.spinner2);
        tv = findViewById(R.id.status);
        tvf = findViewById(R.id.filter);
        tvs = findViewById(R.id.textsort);

        ArrayList<String> unis = (ArrayList<String>) getIntent().getSerializableExtra("arr");

        if(unis.isEmpty())
        {
            s1.setVisibility(View.INVISIBLE);
            s2.setVisibility(View.INVISIBLE);
            tvf.setVisibility(View.INVISIBLE);
            tvs.setVisibility(View.INVISIBLE);
            tv.setText("SORRY NOT ELIGIBLE IN ANY UNI");
            tv.setVisibility(View.VISIBLE);
        }
        else
        {
            s1.setVisibility(View.VISIBLE);
            s2.setVisibility(View.VISIBLE);
            tvf.setVisibility(View.VISIBLE);
            tvs.setVisibility(View.VISIBLE);
            adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filter);
            adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sort);
            s1.setAdapter(adapter1);
            s2.setAdapter(adapter2);

            unilist = new uniinfo[unis.size()];

            for (int z = 0; z < unis.size(); z++) {
                unilist[z] = new uniinfo(unis.get(z), R.drawable.fastnulhr);
            }


            listView = findViewById(R.id.unilist);
            adapterUniList ad = new adapterUniList(this, R.layout.uni_list_row, unilist);
            listView.setAdapter(ad);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    uniinfo u = (uniinfo) adapterView.getItemAtPosition(i);
                    Toast.makeText(uniList.this, "Item: " + u.uniName, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(uniList.this, uniPageStudent.class);
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

}


