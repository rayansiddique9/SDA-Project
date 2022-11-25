package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Classes.managePost;

public class addVideoUni extends AppCompatActivity {

    private EditText ev;
    private Button ok;
    private managePost m;
    private String uname;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video_uni);

        ok = findViewById(R.id.post);
        ev = findViewById(R.id.imagedesc);

        uname = getIntent().getExtras().getString("name");
        m = new managePost();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ev.getText().length() != 0)
                {
                    m.connectToDb(addVideoUni.this);
                    m.insertVideo(addVideoUni.this, uname, ev.getText().toString());
                 //   Toast.makeText(addVideoUni.this, "Video posted", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(addVideoUni.this, homePageUni.class);
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(addVideoUni.this, "Enter url first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}