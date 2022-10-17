package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class homePage extends AppCompatActivity {

    private BottomNavigationView bnView;

    // loads the fragment on tha basis of given 'fragment'
    private void loadFrag(Fragment fragment, boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (!flag){
            ft.add(R.id.container , fragment);
        }else{
            ft.replace(R.id.container , fragment);
        }
        ft.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        bnView = findViewById(R.id.nav_view);


        bnView.setSelectedItemId(R.id.navigation_home);
        loadFrag(new homeFrag(),false);

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId() ;
                if (id == R.id.navigation_home){
                    loadFrag(new homeFrag(),true);
                }else if (id == R.id.navigation_profile){
                    loadFrag(new profileFrag(),true);
                }else{
                    loadFrag(new settingsFrag(),true);
                }
                return true;
            }
        });
    }
}