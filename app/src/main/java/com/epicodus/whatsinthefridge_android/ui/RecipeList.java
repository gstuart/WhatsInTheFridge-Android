package com.epicodus.whatsinthefridge_android.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.whatsinthefridge_android.Constants;
import com.epicodus.whatsinthefridge_android.R;
import com.epicodus.whatsinthefridge_android.adapters.RecipeListAdapter;
import com.epicodus.whatsinthefridge_android.models.Recipe;
import com.epicodus.whatsinthefridge_android.services.RecipeService;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class RecipeList extends AppCompatActivity {
    private SharedPreferences mSharedPreferencs;
    private String mRecentIngredients;

    public static final String TAG = RecipeList.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;

    public ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String ingredient1 = intent.getStringExtra("ingredient1");

        getRecipes(ingredient1);

        mSharedPreferencs = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentIngredients = mSharedPreferencs.getString(Constants.PREFERENCES_INGREDIENT_KEY, null);

        if (mRecentIngredients != null) {
            getRecipes(mRecentIngredients);
        }
    }

    private void getRecipes(String ingredient1) {
        final RecipeService recipeService = new RecipeService();

        recipeService.findRecipes(ingredient1, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {

                mRecipes = recipeService.processResults(response);

                RecipeList.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new RecipeListAdapter(getApplicationContext(), mRecipes);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(RecipeList.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}