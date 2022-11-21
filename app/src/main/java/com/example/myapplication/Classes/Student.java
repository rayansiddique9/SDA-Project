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


    public Student(String name, String email, String pass, String eduType, String d, String fname, String lname)
    {
        super(name, email, pass);
        this.educationType = eduType;
        this.dob = d;
        this.firstName = fname;
        this.lastName = lname;
        this.e = new Evaluator();
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


}
