package com.example.myapplication.Classes;

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
import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountManager {

    private final String url;
    private final String username;
    private final String password;
    private final String Driver;
    private Connection connection;

    public AccountManager() {
        this.url ="jdbc:jtds:sqlserver://192.168.0.101:1433/UniGrab";
        this.username = "sa";
        this.password = "123456";
        this.Driver = "net.sourceforge.jtds.jdbc.Driver";
        this.connection = null;
    }

    public boolean viewAccountList(Context ptr, ArrayList<User> uList){
        try {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
            Class.forName(Driver);
            connection = DriverManager.getConnection(url, username, password);
            //Toast.makeText(ptr,"Successful Connection", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
           //Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (connection != null) {
            Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call getALLUsers()}");
                ResultSet result = callableStatement.executeQuery();

                    while (result.next()) {

                       if (result.getInt("isStudent") == 1) {
                            Student st = new Student(
                                    result.getInt("idUser"),// uid
                                    result.getString("userName"), // name
                                    result.getString("email"), // email
                                    result.getString("password"), // pass
                                    "PlaceHolder", // eduType
                                    "17/02/2001", //DOB
                                    result.getString("firstName"),// fname
                                    result.getString("latName") // lname
                            );
                            uList.add(st);
                       }
//                    else if(result.getInt("isUniversity") == 1){
//                        University un = new University(
//                        )
//                    }
//
                   }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
               //Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    public void disabledAccount(Context ptr, int uid){
        try {
            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
            Class.forName(Driver);
            connection = DriverManager.getConnection(url, username, password);
            Toast.makeText(ptr,"Successful Connection", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (connection != null) {
            Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call disable_Account(?)}"); //para check
                callableStatement.setInt("uid",uid);
                ResultSet result = callableStatement.executeQuery();
                Toast.makeText(ptr,"Query Successful", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void enableAccount(Context ptr,int uid) {
        try {
            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
            Class.forName(Driver);
            connection = DriverManager.getConnection(url, username, password);
            Toast.makeText(ptr,"Successful Connection", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ptr,"Query Successful", Toast.LENGTH_SHORT).show();
        }

        if (connection != null) {
            Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call enable_Account(?)}"); //para check
                callableStatement.setInt("uid",uid);
                ResultSet result = callableStatement.executeQuery();
                Toast.makeText(ptr,"Query Successful", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void deleteAccount(Context ptr,int uid) {
        try {
            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
            Class.forName(Driver);
            connection = DriverManager.getConnection(url, username, password);
            Toast.makeText(ptr,"Successful Connection", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (connection != null) {
            Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call delete_Account(?)}"); //para check
                callableStatement.setInt("uid",uid);
                ResultSet result = callableStatement.executeQuery();
                Toast.makeText(ptr,"Query Successful", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
