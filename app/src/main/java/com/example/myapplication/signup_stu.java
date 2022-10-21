package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class signup_stu extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String []arr = {"Student", "University"};
    Spinner acc;
    Button b;
    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_stu);

        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item == "Student")
                {
                    Intent in = new Intent(signup_stu.this, signupdetail_stu.class);
                    startActivity(in);
                }
                else if(item == "University")
                {
                    Intent in = new Intent(signup_stu.this, loginUni.class);
                    startActivity(in);
                }
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