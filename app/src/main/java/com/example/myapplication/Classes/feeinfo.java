package com.example.myapplication.Classes;

public class feeinfo {
    private String degree;
    private int feePerCdthr;
    private int admissionFee;
    private int creditHrs;

    public feeinfo(String deg, int fpc, int af, int ch)
    {
        this.degree = deg;
        this.feePerCdthr = fpc;
        this.admissionFee = af;
        this.creditHrs = ch;
    }

    public String getDegree() {
        return degree;
    }

    public int getAdmissionFee() {
        return admissionFee;
    }

    public int getCreditHrs() {
        return creditHrs;
    }

    public int getFeePerCdthr() {
        return feePerCdthr;
    }
}