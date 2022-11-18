package com.example.myapplication.Classes;

import java.util.ArrayList;

public class University extends User{
    private String phone;
    private String campusLife;
    private int ranking;
    ArrayList<Department> departments;

    public University(String name, ArrayList<Department> depts)
    {
        this.name = name;
        this.departments = depts;
        this.campusLife = "v good";
        this.ranking = 0;
        this.phone = null;
        this.email = null;
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.password = null;
        this.location = null;
    }
}
