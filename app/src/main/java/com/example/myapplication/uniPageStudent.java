package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class uniPageStudent extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView tname;
    View headerview;


    private void loadFragment(Fragment fragment, boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!flag){
            ft.add(R.id.container1 , fragment);
        }else{
            ft.replace(R.id.container1 , fragment);
        }
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_page_student);

        drawerLayout = findViewById(R.id.side_menu);
        navigationView = findViewById(R.id.sidenav);
        toolbar = findViewById(R.id.toolbar1);
        headerview = navigationView.getHeaderView(0);
        tname = (TextView) headerview.findViewById(R.id.menu_uniname);

        String s = getIntent().getExtras().getString("uniname");
       // Toast.makeText(uniPageStudent.this, "Name: " + s, Toast.LENGTH_SHORT).show();
        tname.setText(s);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        loadFragment(new campusLife_frag(),false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.menu_faculty)
                {
                    loadFragment(new faculty_frag(), true);
                }
                else if(id == R.id.menu_fees)
                {
                    loadFragment(new fees_frag(), true);
                }
                else if(id == R.id.menu_degree)
                {
                    loadFragment(new programsOffered_frag(), true);
                }
                else if(id == R.id.menu_alumni)
                {
                    loadFragment(new AlumniPlacement_frag(), true);
                }
                else if(id == R.id.menu_aid)
                {
                    loadFragment(new FinancialAid_frag(), true);
                }
                else if(id == R.id.menu_review)
                {
                    loadFragment(new Reviews_frag(), true);
                }
                else if(id == R.id.menu_eligibility)
                {
                    loadFragment(new Eligibility_frag(), true);
                }
                else if(id == R.id.menu_campus)
                {
                    loadFragment(new campusLife_frag(), true);
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });


    }






 /*   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Toast.makeText(uniPageStudent.this,"ANDER AAGYA", Toast.LENGTH_SHORT).show();
        if(id == R.id.menu_fees)
        {
            Intent intent = new Intent(uniPageStudent.this, uniFees.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}