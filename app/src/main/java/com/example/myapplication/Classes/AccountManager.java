package com.example.myapplication.Classes;

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

    private static final String url = "jdbc:jtds:sqlserver://192.168.0.104:1433/UniGrab";
    private static final String username = "sa";
    private static final String password = "123456";
    private static final String Driver = "net.sourceforge.jtds.jdbc.Driver";
    private Connection connection = null;

    public boolean viewAccountList(Context ptr, ArrayList<User> uList){
        try {
            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
            Class.forName(Driver);
            connection = DriverManager.getConnection(url, username, password);
            //Toast.makeText(ptr,"Successful Connection", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
           // Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (connection != null) {
            Statement statement = null;
            try {
                CallableStatement callableStatement = connection.prepareCall("{call getALLUsers()}");
                ResultSet result = callableStatement.executeQuery();

                // next() Controls Row Number
                // result.get(i) Controls Column Number
                // 'i' will always start from 1
              //  Toast.makeText(ptr,"Query Successful", Toast.LENGTH_SHORT).show();
                while(result.next()){
                    // Check if Student or University
                    Student temp = new Student(
                            result.getInt("idUser"),// uid
                            result.getString("userName"), // name
                            result.getString("email"), // email
                            result.getString("password"), // pass
                            "PlaceHolder", // eduType
                            "17/02/2001", //DOB
                            result.getString("firstName"),// fname
                            result.getString("lastName") // lname
                    );
                   // Toast.makeText(ptr,"Result Array Success", Toast.LENGTH_SHORT).show();
                    uList.add(temp);
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
               // Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

//    public void selectAccount(int uid) {
//
//    }

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
