package com.example.myapplication.Classes;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String name;
    protected String email;
    protected String location;
    protected String password;
    protected double longitude;
    protected double latitude;
}
