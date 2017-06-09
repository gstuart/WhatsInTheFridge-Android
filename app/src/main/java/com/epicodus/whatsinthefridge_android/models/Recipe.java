package com.epicodus.whatsinthefridge_android.models;

import org.parceler.Parcel;

@Parcel
public class Recipe {
    String title;
    String link;
    String imageUrl;
    String ingredients;
    private String pushId;

    public Recipe() {

    }

    public Recipe(String title, String link, String imageUrl, String ingredients) {
        this.title = title;
        this.link = link;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

    public String getTitle(){
        return title;
    }

    public String getLink(){
        return link;
    }

    public String getImageUrl() {
       return imageUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getPushId() {
        return pushId;
    }

    public String setPushId() {
        this.pushId = pushId;
    }
}
