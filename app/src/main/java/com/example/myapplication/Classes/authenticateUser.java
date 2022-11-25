package com.example.myapplication.Classes;

import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class authenticateUser {
    private static String url = "jdbc:jtds:sqlserver://192.168.100.151:1433/UniGrab";
    private static String username = "sa";
    private static String password = "123456";
    private Connection connection = null;


    public void  connectToDb(Context ptr)
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
        //    Toast.makeText(ptr,"Success", Toast.LENGTH_SHORT).show();

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

    public boolean exeQuery(/*Context ptr,*/ArrayList list,String query){
        if(connection != null)
        {
            PreparedStatement statement = null;

            try {
                statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(resultSet.getString("email"));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            //Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public boolean verifyInfoStu(Context ptr, String name, String pass) {

        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] us join Student s on us.idUser = s.idStudent where us.userName = '"+name+"' and us.password = '"+pass+"' and us.isDisabled = 0");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return true;
                    } else {
                        return false;
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

    public String getEduType(Context ptr, String name, String pass)
    {
        String rslt = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select s.educationType from [User] u join Student s on u.idUser = s.idStudent where u.userName = '"+name+"' and u.password = '"+pass+"'");

                while(resultSet.next())
                {
                    rslt = resultSet.getString(1);
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

        return rslt;
    }

    public UndergradStudent getUGStu(Context ptr, String name, String pass)
    {
        UndergradStudent obj = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.email, s.educationType, s.DOB, s.firstName, s.lastName, ug.marks, ug.subjectCombo from [User] u join Student s on u.idUser = s.idStudent join Undergraduate ug on s.idStudent = ug.idStudent where u.userName = '"+name+"' and u.password = '"+pass+"'");

                while(resultSet.next())
                {
                    obj = new UndergradStudent(123,name, resultSet.getString(1), pass, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7));
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

        return obj;
    }

    public GraduateStudent getGStu(Context ptr, String name, String pass)
    {
        GraduateStudent obj = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.email, s.educationType, s.DOB, s.firstName, s.lastName, ug.CGPA, ug.BSDegree from [User] u join Student s on u.idUser = s.idStudent join Graduate ug on s.idStudent = ug.idStudent where u.userName = '"+name+"' and u.password = '"+pass+"'");

                while(resultSet.next())
                {
                    obj = new GraduateStudent(123,name, resultSet.getString(1), pass, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),  resultSet.getFloat(6), resultSet.getString(7));
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

        return obj;
    }

}
