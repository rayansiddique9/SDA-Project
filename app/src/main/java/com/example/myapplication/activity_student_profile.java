package com.example.myapplication;

        import static com.example.myapplication.MainActivity.Classes;

        import androidx.appcompat.app.AppCompatActivity;
        //import com.google.firebase.firestore.auth.User;

        import android.os.Bundle;
        import android.os.StrictMode;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.sql.CallableStatement;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

public class activity_student_profile extends AppCompatActivity {
    private static String url = "jdbc:jtds:sqlserver://192.168.0.68:1433/UniGrab";
    private static String username = "root";
    private static String password = "123456";
    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        TextView _fName = findViewById(R.id.firstname);
        TextView _lName = findViewById(R.id.lastname);
        TextView _cgpa = findViewById(R.id.CGPA);
        TextView _loc = findViewById(R.id.location);
        TextView _longi = findViewById(R.id.longitude);
        TextView _lati = findViewById(R.id.latitude);
        TextView _aemoon = findViewById(R.id.emmail);
        TextView _dob = findViewById(R.id.DOB);
        TextView _subj=findViewById(R.id.subjectcomb);





        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // This is usd for output display
        String strlog = "";

        /*
         * This is where we connect to DB server,
         * we always connect in a try catch blocks
         */
        try {
            // Classes is string with value "net.sourceforge.jtds.jdbc.Driver"
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            //    Toast.makeText(ptr,"Success", Toast.LENGTH_SHORT).show();
            //_fName.setText("Success");

        } catch (Exception e) {
            e.printStackTrace();
            //_fName.setText("Fail");
            //  Toast.makeText(ptr, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        /*
         * This code is only executed when we have successfully connected to DB
         */
        if (connection != null) {
            Statement statement = null;

            try {

                // For Query
                //statement = connection.createStatement();
                //ResultSet result = statement.executeQuery("SELECT * FROM [User] join [Student] on idUser=idStudent join [Graduate] on idStudent=idGraduate\n");

                // For Procedure with no parameters
                //CallableStatement callableStatement = connection.prepareCall("{call getAllUsers()}");
                //ResultSet result = callableStatement.executeQuery();

                // For Procedure with input parameters
                CallableStatement callableStatement = connection.prepareCall("{call viewStudentProfile(?)}");
                //passing parameters
                callableStatement.setInt("uid",5);
                ResultSet result = callableStatement.executeQuery();

                // // For Procedure with output parameters
                //     CallableStatement callableStatement = connection.prepareCall("{call enable_Account(?,?)}");
                //     //passing input parameters
                //     callableStatement.setInt("uid",1);
                //     //passing output parameter (java.sql.Types.INTEGER)
                //     callableStatement.registerOutParameter("isSuccess", Types.INTEGER);
                //     ResultSet result = callableStatement.executeQuery();


                // next() Controls Row Number
                // result.get(i) Controls Column Number
                // 'i' will always start from 1

                while(result.next()){

                    /*
                     * This is Sample code to get data by Column Number
                     * where 'i' co-responds to column number.
                     */
                    // for(int i = 1; i <= 11; i++){
                    //     strlog += result.getString(i) + " :";
                    // }
                    // strlog += "\n";

                    /*
                     * This is Sample code to get data by Column Name
                     * where input parameter is column name.
                     */
                    _aemoon.setText(result.getString("email"));
                    _fName.setText(result.getString("firstName"));
                    _lName.setText(result.getString("latName"));
                    _lati.setText(result.getString("latitude"));
                    _longi.setText(result.getString("longitude"));
                    _cgpa.setText(result.getString("CGPA"));
                    _subj.setText(result.getString("subjectCombo"));
                    _loc.setText(result.getString("location"));
                    _dob.setText(result.getString("DOB"));

                    Toast.makeText(this,result.getString("firstName"), Toast.LENGTH_SHORT).show();

                }
            } catch (SQLException e) {
                e.printStackTrace();
                //Toast.makeText(ptr,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}