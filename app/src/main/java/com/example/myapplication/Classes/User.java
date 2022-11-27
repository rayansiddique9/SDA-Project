package com.example.myapplication.Classes;

import android.os.Parcelable;

import java.io.Serializable;

public abstract class User implements Serializable{
    protected int userID;
    protected String name;
    protected String email;
    protected String password;
    protected int isAdmin;
    protected int isDisabled;
    protected authenticateUser au;
    protected accountCreator ac;

    protected abstract String getType();
}
