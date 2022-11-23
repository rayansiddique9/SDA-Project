package com.example.myapplication.Classes;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.contextaware.ContextAwareHelper;

public class Visitor extends User{

    protected accountCreator ac;

    public Visitor()
    {
        this.au = new authenticateUser();
        this.name = null;
        this.email = null;
        this.password = null;
    }

    public Visitor(String name, String email, String pass, int admin, int disabled)
    {
        this.name = name;
        this.email = email;
        this.password = pass;
        this.isAdmin = admin;
        this.isDisabled = disabled;
    }

    public boolean signInStu(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        if(this.au.verifyInfoStu(ptr, uname.getText().toString(), pass.getText().toString()) == true)
        {

            return true;
        }
        return false;
    }

    public String getEducationType(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        return this.au.getEduType(ptr, uname.getText().toString(), pass.getText().toString());
    }

    public UndergradStudent makeUndergradObj(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
       return this.au.getUGStu(ptr, uname.getText().toString(), pass.getText().toString());
    }

    public GraduateStudent makeGradObj(EditText uname, EditText pass, Context ptr)
    {
        this.au.connectToDb(ptr);
        return this.au.getGStu(ptr, uname.getText().toString(), pass.getText().toString());
    }

}
