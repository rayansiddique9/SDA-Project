package com.example.myapplication.Classes;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class University extends Visitor{
    private String phone;
    private String campusLife;
    private int ranking;
    protected String location;
    protected double longitude;
    protected double latitude;
    private int admissionFee;
    private ArrayList<Department> departments;
    private ArrayList<aidInfo> aids;
    private ArrayList<alumniInfo> alums;
    private ArrayList<reviewInfo> reviews;
    private ArrayList<profinfo> faculty;
    private ArrayList<feeinfo> fees;
    private ArrayList<imageClass> imgs;
    private ArrayList<String> vids;
    private ArrayList<String> txts;
    private managePost mp;
    private viewProfile vp;

    University(String name, String email, String pass, String ph, String campus, int rank, String location, double longitude, double latitude, int isadmin, int isdisabled, ArrayList<Department> depts, int af, ArrayList<aidInfo> a, ArrayList<alumniInfo> alumnis, ArrayList<reviewInfo> r, ArrayList<profinfo> faculti, ArrayList<feeinfo> fee, ArrayList<imageClass> im, ArrayList<String> vid, ArrayList<String> txt) {
        super(name, email, pass, isadmin, isdisabled);
        this.phone = ph;
        this.campusLife = campus;
        this.ranking = rank;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.departments = depts;
        this.admissionFee = af;
        this.aids = a;
        this.alums = alumnis;
        this.reviews = r;
        this.faculty = faculti;
        this.fees = fee;
        this.imgs = im;
        this.vids = vid;
        this.txts = txt;
        this.mp = new managePost();
        this.vp = new viewProfile();
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<aidInfo> getAids() {
        return aids;
    }

    public ArrayList<alumniInfo> getAlums() {
        return alums;
    }

    public ArrayList<feeinfo> getFees() {
        return fees;
    }

    public ArrayList<profinfo> getFaculty() {
        return faculty;
    }

    public ArrayList<reviewInfo> getReviews() {
        return reviews;
    }

    public ArrayList<imageClass> getImgs() {
        return imgs;
    }

    public ArrayList<String> getTxts() {
        return txts;
    }

    public ArrayList<String> getVids() {
        return vids;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getRanking() {
        return ranking;
    }

    public String getCampusLife() {
        return campusLife;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public int getAdmissionFee() {
        return admissionFee;
    }

    public void getUni(Context ptr, String universityname, ArrayList<String> depts, List<alumniInfo> arr, List<feeinfo> f, List<aidInfo> a, List<reviewInfo> r)
    {
        this.vp.connectToDb(ptr);
        this.vp.getUniveristy(universityname, depts, arr, f, a, r);
    }
}
