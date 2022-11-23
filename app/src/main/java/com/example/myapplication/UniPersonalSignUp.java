package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UniPersonalSignUp extends AppCompatActivity {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_personal_sign_up);


        bt = findViewById((R.id.SignUpButton));

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in = new Intent(UniPersonalSignUp.this, ManageUniMain.class);
                startActivity(in);
            }
        });

    }
}