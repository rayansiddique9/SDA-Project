package com.example.myapplication.Classes;

public class GraduateStudent extends Student{

    private float cgpa;
    private String bsDeg;

    public GraduateStudent(int uid,String name, String email, String pass, String eduType, String d, String fname, String lname, float Cgpa, String bsdeg)
    {
        super(uid,name, email, pass, eduType, d, fname, lname);
        this.cgpa = Cgpa;
        this.bsDeg = bsdeg;
    }

    public float getCgpa() {
        return cgpa;
    }

    public String getBsDeg() {
        return bsDeg;
    }

}
