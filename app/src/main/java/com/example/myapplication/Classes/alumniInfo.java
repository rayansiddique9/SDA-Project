package com.example.myapplication.Classes;

public class alumniInfo {
    private String name;
    private String company;
    private String batch;

    public alumniInfo(String n, String c, String b)
    {
        this.name = n;
        this.company = c;
        this.batch = b;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBatch() {
        return batch;
    }
}
