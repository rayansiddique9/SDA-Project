package com.example.myapplication.Classes;

import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Evaluator{
    /*
    private static String ip = "10.0.2.2";       //ALWAYS CHANGE IP TO CURRENT WIFI
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "UniGrab";

    private Connection connection = null;*/

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


 public ArrayList<String> getFilteredUnisUG( String degree, Context ptr, String subjectCombo, int marks)
 {
     ArrayList<String> arr = new ArrayList<String>();
     if(connection != null)
     {
         Statement statement = null;

         try {
             statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select us.userName from [User] us join University u on u.idUniversity = us.idUser join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment join UndergraduateProgram up on p.idProgram = up.idUGProgram join ugReqBG b on up.idUGProgram = b.bgid where b.name = '"+subjectCombo+"' and p.name = '"+degree+"' and "+marks+" >= up.minMarks");
             while(resultSet.next())
             {
                 arr.add(resultSet.getString(1));
              //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
             }
             Toast.makeText(ptr, String.valueOf(arr.size()), Toast.LENGTH_SHORT).show();
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


    return arr;
 }


    public ArrayList<String> getFilteredUnisG( String degree, Context ptr, String subjectCombo, Float marks)
    {
        ArrayList<String> arr = new ArrayList<String>();
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select us.userName from [User] us join University u on u.idUniversity = us.idUser join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment join GraduateProgram gp on p.idProgram = gp.idUGProgram join gReqBG b on gp.idGProgram = b.bgid where b.name = '"+subjectCombo+"' and p.name = '"+degree+"' and "+marks+" >= gp.minCGPA");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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


        return arr;
    }


    public void fillBSlist(List<String> arr, Context ptr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select distinct p.name from Program p join UndergraduateProgram up on p.idProgram = up.idUGProgram");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
    }

    public void fillMSlist(List<String> arr, Context ptr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select distinct p.name from Program p join GraduateProgram gp on p.idProgram = gp.idGProgram");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
    }

 //for student applying for undergrad / for undergraduate student obj

    public void getEducationalBackground()
    {
        //when student obj is created -> pass undergraduateStudent obj to this function bu using put extra from login to this page serially
        //then this.subjectCombo = UndergradStudentobj.subjectCombo
    }

    public void getIntermediateMarks()
    {
        //when student obj is created -> pass undergraduateStudent obj to this function bu using put extra from login to this page serially
        //then this.marks = UndergradStudentobj.marks
    }

 //for student applying for masters / for graduateStudent

    public void getBSProgram()
    {
        //when student obj is created -> pass GraduateStudent obj to this function bu using put extra from login to this page serially
        //then this.subjectCombo = GraduateStudentobj.subjectCombo
    }

    public void getCGPA()
    {
        //when student obj is created -> pass GraduateStudent obj to this function bu using put extra from login to this page serially
        //then this.marks = GraduateStudent.marks
    }

}
