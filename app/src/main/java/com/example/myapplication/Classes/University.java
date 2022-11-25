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


    University(int uid, String name, String email, String pass, String ph, String campus, int rank, String location, double longitude, double latitude) {
        super(uid,name, email, pass);
        this.phone = ph;
        this.campusLife = campus;
        this.ranking = rank;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
