package com.epicodus.whatsinthefridge_android;


import java.util.ArrayList;

public class Recipe {
    private String mTitle;
    private String mLink;
    private String mImageUrl;
    private ArrayList<String> mIngredients = new ArrayList<>();

    public Recipe(String title, String link, String imageUrl, ArrayList<String> ingredients) {
        this.mTitle = title;
        this.mLink = link;
        this.mImageUrl = imageUrl;
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

    public ArrayList<String> getIngredients() {
        return mIngredients;
    }
}
