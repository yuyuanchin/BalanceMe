package com.bm.balanceme;

public class RecordModel {
    private int imageResourceId;
    private String title;
    private String description; // Add this line
    private int progress;

    public RecordModel(int imageResourceId, String title, String description, int progress) {
        this.imageResourceId = imageResourceId;
        this.title = title;
        this.description = description;
        this.progress = progress;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { // Add this method
        return description;
    }

    public int getProgress() {
        return progress;
    }
}
