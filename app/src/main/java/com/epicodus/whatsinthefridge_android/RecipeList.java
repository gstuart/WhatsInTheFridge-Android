package com.epicodus.whatsinthefridge_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class RecipeList extends AppCompatActivity {
    public static final String TAG = RecipeList.class.getSimpleName();

    @Bind(R.id.recipeListView) ListView mRecipeListView;
    @Bind(R.id.ingredientTextView) TextView mIngredientTextView;

    public ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String ingredient1 = intent.getStringExtra("ingredient1");
        String ingredient2 = intent.getStringExtra("ingredient2");
        String ingredient3 = intent.getStringExtra("ingredient3");
        String ingredient4 = intent.getStringExtra("ingredient4");
        String ingredient5 = intent.getStringExtra("ingredient5");

        getRecipes(ingredient1);
    }

    private void getRecipes(String ingredient1) {
        final RecipeService recipeService = new RecipeService();
        recipeService.findRecipes(ingredient1, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();

                    if (response.isSuccessful()) {
                        mRecipes = recipeService.processResults(response);

                        RecipeList.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                String[] recipeTitles = new String[mRecipes.size()];
                                for (int i = 0; i < recipeTitles.length; i++) {
                                    recipeTitles[i] = mRecipes.get(i).getTitle();
                                }
                                ArrayAdapter adapter = new ArrayAdapter(RecipeList.this,
                                        android.R.layout.simple_list_item_1, recipeTitles);
                                mRecipeListView.setAdapter(adapter);

                                for (Recipe recipe : mRecipes) {
                                    Log.d(TAG, "Recipe Title: " + recipe.getTitle());
                                    Log.d(TAG, "Recipe link: " + recipe.getLink());
                                    Log.d(TAG, "Recipe image URL: " + recipe.getImageUrl());
                                    Log.d(TAG, "Recipe ingredients: " + recipe.getIngredients());
                                }
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}