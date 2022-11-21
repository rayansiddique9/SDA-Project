package com.example.myapplication.Classes;

import java.util.Date;

public class UndergradStudent extends Student{
    private int marks;
    private String subjectCombo;


    public UndergradStudent(String name, String email, String pass, String eduType, String d, String fname, String lname, int m, String subjects)
    {
        super(name, email, pass, eduType, d, fname, lname);
        this.marks = m;
        this.subjectCombo = subjects;
    }

    public int getMarks() {
        return marks;
    }

    public String getSubjectCombo() {
        return subjectCombo;
    }
}
