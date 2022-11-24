package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.Classes.accountManager;

import java.util.ArrayList;

public class feedbackShowAdmin extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listfeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_show_admin);

        listfeedback = new ArrayList<>();
        accountManager am = new accountManager();
        am.connectToDb(this);
        am.getFeedback(this, listfeedback);

        listView = findViewById(R.id.fblist);
        adapterFeedback ad = new adapterFeedback(this, R.layout.feedback_list_row, listfeedback);
        listView.setAdapter(ad);
    }
}