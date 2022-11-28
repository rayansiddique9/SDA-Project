package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ManageUniProgInput extends AppCompatActivity {

    private Button bt;
    //private TextInputEditText ti;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_uni_program_input);

       // String Prog = getIntent().getExtras().getString("Program");
//        int value = getIntent().getExtras().getInt("Program");
        int value =0;
        bt = findViewById(R.id.button_next_to_manage_uni);








        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText name = findViewById(R.id.programmeName);
                EditText credithr = findViewById(R.id.creditHr);
                EditText feepercredit = findViewById(R.id.feePerCreditHr);

                String progname = name.getText().toString();
                String progcredit = String.valueOf(credithr.getText().toString());
                String progfee = String.valueOf(feepercredit.getText().toString());
//                Toast.makeText(ManageUniProgInput.this, ((char) value),
//                        Toast.LENGTH_LONG).show();

                if(value == 1)
                {

                    Intent in = new Intent(ManageUniProgInput.this, ManageUniProgGr.class);
                    in.putExtra("Namekey", progname);
                    in.putExtra("credithrkey", credithr.getInputType());
                    in.putExtra("feepercreditkey", feepercredit.getInputType());
                    startActivity(in);
                }

                else if(value == 0)
                {
                    Log.d("info:", progname + progcredit + progfee);
                    System.out.println(progname);

                    Intent in = new Intent(ManageUniProgInput.this, ManageUniProgUnderGr.class);
                    in.putExtra("Namekey", progname);
                    in.putExtra("credithrkey", progcredit);
                    in.putExtra("feepercreditkey", progfee);
                    startActivity(in);
                }


            }
        });
    }
}
