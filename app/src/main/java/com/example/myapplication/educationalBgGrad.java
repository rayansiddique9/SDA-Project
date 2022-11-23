package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.Classes.GraduateStudent;
import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.accountCreator;

import java.io.Serializable;

public class educationalBgGrad extends AppCompatActivity {
    Button b;
    ArrayAdapter<String> adapter;
    String []arr = {"BSCS", "BSSE", "BSDS","BBA","BSCV", "BSEE", "BSME"};
    Spinner acc;
    String item;
    EditText cgpa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_bg_grad);

        accountCreator obj = new accountCreator();
        obj.connectToDb(educationalBgGrad.this);


        String username = getIntent().getExtras().getString("uname");
        String email = getIntent().getExtras().getString("email");
        String pass = getIntent().getExtras().getString("pass");
        String edutype = getIntent().getExtras().getString("edutype");
        String fname = getIntent().getExtras().getString("fname");
        String lname = getIntent().getExtras().getString("lname");
        String date = getIntent().getExtras().getString("date");


        cgpa = findViewById(R.id.editTextTextcgpa);
        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                obj.createGradAcc(educationalBgGrad.this, username, email, pass, edutype, date, fname, lname, Float.valueOf(cgpa.getText().toString()), item, 0, 0);
                //GraduateStudent obj1 = new GraduateStudent(username, email, pass, edutype, date, fname, lname, Float.valueOf(cgpa.getText().toString()), item);
                Intent in = new Intent(educationalBgGrad.this, loginCredentialsStudent.class);
              /*  in.putExtra("activityname", "educationalBgGrad");
                in.putExtra("edutype",edutype);
                in.putExtra("grad", (Serializable) obj1);*/
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