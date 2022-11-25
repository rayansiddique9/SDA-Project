package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class activity_student_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);


        EditText _firstname = findViewById(R.id.firstname);
        EditText _lastname = findViewById(R.id.lastname);
        EditText _DOB = findViewById(R.id.DOB);
        EditText _subjectCombo = findViewById(R.id.subjectcombo);
        EditText _location = findViewById(R.id.location);
        EditText _longitude = findViewById(R.id.longitude);
        EditText _latitude = findViewById(R.id.latitude);
        EditText _equivalence = findViewById(R.id.equivalence);
        EditText _cgpa = findViewById(R.id.CGPA);

    }
}