package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class signup extends AppCompatActivity
{
    ArrayAdapter<String> adapter;
    String []arr = {"Student", "University"};
    Spinner acc;
    Button b;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentsignup);

        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.button);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);
    }
}
