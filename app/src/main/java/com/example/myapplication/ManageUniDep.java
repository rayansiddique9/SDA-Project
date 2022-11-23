package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageUniDep extends AppCompatActivity {

    private Button bt_Add;
    private Button bt_Del;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_uni_department);

        bt_Add = findViewById(R.id.button_AddUni);

        bt_Del = findViewById(R.id.button_DeleteUni);

        bt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in = new Intent(ManageUniDep.this, ManageUniDepAdd.class);
                startActivity(in);
            }
        });

//        bt_Del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                Intent in = new Intent(ManageUniDep.this, ManageUniProg.class);
//                startActivity(in);
//            }
//        });
    }




}
