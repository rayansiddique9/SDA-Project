package com.example.myapplication.Classes;


import java.util.ArrayList;

public class UndergraduteProgram extends Program{

   /* enum BG
    {
        preEng,
        preMed,
        ICS,
        FineArts

    }*/


    private int minMarks;
    private ArrayList<String> prereqs;

    UndergraduteProgram(int m, ArrayList<String> pr, String name)
    {
        this.name = name;
        this.type = 0;
        this.minMarks = m;
        this.prereqs = pr;
    }




    // cs -> preng,premed,ics
    //se -> preeng,premed
    //bba ->premed,preeng,ics,business,finearts


}
