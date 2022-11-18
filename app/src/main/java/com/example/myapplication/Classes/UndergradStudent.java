package com.example.myapplication.Classes;

public class UndergradStudent extends Student{
    private int marks;
    private String subjectCombo;

    UndergradStudent(int m, String subjects)
    {
        this.educationType = "Intermediate";
        this.marks = m;
        this.subjectCombo = subjects;
    }

}
