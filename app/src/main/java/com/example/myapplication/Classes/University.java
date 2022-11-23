package com.example.myapplication.Classes;

import java.util.ArrayList;

public class University extends Visitor{
    private String phone;
    private String campusLife;
    private int ranking;
    protected String location;
    protected double longitude;
    protected double latitude;
    ArrayList<Department> departments;


    University(String name, String email, String pass, String ph, String campus, int rank, String location, double longitude, double latitude, int isadmin, int isdisabled, ArrayList<Department> depts) {
        super(name, email, pass, isadmin, isdisabled);
        this.phone = ph;
        this.campusLife = campus;
        this.ranking = rank;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.departments = depts;
    }

    /*public void getDepartments(ArrayList<String> arr) {
        for(int i = 0; i < departments.size(); i++)
        {
            arr.add(departments.get(i).getName());
        }
    }*/

    public ArrayList<Department> getDepartments() {
        return departments;
    }
}
