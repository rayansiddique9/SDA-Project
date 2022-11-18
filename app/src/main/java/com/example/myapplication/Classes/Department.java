package com.example.myapplication.Classes;

import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<UndergraduteProgram> programs;

    public Department(String n, ArrayList<UndergraduteProgram> obj)
    {
        this.name = n;
        this.programs = obj;
    }
}
