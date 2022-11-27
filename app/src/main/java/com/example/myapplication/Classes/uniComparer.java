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
import java.util.ArrayList;
import java.util.List;

public class uniComparer {
    private static String url = "jdbc:jtds:sqlserver://10.0.2.2:1433/UniGrab";
    private static String username = "sa";
    private static String password = "258369";
    private Connection connection = null;
    private String uniname;

    public void  connectToDb(Context ptr)
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            //  Toast.makeText(ptr,"Success", Toast.LENGTH_SHORT).show();

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

    public void fillUniList(List<String> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select a.userName from [User] a join University u on u.idUniversity = a.idUser\n");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
             //   Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
          //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }


    public void getUniveristy(String universityname, ArrayList<String> depts, List<alumniInfo> arr, List<feeinfo> f, List<aidInfo> a, List<reviewInfo> r)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select d.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet.next())
                {
                    depts.add(resultSet.getString(1));
                }

                ResultSet resultSet1 = statement.executeQuery("select al.name, al.placementCompany, al.batch from [User] a join University u on a.idUser = u.idUniversity join Alumni al on u.idUniversity = al.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet1.next())
                {
                    arr.add(new alumniInfo(resultSet1.getString(1), resultSet1.getString(2), Integer.toString(resultSet1.getInt(3))));
                }

                ResultSet resultSet2 = statement.executeQuery("select p.name, p.feePerCreditHour, p.creditHours from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment where a.userName = '"+universityname+"'");
                while(resultSet2.next())
                {
                    f.add(new feeinfo(resultSet2.getString(1), resultSet2.getInt(2), resultSet2.getInt(3)));
                }

                ResultSet resultSet3 = statement.executeQuery("select fa.name, fa.detail from [User] a join University u on a.idUser = u.idUniversity join FinancialAid fa on u.idUniversity = fa.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet3.next())
                {
                    a.add(new aidInfo(resultSet3.getString(1), resultSet3.getString(2)));
                }

                ResultSet resultSet4 = statement.executeQuery("select r.review, r.stars from [User] a join University u on a.idUser = u.idUniversity join Review r on u.idUniversity = r.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet4.next())
                {
                    r.add(new reviewInfo(resultSet4.getString(1), resultSet4.getInt(2)));
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //   Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void getDepts(String universityname, List<String> depts)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select d.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet.next())
                {
                    depts.add(resultSet.getString(1));
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                //   Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public List<String> getProgramsOfDept(String universityname, String deptname)
    {
        List<String> arr = new ArrayList<>();
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select p.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment where a.userName = '"+universityname+"' and d.name = '"+deptname+"'");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
               // Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
          //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return arr;
    }
}