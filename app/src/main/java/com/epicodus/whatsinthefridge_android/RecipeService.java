package com.epicodus.whatsinthefridge_android;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeService {
    public static void findRecipes(String ingredient1, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.API_INGREDENT_QUERY_PARAMETER, ingredient1);
        String url = urlBuilder.build().toString();
        Log.v("RecipeService", url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Recipe> processResults(Response response) {
        ArrayList<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject yelpJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = yelpJSON.getJSONArray("results");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject recipeJSON = resultsJSON.getJSONObject(i);
                    String title = recipeJSON.getString("title");
                    String link = recipeJSON.getString("link");
                    String imageUrl = recipeJSON.getString("imageUrl");
                    ArrayList<String> ingredient = new ArrayList<>();
                    JSONArray ingredientJSON = recipeJSON.getJSONObject("ingredient")
                            .getJSONArray("ingredients");
                    for (int y = 0; y < ingredientJSON.length(); y++) {
                        ingredient.add(ingredientJSON.get(y).toString());
                    }

                    Recipe recipe = new Recipe(title, link, imageUrl, ingredient);
                    recipes.add(recipe);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
