package com.bm.balanceme;

public class PastChallengeItem {
    private String title;
    private String description;
    private String date;
    private int imageResourceId; // You can change this to String for an image URL if needed

    public PastChallengeItem(String date, int imageResourceId, String title, String description) {
        this.date = date;
        this.imageResourceId = imageResourceId;
        this.title = title;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
