package com.example.myapplication;

import static com.example.myapplication.MainActivity.Classes;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Classes.*;
//import com.google.firebase.firestore.auth.User;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class Admin_Home extends AppCompatActivity {
    AccountManager AM = new AccountManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

//        EditText _email = findViewById(R.id.admin_email);
//        EditText _pass = findViewById(R.id.admin_pass);
//        Button login = findViewById(R.id.admin_login);
//        TextView conStatus = findViewById(R.id.con_status);
//        TextView _welcome = findViewById(R.id.welcome);
//        String email = _email.getText().toString();
//        String pass = _pass.getText().toString();
        
        ArrayList<User> list = new ArrayList<User>();
        if(AM.viewAccountList(this,list)){
            Toast.makeText(this,"Successful Operation", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Some Error", Toast.LENGTH_SHORT).show();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
}

//public class Admin_Home extends AppCompatActivity {
//    private static String url = "jdbc:jtds:sqlserver://192.168.0.104:1433/UniGrab";
//    private static String username = "sa";
//    private static String password = "123456";
//    private Connection connection = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin_home);
//
//        EditText _email = findViewById(R.id.admin_email);
//        EditText _pass = findViewById(R.id.admin_pass);
//        Button login = findViewById(R.id.admin_login);
//        TextView conStatus = findViewById(R.id.con_status);
//        TextView _welcome = findViewById(R.id.welcome);
//        String email = _email.getText().toString();
//        String pass = _pass.getText().toString();
//
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        // This is usd for output display
//        String strlog = "";
//
//        /*
//         * This is where we connect to DB server,
//         * we always connect in a try catch blocks
//         */
//        try {
//            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
//            Class.forName(Classes);
//            connection = DriverManager.getConnection(url, username, password);
//            //    Toast.makeText(ptr,"Success", Toast.LENGTH_SHORT).show();
//            conStatus.setText("Success");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            conStatus.setText("Fail");
//            //  Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//        /*
//         * This code is only executed when we have successfully connected to DB
//         */
//        if (connection != null) {
//            Statement statement = null;
//
//            try {
//
//                // For Query
//                //statement = connection.createStatement();
//                //ResultSet result = statement.executeQuery("SELECT * FROM [User]");
//
//                // For Procedure with no parameters
//                // CallableStatement callableStatement = connection.prepareCall("{call getAllUsers()}");
//                // ResultSet result = callableStatement.executeQuery();
//
//                // For Procedure with input parameters
//                CallableStatement callableStatement = connection.prepareCall("{call getUser(?)}");
//                //passing parameters
//                callableStatement.setInt("uid",1);
//                ResultSet result = callableStatement.executeQuery();
//
//                // // For Procedure with output parameters
//                //     CallableStatement callableStatement = connection.prepareCall("{call enable_Account(?,?)}");
//                //     //passing input parameters
//                //     callableStatement.setInt("uid",1);
//                //     //passing output parameter (java.sql.Types.INTEGER)
//                //     callableStatement.registerOutParameter("isSuccess", Types.INTEGER);
//                //     ResultSet result = callableStatement.executeQuery();
//
//
//                // next() Controls Row Number
//                // result.get(i) Controls Column Number
//                // 'i' will always start from 1
//
//                while(result.next()){
//
//                    /*
//                     * This is Sample code to get data by Column Number
//                     * where 'i' co-responds to column number.
//                     */
//                    // for(int i = 1; i <= 11; i++){
//                    //     strlog += result.getString(i) + " :";
//                    // }
//                    // strlog += "\n";
//
//                    /*
//                     * This is Sample code to get data by Column Name
//                     * where input parameter is column name.
//                     */
//                    strlog += "Uid: ";
//                    strlog += result.getString("idUser");
//                    strlog += "\nUName: ";
//                    strlog += result.getString("userName");
//                    strlog += "\nEmail: ";
//                    strlog += result.getString("email");
//                    strlog += "\n";
//                    strlog += result.getString("password");
//                    strlog += "\n";
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                //Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            conStatus.setText(strlog);
//        }
//
//    }
//}