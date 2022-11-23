package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ManageUniMain extends AppCompatActivity {

    private Button bt_Dep;
    private Button bt_Prog;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_uni_main);

        bt_Dep = findViewById(R.id.button_department);

        bt_Prog = findViewById(R.id.button_Program);

        bt_Dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in = new Intent(ManageUniMain.this, ManageUniDep.class);
                startActivity(in);
            }
        });

        bt_Prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in = new Intent(ManageUniMain.this, ManageUniProg.class);
                startActivity(in);
            }
        });
    }



}
