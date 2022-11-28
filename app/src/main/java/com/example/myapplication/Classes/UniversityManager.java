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

public class UniversityManager {

    private static String url = "jdbc:jtds:sqlserver://10.0.2.2:1433/UniGrab";
    private static String username = "sa";
    private static String password = "12345678";
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

    public void createProgramUnderGraduate(Context ptr, String name, int credithr, int feepercredithr, int min_marks, String preReq )
    {

        if(connection != null)
        {
            Statement statement = null;
            Statement statement1 = null;
            Statement statement2 = null;
            Statement statement3 = null;
            Statement statement4 = null;
            Statement statement5 = null;
            int UserId = 3;
            int Departmentid = 0;
            int Programid = 0;
            int UnderGradId = 0;

            try
            {
                statement1 = connection.createStatement();
                String query1 = "select u.idDepartment from [Department] u where u.idUniversity = '"+UserId+"'";
                ResultSet resultSet1 = statement1.executeQuery(query1);

                while(resultSet1.next())
                {
                    Departmentid = resultSet1.getInt(1);
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
                String query2 = "insert into Program values("+Departmentid+", '"+name+"', '"+credithr+"', '"+feepercredithr+"')";
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
                String query3 = "select u.idProgram from [Program] u where u.idDepartment = '"+Departmentid+"'  AND u.name = '"+name+"'";
                ResultSet resultSet2 = statement3.executeQuery(query3);

                while(resultSet2.next())
                {
                    Programid = resultSet2.getInt(1);
                    //  Toast.makeText(ptr, String.valueOf(stuId), Toast.LENGTH_SHORT).show();
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            try {
                statement = connection.createStatement();
                String query = "insert into [UndergraduateProgram] values ('"+Programid+"', '"+min_marks+"')";
                statement.executeQuery(query);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            try {
                statement5 = connection.createStatement();
                String query5 = "insert into [ugReqBG] values ('"+preReq+"', '"+Programid+"')";
                statement5.executeQuery(query5);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

    }
}
