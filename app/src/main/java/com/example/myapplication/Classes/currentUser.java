package com.example.myapplication.Classes;

public class currentUser{
    private static currentUser instance = null;
    Student s;

    private currentUser(Student obj)
    {
        this.s = obj;
    }

    public static currentUser getInstance(Student obj)
    {
        if(instance == null)
        {
            instance = new currentUser(obj);
        }
        return instance;
    }

    public Student getStu()
    {
        return this.s;
    }
}
