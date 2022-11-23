package com.example.myapplication.Classes;

public abstract class  Program {
    protected String name;
    protected int creditHours;
    protected int feepch;
    protected int admissionfees;

    public Program(String name, int ch, int fpch, int admissionfee) {
        this.name = name;
        this.creditHours = ch;
        this.feepch = fpch;
        this.admissionfees = admissionfee;
    }
}
