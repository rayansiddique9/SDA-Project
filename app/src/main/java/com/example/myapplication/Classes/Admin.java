package com.example.myapplication.Classes;

public class Admin extends User {
    int isAdmin;
    public Admin(int uid,String name, String email,String location,String password,double longitude, double latitude){
        // Default Constructor for user
        super(uid,name,email,location,password,longitude,latitude);
        isAdmin = 1;
    }
}
