package com.example.myapplication.Classes;

public class User {
    protected int idUser;
    protected String userName;
    protected String email;
    protected String location;
    protected String password;
    protected double longitude;
    protected double latitude;

    protected User(int uid,String name, String email,String location,String password,double longitude, double latitude){
        this.idUser = uid;
        this.userName = name;
        this.email = email;
        this.location = location;
        this.password = password;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
