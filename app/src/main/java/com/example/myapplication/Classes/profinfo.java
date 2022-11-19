package com.example.myapplication.Classes;

public class profinfo {
    private String profname;
    private String designation;
    private String email;

    public profinfo(String profName, String Designation, String Email)
    {
        this.profname = profName;
        this.designation = Designation;
        this.email = Email;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEmail() {
        return email;
    }

    public String getProfname() {
        return profname;
    }
}
