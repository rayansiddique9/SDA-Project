package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Classes.Department;
import com.example.myapplication.Classes.Student;
import com.example.myapplication.Classes.University;
import com.example.myapplication.Classes.Visitor;
import com.example.myapplication.Classes.currentUser;

import java.util.ArrayList;

public class loginUni extends AppCompatActivity {

    Button l;
    EditText username;
    EditText password;
    Visitor obj;
    String edu;
    University obj1;
    currentUser cu;
    TextView t;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_uni);


        username = findViewById(R.id.usernamee);
        password = findViewById(R.id.unipassword);
        obj = new Visitor();
        l = findViewById(R.id.unilogin);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {

                    if (obj.signInUni(username, password, loginUni.this) == true)
                    {
                        obj1 = obj.makeUni(username, password, loginUni.this);
                        Intent in = new Intent(loginUni.this, homePageUni.class);
                     //   cu = currentUser.getInstance(null, obj1, null);

                        /*ArrayList<String> swad = new ArrayList<>();
                        obj1.getDepartments(swad);
                        for(int x = 0; x < swad.size(); x++)
                        {
                            Toast.makeText(loginUni.this, swad.get(x), Toast.LENGTH_SHORT).show();
                        }*/

                    /*    String un=obj.getUsername();
                        Toast.makeText(loginUni.this, un, Toast.LENGTH_SHORT).show();
*/
                        //Toast.makeText(loginUni.this, obj1.getUsername(), Toast.LENGTH_SHORT).show();

                    //    t.setText(obj1.getUsername());

                        /*ArrayList<Department> dept = null;
                        dept = obj1.getDepartments();

                        for(int x = 0; x < dept.size(); x++)
                        {
                            Toast.makeText(loginUni.this, dept.get(x).getName(), Toast.LENGTH_SHORT).show();
                        }*/
                        in.putExtra("name", username.getText().toString());
                        startActivity(in);
                    }
                    else
                    {
                        Toast.makeText(loginUni.this, "Wrong credentials or Account Disabled", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e)
                {

                    Toast.makeText(loginUni.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}