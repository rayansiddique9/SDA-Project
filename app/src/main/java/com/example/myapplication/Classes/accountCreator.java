package com.example.myapplication.Classes;

import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class accountCreator {
    private static String url = "jdbc:jtds:sqlserver://10.0.2.2:1433/UniGrab";
    private static String username = "sa";
    private static String password = "258369";
    private Connection connection = null;


    public void  connectToDb(Context ptr)
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            Toast.makeText(ptr,"Success", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void closeConnection() throws SQLException
    {
        connection.close();
        connection = null;
    }

    public boolean isConnectionOpen()
    {
        if (connection == null)
            return false;
        return true;
    }


    public boolean checkUsername(Context ptr, String name)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] u where u.userName = '"+name+"'");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void createUndergradAcc(String name, String email, String pass, String eduType, String d, String fname, String lname, int m, String subjects, int isadmin, int isdisabled)
    {
        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;
            int stuId = 0;
            try {
                statement = connection.createStatement();
                String query = "insert into [User] values ('"+name+"', '"+email+"', '"+pass+"',"+isadmin+","+isdisabled+")";
                statement.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
              //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement1 = connection.createStatement();
                String query1 = "select u.idUser from [User] u where u.userName = '"+name+"'";
                ResultSet resultSet1 = statement1.executeQuery(query1);

                while(resultSet1.next())
                {
                    stuId = resultSet1.getInt(1);
                  //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
              //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement2 = connection.createStatement();
                String query2 = "insert into Student values("+stuId+", '"+eduType+"', '"+d+"', '"+fname+"', '"+lname+"'"+isadmin+","+isdisabled+")";
                statement2.executeQuery(query2);

            }
            catch(SQLException e)
            {
                e.printStackTrace();
              //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement3 = connection.createStatement();
                String query3 = "insert into Undergraduate values("+stuId+", "+m+", '"+subjects+"')";
                statement3.executeQuery(query3);

            }
            catch(SQLException e)
            {
                e.printStackTrace();
              //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
          //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void createGradAcc(String name, String email, String pass, String eduType, String d, String fname, String lname, Float m, String subjects, int isadmin, int isdisabled)
    {
        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;
            int stuId = 0;
            try {
                statement = connection.createStatement();
                String query = "insert into [User] values ('"+name+"', '"+email+"', '"+pass+"',0,0)";
                statement.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement1 = connection.createStatement();
                String query1 = "select u.idUser from [User] u where u.userName = '"+name+"'";
                ResultSet resultSet1 = statement1.executeQuery(query1);

                while(resultSet1.next())
                {
                    stuId = resultSet1.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement2 = connection.createStatement();
                String query2 = "insert into Student values("+stuId+", '"+eduType+"', '"+d+"', '"+fname+"', '"+lname+"')";
                statement2.executeQuery(query2);

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            try
            {
                statement3 = connection.createStatement();
                String query3 = "insert into Graduate values("+stuId+", "+m+", '"+subjects+"')";
                statement3.executeQuery(query3);

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
          //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


}
