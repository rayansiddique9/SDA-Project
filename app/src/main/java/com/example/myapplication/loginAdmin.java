package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.Visitor;

public class loginAdmin extends AppCompatActivity {

    Button l;
    EditText username;
    EditText password;
    Visitor obj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        username = findViewById(R.id.usernameadmin);
        password = findViewById(R.id.stuPassword);
        obj = new Visitor();
        l = findViewById(R.id.loginadminn);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (obj.signInAdmin(username, password, loginAdmin.this) == true) {

                        Intent in = new Intent(loginAdmin.this, homePageAdmin.class);
                        startActivity(in);

                    } else {
                        Toast.makeText(loginAdmin.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(loginAdmin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}