package com.example.kleanlife;

public class Review {
    String reviewGiver;
    String review;
    String reviewReceiver;
    String date;

    public Review(String reviewGiver, String review, String reviewReceiver, String date) {
        this.reviewGiver = reviewGiver;
        this.review = review;
        this.reviewReceiver = reviewReceiver;
        this.date = date;
    }

    public String getReviewGiver() {
        return reviewGiver;
    }

    public void setReviewGiver(String reviewGiver) {
        this.reviewGiver = reviewGiver;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewReceiver() {
        return reviewReceiver;
    }

    public void setReviewReceiver(String reviewReceiver) {
        this.reviewReceiver = reviewReceiver;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
