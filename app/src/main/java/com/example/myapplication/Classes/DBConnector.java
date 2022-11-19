package com.example.myapplication.Classes;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnector {
    private static String ip = "192.168.0.104";       //ALWAYS CHANGE IP TO CURRENT WIFI
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "UniGrab";
    private static String username = "sa";
    private static String password = "123456";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
    private Connection connection = null;

    public String ConUniGrab(String query) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String out = "";
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            //textView.setText("Success");
            Statement stat = connection.createStatement();
            ResultSet rs = stat.executeQuery(query);

            while (rs.next()){
              out = rs.getString(0);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out = "Error";
            //textView.setText("Error");
        } catch (SQLException e) {
            e.printStackTrace();
            out = "Failure";
            //textView.setText("Failure");
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return out;
    }
//    public void ExeQ(String query){
//        Statement stat = connection.createStatement();
//        ResultSet rs = stat.executeQuery(query);
//    }
}
