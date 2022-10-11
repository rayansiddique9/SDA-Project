package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class selectAccount extends AppCompatActivity{

  //  private Spinner acc;
    AutoCompleteTextView auto;
    ArrayAdapter<String> arr;
    String []accounts = {"Student", "University"};
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);

     /*   acc = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acc.setAdapter(adapter);*/

        auto = findViewById(R.id.act);
        arr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,accounts);
        auto.setAdapter(arr);
        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();

            }
        });


    }

}