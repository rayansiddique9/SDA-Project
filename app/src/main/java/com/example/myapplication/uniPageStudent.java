package com.example.myapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.android.material.navigation.NavigationView;


public class uniPageStudent extends AppCompatActivity {
    private String deptList[] = {"Department Of \n Computer Science", "Department Of \n Electrical Engineering", "Department Of \n Software Engineering", "Department Of \n Mechanical Engineering", "Department Of \n Data Science", "Department Of \n AI"};
    private String degreeList[] = {"BS-CS", "BS-EE", "BS-SE", "MS-CS", "MS-AI"};
    private ListView listView, listView2;
    private ScrollView scroll;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_page_student);

        listView = findViewById(R.id.deptlist);
        listView2 = findViewById(R.id.degreelist);
     //   scroll = findViewById(R.id.sv);

        drawerLayout = findViewById(R.id.side_menu);
        navigationView = findViewById(R.id.sidenav);
        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                view.onTouchEvent(motionEvent);
                return true;
            }
        });


        adapterDeptList ad = new adapterDeptList(getApplicationContext(), deptList);
        listView.setAdapter(ad);


        adapterDegreeList ad2 = new adapterDegreeList(getApplicationContext(), degreeList);
        listView2.setAdapter(ad2);



    }

}