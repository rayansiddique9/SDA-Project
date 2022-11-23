package com.example.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


public class MainActivity extends AppCompatActivity {

    private Button b;
    private Button b2;

    private TextView textView;
    private static String ip = "10.0.2.2";       //ALWAYS CHANGE IP TO CURRENT WIFI
    private static String port = "1433";
    public static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "UniGrab";
    private static String username = "sa";
    private static String password = "12345678";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        b = findViewById(R.id.button4);

        b2 = findViewById(R.id.button5);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            textView.setText("Success");

        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
            textView.setText("Error");
        } catch (SQLException e) {
            e.printStackTrace();
            textView.setText("Failure");
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, selectAccount.class);
                startActivity(in);

            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            Intent in = new Intent(MainActivity.this, selectAccount_signup.class);
            startActivity(in);

            }
        });


    }

    public void sqlButton(View view)
    {
        if(connection != null)
        {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("insert into person(name) values ('zzzebra')");
           /*     Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from person");*/
                while(resultSet.next())
                {
                    textView.setText(resultSet.getString(1));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            textView.setText("Connection is null");
        }
    }

    public void sqlButton2(View view)
    {
        if(connection != null)
        {
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select userName from [User]");
                while(resultSet.next())
                {
                    textView.setText(resultSet.getString(1));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            textView.setText("Connection is null");
        }

    }



}

