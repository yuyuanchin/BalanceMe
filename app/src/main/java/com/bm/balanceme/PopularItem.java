package com.bm.balanceme;

public class PopularItem {
    private int imageResourceId;
    private String title;
    private String quantity;
    private String description;

    public PopularItem(int imageResourceId, String title, String quantity, String description) {
        this.imageResourceId = imageResourceId;
        this.title = title;
        this.quantity = quantity;
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getTitle() {
        return title;
    }
    public String getQuantity() {
        return quantity;
    }
    public String getDescription() {
        return description;
    }
}
