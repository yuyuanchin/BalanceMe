package com.bm.balanceme;

public class RCGymModel {

    String title;
    int image;
    int ratingIcon;
    String ratingText;
    String address;
    boolean isFavorite;

    public RCGymModel(String title, int image, int ratingIcon, String ratingText, String address) {
        this.title = title;
        this.image = image;
        this.ratingIcon = ratingIcon;
        this.ratingText = ratingText;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
