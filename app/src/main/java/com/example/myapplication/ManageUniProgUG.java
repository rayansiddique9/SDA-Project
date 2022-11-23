package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageUniProgUG extends AppCompatActivity {

    private Button bt;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_uni_program_input);

        bt = findViewById(R.id.button_next_to_manage_uni);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in = new Intent(ManageUniProgUG.this, ManageUniMain.class);
                startActivity(in);
            }
        });
    }
}
