package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class educationalbg_fsc extends AppCompatActivity {


    Button b;
    ArrayAdapter<String> adapter;
    String []arr = {"Pre Medical", "Pre Engineering", "ICS","FA","Business"};
    Spinner acc;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educationalbg_fsc);

        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent in = new Intent(educationalbg_fsc.this, MainActivity.class);
                startActivity(in);
            }
        });


    }
}