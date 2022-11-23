package com.example.myapplication.Classes;

import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchUni implements Serializable {
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

    public SearchUni()
    {
        this.uniname = null;
    }

    public void getUnis(Context ptr, ArrayList<String> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.userName from [User] u join University un on u.idUser = un.idUniversity");
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

    public void getUniveristy(Context ptr,String universityname, ArrayList<String> depts, List<alumniInfo> arr, List<feeinfo> f, List<aidInfo> a, List<reviewInfo> r)
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

                ResultSet resultSet2 = statement.executeQuery("select p.name, p.feePerCreditHour, p.admissionFee, p.creditHours from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment where a.userName = '"+universityname+"'");
                while(resultSet2.next())
                {
                    f.add(new feeinfo(resultSet2.getString(1), resultSet2.getInt(2), resultSet2.getInt(3), resultSet2.getInt(4)));
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
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void getProgramsOfDept(Context ptr, String universityname, String deptname, List<String> arr)
    {
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
                Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void getFacultyOfDept(Context ptr, String universityname, String deptname, List<profinfo> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select f.firstName, f.lastName, f.designantion, f.email from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Faculty f on d.idDepartment = f.idDepartment where a.userName = '"+universityname+"' and d.name = '"+deptname+"'");
                while(resultSet.next())
                {
                    arr.add(new profinfo(resultSet.getString(1)+" "+resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
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

    public String getCampusLife(Context ptr, String uniname)
    {
        String str = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.campusLife from [User] us join University u on u.idUniversity = us.idUser where us.userName = '"+uniname+"'");
                while(resultSet.next())
                {
                    str = resultSet.getString(1);
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
        return str;
    }

    public void getUniReviews(Context ptr, String uniname, List<String> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select r.review from [User] us join University u on us.idUser = u.idUniversity join Review r on u.idUniversity = r.idUniversity where us.userName = '"+uniname+"'");
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

    public double getUniLatitude(Context ptr,String universityname)
    {
        double ans = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.latitude from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+universityname+"'");
                while(resultSet.next())
                {
                    ans = (double) resultSet.getFloat(1);
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
        return ans;
    }

    public double getUniLongitude(Context ptr,String universityname)
    {
        double ans = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.longitude from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+universityname+"'");

                while(resultSet.next())
                {
                    ans = (double) resultSet.getFloat(1);
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
        return ans;
    }

    public int getUniId(String universityname)
    {
        int ans = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select a.idUser from [User] a join University u on a.idUser = u.idUniversity where a.userName = '"+universityname+"'");

                while(resultSet.next())
                {
                    ans = resultSet.getInt(1);
                }


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
        return ans;
    }

    public int getStuId(String stuname)
    {
        int ans = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.idStudent from [User] a join Student u on a.idUser = u.idStudent where a.userName = '"+stuname+"'");

                while(resultSet.next())
                {
                    ans = resultSet.getInt(1);
                }


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
        return ans;
    }

    public void insertReview(int idstu, int iduni, int star, String text)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                String query = "insert into Review values("+idstu+", "+iduni+", "+star+", '"+text+"')";
                statement.executeQuery(query);
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

    public void getFAQs(List<faqInfo> arr)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select fa.question, fa.answer from FAQs fa");

                while(resultSet.next())
                {
                    arr.add(new faqInfo(resultSet.getString(1), resultSet.getString(2)));
                }
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

    public void insertFeedback(String fb, int uid)
    {
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                String query = "insert into Feedback values("+uid+", '"+fb+"')";
                statement.executeQuery(query);
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

    public int getUserId(String name)
    {
        int ans = 0;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select a.idUser from [User] a where a.userName = '"+name+"'");

                while(resultSet.next())
                {
                    ans = resultSet.getInt(1);
                }


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
        return ans;
    }

}
