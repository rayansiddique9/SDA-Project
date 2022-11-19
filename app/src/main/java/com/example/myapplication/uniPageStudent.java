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

import com.example.myapplication.Classes.SearchUni;
import com.example.myapplication.Classes.aidInfo;
import com.example.myapplication.Classes.alumniInfo;
import com.example.myapplication.Classes.feeinfo;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class uniPageStudent extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView tname;
    View headerview;
    ArrayList<String> depts;
    List<alumniInfo> arr;
    List<feeinfo> fees;
    List<aidInfo> aid;

    private void loadFragment(Fragment fragment, boolean flag, Bundle b) {
        fragment.setArguments(b);
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

        SearchUni obj = new SearchUni();             //making instance of persistance class
        obj.connectToDb(uniPageStudent.this);   //connecting to db

        drawerLayout = findViewById(R.id.side_menu);
        navigationView = findViewById(R.id.sidenav);
        toolbar = findViewById(R.id.toolbar1);
        headerview = navigationView.getHeaderView(0);
        tname = (TextView) headerview.findViewById(R.id.menu_uniname);

        String s = getIntent().getExtras().getString("uniname");
        tname.setText(s);

        depts = new ArrayList<String>();
        arr = new ArrayList<alumniInfo>();
        fees = new ArrayList<feeinfo>();
        aid = new ArrayList<aidInfo>();

        obj.getUniveristy(uniPageStudent.this, s, depts, arr, fees, aid);   //gets uni content from db



        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Bundle bundle = new Bundle();
        bundle.putString("universityName", s);

        loadFragment(new campusLife_frag(),false, bundle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.menu_faculty)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putStringArrayList("dept", depts);
                    b.putSerializable("obj", obj);
                    loadFragment(new faculty_frag(), true, b);
                }
                else if(id == R.id.menu_fees)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putSerializable("fee", (Serializable) fees);
                    loadFragment(new fees_frag(), true, b);
                }
                else if(id == R.id.menu_degree)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putStringArrayList("dept", depts);
                    b.putSerializable("obj", obj);
                    loadFragment(new programsOffered_frag(), true, b);
                }
                else if(id == R.id.menu_alumni)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putSerializable("alumnis", (Serializable) arr);
                    loadFragment(new AlumniPlacement_frag(), true, b);
                }
                else if(id == R.id.menu_aid)
                {
                    Bundle b = new Bundle();
                    b.putString("universityName", s);
                    b.putSerializable("a", (Serializable) aid);
                    loadFragment(new FinancialAid_frag(), true, b);
                }
                else if(id == R.id.menu_review)
                {
                    loadFragment(new Reviews_frag(), true, bundle);
                }
                else if(id == R.id.menu_eligibility)
                {
                    loadFragment(new Eligibility_frag(), true, bundle);
                }
                else if(id == R.id.menu_campus)
                {
                    loadFragment(new campusLife_frag(), true, bundle);
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