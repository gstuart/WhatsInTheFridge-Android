package com.epicodus.whatsinthefridge_android.models;

import org.parceler.Parcel;

@Parcel
public class Recipe {
    String mTitle;
    String mLink;
    String mImageUrl;
    String mIngredients;

    public Recipe() {

    }

    public Recipe(String title, String link, String imageUrl, String ingredients) {
        this.mTitle = title;
        this.mLink = link;
        this.mImageUrl = getImageUrl();
        this.mIngredients = ingredients;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getLink(){
        return mLink;
    }

    public String getImageUrl() {
       return mImageUrl;
    }

    public String getIngredients() {
        return mIngredients;
    }
}
