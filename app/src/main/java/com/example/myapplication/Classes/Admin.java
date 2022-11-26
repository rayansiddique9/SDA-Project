package com.example.myapplication.Classes;

public class Admin extends Visitor {
    public Admin(int uid,String name, String email,String password){
        // Default Constructor for user
        super(uid,name,email,password);
        this.au = new authenticateUser();
    }

    public String getType(){
        return "Admin";
    }
}
