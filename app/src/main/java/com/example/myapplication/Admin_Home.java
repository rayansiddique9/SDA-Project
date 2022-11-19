package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Classes.*;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Admin_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Admin user;
        EditText _email = findViewById(R.id.admin_email);
        EditText _pass = findViewById(R.id.admin_pass);
        Button login = findViewById(R.id.admin_login);
        TextView conStatus = findViewById(R.id.con_status);

        String email = _email.getText().toString();
        String pass = _pass.getText().toString();
        String email1 = "faraz@gmail.com";

        DBConnector conn = new DBConnector();
        conStatus.setText(conn.ConUniGrab("SELECT * FROM [User]"));

    }
}