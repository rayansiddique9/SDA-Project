package com.example.myapplication;

import static com.example.myapplication.MainActivity.Classes;

import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.firestore.auth.User;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class viewStudentProfile extends AppCompatActivity {
    private static String url = "jdbc:jtds:sqlserver://192.168.0.103:1433/UniGrab";
    private static String username = "sa";
    private static String password = "123456";
    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_profile);

        TextView _fName = findViewById(R.id.firstname);
        TextView _lName = findViewById(R.id.lastname);
        TextView _cgpa = findViewById(R.id.CGPA);
        TextView _loc = findViewById(R.id.location);
        TextView _longi = findViewById(R.id.longitude);
        TextView _lati = findViewById(R.id.latitude);
        TextView _aemoon = findViewById(R.id.emmail);
        TextView _dob = findViewById(R.id.DOB);
        TextView _subj=findViewById(R.id.subjectcomb);

        Intent intent = getIntent();
        String temp  = intent.getStringExtra("uid");
        int uid = Integer.parseInt(temp);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            //    Toast.makeText(ptr,"Success", Toast.LENGTH_SHORT).show();
            //_fName.setText("Success");

        } catch (Exception e) {
            e.printStackTrace();
            //_fName.setText("Fail");
            // Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        /*
         * This code is only executed when we have successfully connected to DB
         */
        if (connection != null) {
            Statement statement = null;

            try {
                // For Procedure with input parameters
                CallableStatement callableStatement = connection.prepareCall("{call viewStudentProfile(?)}");
                //passing parameters
                callableStatement.setInt("uid",uid);
                ResultSet result = callableStatement.executeQuery();

                while(result.next()){

                    _aemoon.setText(result.getString("email"));
                    _fName.setText(result.getString("firstName"));
                    _lName.setText(result.getString("lastName"));
                   // _lati.setText(result.getString("latitude"));
                   // _longi.setText(result.getString("longitude"));
                   // _cgpa.setText(result.getString("CGPA"));
                   // _subj.setText(result.getString("subjectCombo"));
                   // _loc.setText(result.getString("location"));
                    _dob.setText(result.getString("DOB"));

                    Toast.makeText(this,result.getString("firstName"), Toast.LENGTH_SHORT).show();

                }
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }

    }
}