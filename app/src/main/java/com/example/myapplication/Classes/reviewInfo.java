package com.example.myapplication.Classes;

public class reviewInfo {
    private String review;
    private int rating;

    public reviewInfo(String text, int rating)
    {
        this.review = text;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}
