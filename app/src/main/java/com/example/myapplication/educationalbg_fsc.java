package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Classes.UndergradStudent;
import com.example.myapplication.Classes.accountCreator;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class educationalbg_fsc extends AppCompatActivity {


    Button b;
    ArrayAdapter<String> adapter;
    String []arr = {"Pre Medical", "Pre Engineering", "ICS","FA","Business"};
    Spinner acc;
    String item;
    EditText marks;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educationalbg_fsc);


        accountCreator obj = new accountCreator();
        obj.connectToDb(educationalbg_fsc.this);


        String username = getIntent().getExtras().getString("uname");
        String email = getIntent().getExtras().getString("email");
        String pass = getIntent().getExtras().getString("pass");
        String edutype = getIntent().getExtras().getString("edutype");
        String fname = getIntent().getExtras().getString("fname");
        String lname = getIntent().getExtras().getString("lname");
        String date = getIntent().getExtras().getString("date");

/*        Date date1 = null;
        try {
            date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

       /* Date date1 = null;
        try {
            date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

 /*       Toast.makeText(educationalbg_fsc.this, "USername:"+username, Toast.LENGTH_SHORT).show();
        Toast.makeText(educationalbg_fsc.this, "Email:"+email, Toast.LENGTH_SHORT).show();
        Toast.makeText(educationalbg_fsc.this, "Password:"+pass, Toast.LENGTH_SHORT).show();
        Toast.makeText(educationalbg_fsc.this, "EduType:"+edutype, Toast.LENGTH_SHORT).show();
        Toast.makeText(educationalbg_fsc.this, "FirstName:"+fname, Toast.LENGTH_SHORT).show();
        Toast.makeText(educationalbg_fsc.this, "LastName:"+lname, Toast.LENGTH_SHORT).show();*/
      //  Toast.makeText(this, date.toString(), Toast.LENGTH_SHORT).show();



        marks = findViewById(R.id.marks);
        acc = findViewById(R.id.spinner);
        b = findViewById(R.id.nextbtn);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                obj.createUndergradAcc(educationalbg_fsc.this, username, email, pass, edutype, date, fname, lname, Integer.valueOf(marks.getText().toString()), item, 0, 0);
             //   UndergradStudent obj1 = new UndergradStudent(username, email, pass, edutype, date, fname, lname, Integer.valueOf(marks.getText().toString()), item);
                Intent in = new Intent(educationalbg_fsc.this, loginCredentialsStudent.class);
                /*in.putExtra("edutype",edutype);
                in.putExtra("activityname", "educationalbg_fsc");
                in.putExtra("undergrad", (Serializable) obj1);*/
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