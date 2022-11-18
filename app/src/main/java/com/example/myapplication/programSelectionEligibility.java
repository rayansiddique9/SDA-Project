package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class programSelectionEligibility extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    String []arr = {"BSCS", "BSSE", "BSDS"};
    String []arr1 = {"MSCS", "MSSE", "MSDS"};
    Spinner acc;
    Button b;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_selection_eligibility);

        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   String st = getIntent().getExtras().getString("str");
            //    Toast.makeText(getApplicationContext(),"Item: "+st, Toast.LENGTH_SHORT).show();

              /*  if(Objects.equals(st, "Bachelors")) {
                    Intent in = new Intent(programSelectionEligibility.this, educationalbg_fsc.class);
                    startActivity(in);

                }
                else if(Objects.equals(st, "Masters"))
                {
                    Intent in = new Intent(programSelectionEligibility.this, educationalBgGrad.class);
                    startActivity(in);
                }
               */
                Intent in = new Intent(programSelectionEligibility.this, uniList.class);
                startActivity(in);

            }
        });

        acc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                item = parent.getItemAtPosition(i).toString();
                //  Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}