package com.example.myapplication.Classes;

import java.util.Date;
import java.time.LocalDate;

public class Student extends User {
    //protected Enum educationType;
    protected double equivalence;
    protected LocalDate DOB;
    protected int age;
    //protected Enum subjectCombo;

    public Student(int uid,String name, String email,String location,String password,double longitude, double latitude,double equivalence){
        super(uid,name,email,location,password,longitude,latitude);
        this.age = calAge();
        this.equivalence = equivalence;
//        this.DOB = LocalDate.now();
    }
    public int calAge(/*LocalDate D*/){
        // Calculate age using todays date
        return 21;
    }
    public void getUserFromDB(int uid){}
}
