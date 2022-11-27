package com.example.myapplication.Classes;

import static com.example.myapplication.MainActivity.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Base64;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class authenticateUser {
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
         //   Toast.makeText(ptr,"Success", Toast.LENGTH_SHORT).show();

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
                ResultSet resultSet = statement.executeQuery("select u.email, s.educationType, s.DOB, s.firstName, s.lastName, ug.marks, ug.subjectCombo, u.isAdmin, u.isDisabled from [User] u join Student s on u.idUser = s.idStudent join Undergraduate ug on s.idStudent = ug.idStudent where u.userName = '"+name+"' and u.password = '"+pass+"'");

                while(resultSet.next())
                {
                    obj = new UndergradStudent(name, resultSet.getString(1), pass, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getInt(9));
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
                ResultSet resultSet = statement.executeQuery("select u.email, s.educationType, s.DOB, s.firstName, s.lastName, ug.CGPA, ug.BSDegree, u.isAdmin, u.isDisabled from [User] u join Student s on u.idUser = s.idStudent join Graduate ug on s.idStudent = ug.idStudent where u.userName = '"+name+"' and u.password = '"+pass+"'");

                while(resultSet.next())
                {
                    obj = new GraduateStudent(name, resultSet.getString(1), pass, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),  resultSet.getFloat(6), resultSet.getString(7),  resultSet.getInt(8), resultSet.getInt(9));
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

    public ArrayList<String> getUGPrereqs(String uniname, String programname)
    {
        ArrayList<String> arr = new ArrayList<>();
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select ugr.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join UndergraduateProgram ugp on p.idProgram = ugp.idUGProgram join ugReqBG ugr on ugp.idUGProgram = ugr.bgid where a.userName = '"+uniname+"' and p.name = '"+programname+"'");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
        return arr;
    }

    public ArrayList<String> getGPrereqs(String uniname, String programname)
    {
        ArrayList<String> arr = new ArrayList<>();
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select ugr.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join GraduateProgram ugp on p.idProgram = ugp.idGProgram join gReqBG ugr on ugp.idGProgram = ugr.bgid where a.userName = '"+uniname+"' and p.name = '"+programname+"'");
                while(resultSet.next())
                {
                    arr.add(resultSet.getString(1));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
        return arr;
    }

    public ArrayList<UndergraduteProgram> getUGProgramsOfDept(String universityname, String deptname)
    {
        ArrayList<UndergraduteProgram> arr = new ArrayList<UndergraduteProgram>();
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select p.name, p.creditHours, p.feePerCreditHour, ugp.minMarks from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join UndergraduateProgram ugp on p.idProgram = ugp.idUGProgram where a.userName = '"+universityname+"' and d.name = '"+deptname+"'");
                while(resultSet.next())
                {
                    arr.add(new UndergraduteProgram(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), getUGPrereqs(universityname, resultSet.getString(1))));
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
           // Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
        return arr;
    }


    public ArrayList<GraduateProgram> getGProgramsOfDept(String universityname, String deptname)
    {
        ArrayList<GraduateProgram> arr = new ArrayList<GraduateProgram>();
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(" select p.name, p.creditHours, p.feePerCreditHour, ugp.minCGPA from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join GraduateProgram ugp on p.idProgram = ugp.idGProgram where a.userName = '"+universityname+"' and d.name = '"+deptname+"'");
                while(resultSet.next())
                {
                    arr.add(new GraduateProgram(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getFloat(4), getGPrereqs(universityname, resultSet.getString(1))));
                    //   Toast.makeText(ptr,resultSet.getString(1), Toast.LENGTH_SHORT).show();
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
        return arr;
    }


    public void getFacultyOfDept(String universityname, String deptname, List<profinfo> arr)
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
              //  Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
          //  Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }
    }

    public University getUni(Context ptr, String name, String pass)
    {
        University obj = null;
        ArrayList<Department> dep = new ArrayList<Department>();
        ArrayList<aidInfo> aids = new ArrayList<aidInfo>();
        ArrayList<alumniInfo> alums = new ArrayList<alumniInfo>();
        ArrayList<reviewInfo> reviews = new ArrayList<reviewInfo>();
        ArrayList<profinfo> faculty = new ArrayList<profinfo>();
        ArrayList<feeinfo> fees = new ArrayList<feeinfo>();
        ArrayList<imageClass> imgs = new ArrayList<imageClass>();
        ArrayList<String> s1 = new ArrayList<>(), s2 = new ArrayList<>();

        int uid = 0;

        if(connection != null)
        {
            Statement statement = null;

            try {

                statement = connection.createStatement();

                ResultSet resultSet3 = statement.executeQuery("select d.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity where a.userName = '" + name + "' and a.password = '" + pass + "'");

                while (resultSet3.next()) {
                    dep.add(new Department(resultSet3.getString(1), getUGProgramsOfDept(name, resultSet3.getString(1)), getGProgramsOfDept(name, resultSet3.getString(1))));
                    getFacultyOfDept(name, resultSet3.getString(1), faculty);
                }

            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Department me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet1 = statement.executeQuery("select al.name, al.placementCompany, al.batch from [User] a join University u on a.idUser = u.idUniversity join Alumni al on u.idUniversity = al.idUniversity where a.userName = '" + name + "'");
                while (resultSet1.next()) {
                    alums.add(new alumniInfo(resultSet1.getString(1), resultSet1.getString(2), Integer.toString(resultSet1.getInt(3))));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Alumni me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet2 = statement.executeQuery("select fa.name, fa.detail from [User] a join University u on a.idUser = u.idUniversity join FinancialAid fa on u.idUniversity = fa.idUniversity where a.userName = '" + name + "'");
                while (resultSet2.next()) {
                    aids.add(new aidInfo(resultSet2.getString(1), resultSet2.getString(2)));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Aid me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet4 = statement.executeQuery("select r.review, r.stars from [User] a join University u on a.idUser = u.idUniversity join Review r on u.idUniversity = r.idUniversity where a.userName = '" + name + "'");
                while (resultSet4.next()) {
                    reviews.add(new reviewInfo(resultSet4.getString(1), resultSet4.getInt(2)));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Review me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet5 = statement.executeQuery("select p.name, p.feePerCreditHour, p.creditHours from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on d.idDepartment = p.idDepartment where a.userName = '" + name + "'");
                while (resultSet5.next()) {
                    fees.add(new feeinfo(resultSet5.getString(1), resultSet5.getInt(2), resultSet5.getInt(3)));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Fee me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet6 = statement.executeQuery("select i.imgBin, i.description from University u join Image i on u.idUniversity = i.idUniversity where u.idUniversity = " + uid);

                while (resultSet6.next()) {
                    byte[] decodeString = Base64.decode(resultSet6.getString(1), Base64.DEFAULT);
                    Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                    imgs.add(new imageClass(decodebitmap, resultSet6.getString(2)));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "Image me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet7 = statement.executeQuery("select u.idUniversity from [User] a join University u on a.idUser = u.idUniversity where a.userName = '" + name + "'");
                while (resultSet7.next()) {
                    uid = resultSet7.getInt(1);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "uid me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet8 = statement.executeQuery("select i.description from University u join text i on u.idUniversity = i.idUniversity where u.idUniversity = " + uid);
                while (resultSet8.next()) {
                    s1.add(resultSet8.getString(1));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "text me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet9 = statement.executeQuery("select i.link from University u join Video i on u.idUniversity = i.idUniversity where u.idUniversity = " + uid);
                while (resultSet9.next()) {
                    s2.add(resultSet9.getString(1));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, "video me masla", Toast.LENGTH_SHORT).show();
            }

            try {
                ResultSet resultSet = statement.executeQuery("select u.email, s.phone, s.campusLife, s.ranking, s.location, s.longitude, s.latitude, u.isAdmin, u.isDisabled, s.admissionFee from [User] u join University s on u.idUser = s.idUniversity where u.userName = '" + name + "' and u.password = '" + pass + "'");
                while (resultSet.next()) {
                    obj = new University(name, resultSet.getString(1), pass, resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), (double) resultSet.getFloat(6), (double) resultSet.getFloat(7), resultSet.getInt(8), resultSet.getInt(9), dep, resultSet.getInt(10), aids, alums, reviews, faculty, fees, imgs, s2, s1);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


                /*
                ResultSet resultSet;
                ResultSet resultSet1;
                ResultSet resultSet2;

                for(int l = 0; l < dname.size(); l++)
                {
                  //  Toast.makeText(ptr, "Dname:"+dname.get(l), Toast.LENGTH_SHORT).show();

                    resultSet = statement.executeQuery("select distinct p.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join UndergraduateProgram ugp on p.idProgram = ugp.idUGProgram where a.userName = '" + name + "' and a.password = '" + pass + "' and d.name = '"+dname.get(l)+"'");
                    while (resultSet.next()) {
                        s1.add(resultSet.getString(1));
                    }

                    if (s1.size() != 0) {
                        for (int z = 0; z < s1.size(); z++) {
                         //   Toast.makeText(ptr, "ugProgram:"+s1.get(z), Toast.LENGTH_SHORT).show();
                            resultSet1 = statement.executeQuery("select ugr.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join UndergraduateProgram ugp on p.idProgram = ugp.idUGProgram join ugReqBG ugr on ugp.idUGProgram = ugr.bgid where a.userName = '" + name + "' and a.password = '" + pass + "' and p.name = '" + s1.get(z) + "'");
                            while (resultSet1.next()) {
                                req1.add(resultSet1.getString(1));
                            }

                            resultSet2 = statement.executeQuery("select top(1) p.creditHours, p.feePerCreditHour, ugp.minMarks from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join UndergraduateProgram ugp on p.idProgram = ugp.idUGProgram where a.userName = '" + name + "' and a.password = '" + pass + "' and p.name = '" + s1.get(z) + "'");
                            ugprograms.add(new UndergraduteProgram(s1.get(z), resultSet2.getInt(1), resultSet2.getInt(2), resultSet2.getInt(3), req1));
                            req1.clear();
                        }
                    }

                    resultSet = statement.executeQuery("select distinct p.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join GraduateProgram ugp on p.idProgram = ugp.idGProgram where a.userName = '" + name + "' and a.password = '" + pass + "' and d.name = '"+dname.get(l)+"'");
                    while (resultSet.next()) {
                        s2.add(resultSet.getString(1));
                    }

                    if (s2.size() != 0) {
                        for (int z = 0; z < s2.size(); z++) {
                            resultSet1 = statement.executeQuery("select ugr.name from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join GraduateProgram ugp on p.idProgram = ugp.idGProgram join gReqBG ugr on ugp.idGProgram = ugr.bgid where a.userName = '" + name + "' and a.password = '" + pass + "' and p.name = '" + s2.get(z) + "'");
                            while (resultSet1.next()) {
                                req2.add(resultSet1.getString(1));
                            }

                            resultSet2 = statement.executeQuery("select top(1) p.creditHours, p.feePerCreditHour, ugp.minCGPA from [User] a join University u on a.idUser = u.idUniversity join Department d on u.idUniversity = d.idUniversity join Program p on p.idDepartment = d.idDepartment join GraduateProgram ugp on p.idProgram = ugp.idGProgram where a.userName = '" + name + "' and a.password = '" + pass + "' and p.name = '" + s2.get(z) + "'");
                            gprograms.add(new GraduateProgram(s1.get(z), resultSet2.getInt(1), resultSet2.getInt(2), resultSet2.getFloat(3), req2));
                            req2.clear();
                        }
                    }


                    dep.add(new Department(dname.get(l), ugprograms, gprograms));
                    ugprograms.clear();
                    gprograms.clear();
                }*/
        }
        else
        {
            Toast.makeText(ptr,"Connection is null", Toast.LENGTH_SHORT).show();
        }

        return obj;
    }

    public Admin getAdmin(String name, String pass)
    {
        Admin obj = null;
        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select u.email, u.isAdmin, u.isDisabled from [User] u where u.userName = '"+name+"' and u.password = '"+pass+"'");

                while(resultSet.next())
                {
                    obj = new Admin(name, resultSet.getString(1), pass, resultSet.getInt(2), resultSet.getInt(3));
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

        return obj;
    }



    public boolean verifyInfoUni(Context ptr, String name, String pass) {

        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] us join University s on us.idUser = s.idUniversity where us.userName = '"+name+"' and us.password = '"+pass+"' and us.isDisabled = 0");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return true;
                    }
                    return false;
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

        return false;
    }


    public boolean verifyInfoAdmin(Context ptr, String name, String pass) {

        if(connection != null)
        {
            Statement statement = null;

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select 1 from [User] us where us.userName = '"+name+"' and us.password = '"+pass+"' and us.isAdmin = 1");

                while(resultSet.next())
                {
                    if (resultSet.getString(1).equals("1")) {
                        return true;
                    }
                    return false;
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

        return false;
    }

}
