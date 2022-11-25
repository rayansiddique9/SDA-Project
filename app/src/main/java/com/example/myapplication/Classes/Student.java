package com.example.myapplication.Classes;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.programSelectionEligibility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends Visitor {
    protected String educationType;
    protected String dob;
    protected String firstName;
    protected String lastName;
    protected Evaluator e;
    protected SearchUni su;
    protected managePost mp;


    public Student(String name, String email, String pass, String eduType, String d, String fname, String lname, int isadmin, int isdisabled)
    {
        super(name, email, pass, isadmin, isdisabled);
        this.educationType = eduType;
        this.dob = d;
        this.firstName = fname;
        this.lastName = lname;
        this.e = new Evaluator();
        this.su = new SearchUni();
        this.mp = new managePost();
    }

    public String getDob() {
        return dob;
    }

    public String getEducationType() {
        return educationType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void getAvalaiblePrograms(Context ptr, List<String> programlist)
    {
        e.connectToDb(ptr);
        if(this.educationType.equals("Undergraduate"))
        {
            e.fillBSlist(programlist, ptr);
        }
        else
        {
            e.fillMSlist(programlist, ptr);
        }

    }

    public ArrayList<String> getEligibleUniList(Context ptr, String preferredDeg)
    {
        e.connectToDb(ptr);
        ArrayList<String> rst;
        if(this.educationType.equals("Undergraduate"))
        {
           rst = e.getFilteredUnisUG(preferredDeg, ptr, ((UndergradStudent)this).getSubjectCombo(), ((UndergradStudent)this).getMarks());

        }
        else
        {
            rst = e.getFilteredUnisG(preferredDeg, ptr, ((GraduateStudent)this).getBsDeg(), ((GraduateStudent)this).getCgpa());
        }

        return rst;

    }

    public boolean getEligiblityStatus(Context ptr, String preferredDeg, String uname)
    {
        e.connectToDb(ptr);
        if(this.educationType.equals("Undergraduate"))
        {
            return e.getStatusBS(preferredDeg, ptr, ((UndergradStudent)this).getSubjectCombo(), ((UndergradStudent)this).getMarks(), uname);

        }
        else
        {
            return e.getStatusMS(preferredDeg, ptr, ((GraduateStudent)this).getBsDeg(), ((GraduateStudent)this).getCgpa(), uname);
        }
    }

    public void getAllPrograms(Context ptr, List<String> programlist)
    {
        e.connectToDb(ptr);
        e.fillAllPrograms(programlist, ptr);
    }

    public ArrayList<String> getFilteredUniListDeg(Context ptr, String preferredDeg)
    {
        e.connectToDb(ptr);
        ArrayList<String> rst;
        rst = e.getUnisDegFiltered(preferredDeg, ptr);
        return rst;

    }

    public ArrayList<String> getFilteredUniListRanking(Context ptr, int l, int u)
    {
        e.connectToDb(ptr);
        ArrayList<String> rst;
        rst = e.getUnisRankingFiltered(ptr, l, u);
        return rst;

    }

    public void submitReview(Context ptr, String uname, int rating, String review)
    {
        su.connectToDb(ptr);
        int uid = su.getUniId(uname);
      //  Toast.makeText(ptr, "UniId:"+String.valueOf(uid), Toast.LENGTH_SHORT).show();
        int sid = su.getStuId(this.getUsername());
      //  Toast.makeText(ptr, "StuId:"+String.valueOf(sid), Toast.LENGTH_SHORT).show();
        su.insertReview(sid, uid, rating, review);
    }

    public void getFaqs(Context ptr, List<faqInfo> ar)
    {
        su.connectToDb(ptr);
        su.getFAQs(ar);
    }

    public void giveFeedback(Context ptr, String str)
    {
        su.connectToDb(ptr);
        int uid = su.getUserId(this.getUsername());
        su.insertFeedback(str, uid);
    }

    public void getImageList(Context ptr, ArrayList<String> imgs, ArrayList<String> captions, String uniname)
    {
        mp.connectToDb(ptr);
        mp.getImages(uniname, imgs, captions);
    }

    public void getTextList(Context ptr, ArrayList<String> statuses, String uniname)
    {
        mp.connectToDb(ptr);
        mp.getTexts(uniname, statuses);
    }

    public void getVideoList(Context ptr, ArrayList<String> links, String uniname)
    {
        mp.connectToDb(ptr);
        mp.getVideos(uniname, links);
    }




}
