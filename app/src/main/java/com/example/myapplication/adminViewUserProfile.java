package com.example.myapplication;

import static com.example.myapplication.MainActivity.Classes;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.firestore.auth.User;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.accountManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class adminViewUserProfile extends AppCompatActivity {
    accountManager AM = new accountManager();

    private static String url = "jdbc:jtds:sqlserver://192.168.137.87:1433/UniGrab";
    private static String username = "sa";
    private static String password = "123456";
    private Connection connection = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user_profile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

            TextView _fname = findViewById(R.id.fname);
            TextView _lname = findViewById(R.id.lname);
            TextView _email = findViewById(R.id._email);
            TextView _uid = findViewById(R.id.uid);
            TextView _uname = findViewById(R.id.uname);
            TextView _atype = findViewById(R.id.atype);
            TextView _disabled = findViewById(R.id.disabled);

            Button _enable = findViewById(R.id._enable);
            Button _disable = findViewById(R.id._disable);
            Button _delete = findViewById(R.id._delete);



            Intent intent = getIntent();
            String temp  = intent.getStringExtra("uid");
            int uid = Integer.parseInt(temp);
            String s = intent.getStringExtra("type");
            _atype.setText(s);

            _enable.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(AM.enableAccount(adminViewUserProfile.this,uid)){
                        _disabled.setText("ENABLED");
                        Intent back = new Intent(adminViewUserProfile.this, Admin_Home.class);
                        back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //Toast.makeText(adminViewUserProfile.this,"Succesfuly Enabled", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            _disable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(AM.disableAccount(adminViewUserProfile.this,uid)){
                        _disabled.setText("DISABLED");
                        Intent back = new Intent(adminViewUserProfile.this, Admin_Home.class);
                        back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }
            });

            _delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (AM.deleteAccount(adminViewUserProfile.this,uid)) {
                        Intent back = new Intent(adminViewUserProfile.this, Admin_Home.class);
                        back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(back);
                    }
                }
            });

            if(!AM.viewAccount(this,uid,_uid,_uname,_fname,_lname,_email,_disabled,s)){
                Toast.makeText(this,"Some Kind Of Error. I crash gracefully", Toast.LENGTH_SHORT).show();
            }




        }

}