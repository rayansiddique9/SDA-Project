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
    private TextView textView;
    private static String ip = "192.168.18.46";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "mydb";
    private static String username = "sa";
    private static String password = "258369";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        b = findViewById(R.id.button4);

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
        }


     /*   b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, selectAccount.class);
                startActivity(in);

            }
        });*/
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
                ResultSet resultSet = statement.executeQuery("select * from person");
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



/*    public void GetTextFromSQL(View v)
    {
        TextView tx;
        tx = findViewById(R.id.textView2);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect != null)
            {
                String query = "select * from Person";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);
                while(rs.next())
                {
                    tx.setText(rs.getString(1));
                }
            }
            else
            {
                connectionResult = "check connection";
            }
        }
        catch(Exception ex)
        {
            Log.e("error",ex.getMessage());
        }
    }*/

}

