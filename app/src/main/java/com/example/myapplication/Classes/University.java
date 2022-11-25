package com.example.myapplication.Classes;

import android.content.Context;

import java.util.ArrayList;

public class University extends Visitor{
    private String phone;
    private String campusLife;
    private int ranking;
    protected String location;
    protected double longitude;
    protected double latitude;
    ArrayList<Department> departments;
    private managePost mp;


    University(String name, String email, String pass, String ph, String campus, int rank, String location, double longitude, double latitude, int isadmin, int isdisabled, ArrayList<Department> depts) {
        super(name, email, pass, isadmin, isdisabled);
        this.phone = ph;
        this.campusLife = campus;
        this.ranking = rank;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.departments = depts;
        this.mp = new managePost();
    }

    public University()
    {
        this.name = null;
        this.email = null;
        this.password = null;
        this.isAdmin = 0;
        this.isDisabled = 0;
        this.au = null;
        this.phone = null;
        this.campusLife = null;
        this.ranking = 0;
        this.location = null;
        this.longitude = 0;
        this.latitude = 0;
        this.departments = null;
        this.mp = new managePost();
    }

//    @Override
//    public String getUsername() {
//        return super.getUsername();
//    }

    /*public void getDepartments(ArrayList<String> arr) {
        for(int i = 0; i < departments.size(); i++)
        {
            arr.add(departments.get(i).getName());
        }
    }*/

   /* public ArrayList<Department> getDepartments() {
        return departments;
    }*/


  /*  public void postImage(Context ptr, String img)
    {
        this.mp.connectToDb(ptr);


    }*/

}
