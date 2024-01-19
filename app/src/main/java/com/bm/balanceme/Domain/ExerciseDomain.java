package com.bm.balanceme.Domain;

public class ExerciseDomain {
    private int videoName;
    private String name;
    private String description;
    private int imageUrl;

    public int getVideoName() {
        return videoName;
    }

    public void setVideoName(int videoName) {
        this.videoName = videoName;
    }

    public ExerciseDomain(int videoName, String name, String description, int imageUrl) {
        this.videoName = videoName;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
