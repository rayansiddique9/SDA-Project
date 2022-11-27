package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.currentUserUni;
import com.example.myapplication.Classes.managePost;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class homePageUni extends AppCompatActivity {
    private Button postBtn, profileBtn;
    private String uname;
    private University obj;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_uni);

        currentUserUni cu = currentUserUni.getInstance(obj);
        obj = (University) cu.getU();

        Toast.makeText(this, String.valueOf(obj.getName()), Toast.LENGTH_SHORT).show();



        uname = getIntent().getExtras().getString("name");

        postBtn = findViewById(R.id.post);
        profileBtn = findViewById(R.id.profile);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(homePageUni.this, addPostUni.class);
                in.putExtra("name", uname);
                startActivity(in);
            }

        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(homePageUni.this, uniPageUni.class);
                in.putExtra("name", uname);
                startActivity(in);
            }

        });
    }

}