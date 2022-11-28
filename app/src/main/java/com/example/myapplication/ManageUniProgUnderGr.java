package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Classes.UniversityManager;

import java.util.ArrayList;
import java.util.Collections;

public class ManageUniProgUnderGr extends AppCompatActivity {
    private Button bt;
    TextView tvDay;
    boolean[] selectProgram;
    ArrayList<Integer> programlist = new ArrayList<>();
    String[] programArray = {"pre-engineering", "pre-medical", "ICS", "FA", "FSC", "Business Studies"};
    String userChoice = " ";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_uni_program_undergrad_input);

        String ProgramName = getIntent().getExtras().getString("Namekey");
        int credithr = Integer.valueOf( getIntent().getExtras().getString("credithrkey"));
        int feepercredit = Integer.valueOf( getIntent().getExtras().getString("feepercreditkey"));

//        Toast.makeText(ManageUniProgUnderGr.this, "name:"+ProgramName ,
//                Toast.LENGTH_LONG).show();

        Log.d("info:", ProgramName+credithr+feepercredit);


        //Unimanager Account Creation

        UniversityManager obj = new UniversityManager();
        obj.connectToDb(ManageUniProgUnderGr.this);

        bt = findViewById(R.id.button_next_to_manage_uni_from_undergrad);
        tvDay = findViewById(R.id.undergraduate_multiple_selection);

        selectProgram = new boolean[programArray.length];




        tvDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ManageUniProgUnderGr.this
                );

                builder.setTitle("Select Program Requirement");

                builder.setCancelable(false);

                builder.setMultiChoiceItems(programArray, selectProgram, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                        if(b){
                            programlist.add(i);


                            Collections.sort(programlist);
                        }

                        else{
                            programlist.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for(int j = 0; j < programlist.size(); j++) {
                            stringBuilder.append(programArray[programlist.get(j)]);

                            if(j != programlist.size() - 1) {
                                stringBuilder.append(", ");
                            }

                            tvDay.setText(stringBuilder.toString());

                        }

                        userChoice = stringBuilder.toString();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        for(int j = 0; j < selectProgram.length; j++) {

                            selectProgram[j] = false;

                            programlist.clear();

                            tvDay.setText("");

                        }


                    }
                });

                builder.show();



            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText ReqMarks = findViewById(R.id.MinMarks);
                int prog_req_marks = Integer.valueOf(ReqMarks.getText().toString());

                Log.d("Marks:","marks: "+prog_req_marks);

//                + credithr + feepercredit +ReqMarks.getInputType()

                obj.createProgramUnderGraduate(ManageUniProgUnderGr.this,ProgramName,credithr,feepercredit,prog_req_marks, userChoice);

//                obj.createProgramUnderGraduate(ManageUniProgUnderGr.this,"Computer Science",3,8500,800, userChoice);


                Intent in = new Intent(ManageUniProgUnderGr.this, ManageUniMain.class);
                startActivity(in);
            }
        });
    }

}
