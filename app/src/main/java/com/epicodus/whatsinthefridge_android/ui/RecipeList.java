package com.epicodus.whatsinthefridge_android.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentIngredients;
    public static final String TAG = RecipeList.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private RecipeListAdapter mAdapter;
    public ArrayList<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String ingredient = intent.getStringExtra("ingredient");

        getRecipes(ingredient);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentIngredients = mSharedPreferences.getString(Constants.PREFERENCES_INGREDIENT_KEY, null);

        if (mRecentIngredients != null) {
            getRecipes(mRecentIngredients);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getRecipes(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void getRecipes(String ingredient) {
        final RecipeService recipeService = new RecipeService();

        recipeService.findRecipes(ingredient, new Callback() {

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

    private void addToSharedPreferences(String ingredient) {
        mEditor.putString(Constants.PREFERENCES_INGREDIENT_KEY, ingredient).apply();
    }
}