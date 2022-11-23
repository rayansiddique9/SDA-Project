package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageUniDepAdd extends AppCompatActivity {

    private Button bt;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_uni_department_info);

        bt = findViewById(R.id.button_next_to_program);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in = new Intent(ManageUniDepAdd.this, ManageUniProg.class);
                startActivity(in);
            }
        });


    }

}
