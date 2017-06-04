package com.epicodus.whatsinthefridge_android;

import okhttp3.Callback;
import okhttp3.HttpUrl;

public class RecipeService {
    public static void findRecipes(String ingredients, Callback callback) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_INGREDENT_QUERY_PARAMETER, ingredients);
        String url = urlBuilder.build().toString();

    }
}
