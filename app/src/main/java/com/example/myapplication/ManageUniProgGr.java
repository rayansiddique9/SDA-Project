package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ManageUniProgGr extends AppCompatActivity {

    private Button bt;
    TextView tvDay;
    boolean[] selectProgram;
    ArrayList<Integer> programlist = new ArrayList<>();
    String[] programArray = {"BS CS", "BS SE", "BS ME", "BS EE", "BS CV", "BS BBA"};
    String userChoice = " ";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_uni_program_grad_input);

        bt = findViewById(R.id.button_next_to_manage_uni_from_grad);

        tvDay = findViewById(R.id.graduate_multiple_selection);

        selectProgram = new boolean[programArray.length];

        tvDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(
                    ManageUniProgGr.this
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

                Toast.makeText(ManageUniProgGr.this, userChoice,
                        Toast.LENGTH_LONG).show();

                Intent in = new Intent(ManageUniProgGr.this, ManageUniMain.class);
                startActivity(in);
            }
        });
    }

}
