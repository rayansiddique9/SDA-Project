package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class adduniversityinformation extends AppCompatActivity {

    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_university_info);

        bt = findViewById(R.id.AddUniInfo);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText t = findViewById(R.id.UsernameAddUni);
                String input = t.getText().toString();

                Log.d("info", input);

                Intent in = new Intent(adduniversityinformation.this, displayUnivinfo.class);
                startActivity(in);
            }
        });
    }
}