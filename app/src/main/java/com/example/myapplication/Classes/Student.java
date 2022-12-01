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
    protected viewProfile vp;
    protected uniComparer uc;


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
        this.vp = new viewProfile();
        this.uc = new uniComparer();
    }

    public Student(int uid,String name, String email,String d, String fname, String lname,int isdisabled)
    {
        super(name, email, "NULL", 0, isdisabled);
        this.educationType = "NULL";
        this.dob = d;
        this.firstName = fname;
        this.lastName = lname;
        this.uid = uid;
        this.e = null;
        this.su = null;
        this.mp = null;
        this.vp = null;
        this.uc = null;
    }

    public Student(int uid,String name, String email,int isdisabled)
    {
        super(name, email, "NULL", 0, isdisabled);
        this.educationType = "NULL";
        this.uid = uid;
        this.e = null;
        this.su = null;
        this.mp = null;
        this.vp = null;
        this.uc = null;
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

    public void getAvalaiblePrograms(Context ptr, String uniname, List<String> programlist)
    {
        e.connectToDb(ptr);
        if(this.educationType.equals("Undergraduate"))
        {
            e.fillBSlist(programlist, uniname);
        }
        else
        {
            e.fillMSlist(programlist, uniname);
        }

    }

    public void getAllAvalaiblePrograms(Context ptr, List<String> programlist)
    {
        e.connectToDb(ptr);
        if(this.educationType.equals("Undergraduate"))
        {
            e.fillAllBSlist(programlist);
        }
        else
        {
            e.fillAllMSlist(programlist);
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
        int sid = su.getStuId(this.getName());
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
        int uid = su.getUserId(this.getName());
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


    public void getUniContent(Context ptr, String universityname, ArrayList<String> depts, List<alumniInfo> arr, List<feeinfo> f, List<aidInfo> a, List<reviewInfo> r)
    {
        this.vp.connectToDb(ptr);
        this.vp.getUniveristy(universityname, depts, arr, f, a, r);
    }

/*    public void getFacultyContent(Context ptr, String universityname, String deptname, List<profinfo> arr)
    {
        this.vp.connectToDb(ptr);
        this.vp.getFacultyOfDept(universityname, deptname, arr);
    }*/

    /*public void getProgramsContent(Context ptr, String universityname, String deptname, List<String> arr)
    {
        this.vp.connectToDb(ptr);
        this.vp.getProgramsOfDept(universityname, deptname, arr);
    }*/

    public void getUnis(Context ptr, ArrayList<String> arr)
    {
        this.uc.connectToDb(ptr);
        this.uc.fillUniList(arr);
    }

    public void getDeptsUni(Context ptr, String uniname, List<String> arr)
    {
        this.uc.connectToDb(ptr);
        this.uc.getDepts(uniname, arr);
    }

    public List<String> getDeptPrgms(Context ptr, String universityname, String deptname)
    {
        this.uc.connectToDb(ptr);
        return this.uc.getProgramsOfDept(universityname, deptname);
    }

    public void getUnis(Context ptr, String universityname, ArrayList<String> depts, List<alumniInfo> arr, List<feeinfo> f, List<aidInfo> a, List<reviewInfo> r)
    {
        this.vp.connectToDb(ptr);
        this.vp.getUniveristy(universityname, depts, arr, f, a, r);
    }

    public String getType(){
        return "Student";
    }
}
