package com.example.myapplication.Classes;

import java.util.ArrayList;

public class University extends User{
    private String phone;
    private String campusLife;
    private int ranking;
    ArrayList<Department> departments;

//    public University(String name, ArrayList<Department> depts)
//    {
//        this.userName = name;
//        this.departments = depts;
//        this.campusLife = "v good";
//        this.ranking = 0;
//        this.phone = null;
//        this.email = null;
//        this.latitude = 0.0;
//        this.longitude = 0.0;
//        this.password = null;
//        this.location = null;
//    }
    public University(int uid,String name, String email,String location,String password,double longitude, double latitude,double equivalence, ArrayList<Department> depts)
    {
        super(uid,name,email,location,password,longitude,latitude);
        this.phone = null;
        this.campusLife = "v good";
        this.ranking = 0;
        this.departments = depts;
    }
}
