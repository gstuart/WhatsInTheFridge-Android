package com.epicodus.whatsinthefridge_android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.epicodus.whatsinthefridge_android.R;
import com.epicodus.whatsinthefridge_android.models.Recipe;
import com.epicodus.whatsinthefridge_android.util.OnRecipeSelectedListener;

import java.util.ArrayList;

public class RecipeList extends AppCompatActivity implements OnRecipeSelectedListener {
    private Integer mPosition;
    ArrayList<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

    }

    @Override
    public void onRecipeSelected(Integer position, ArrayList<Recipe> recipes) {
        mPosition = position;
        mRecipes = recipes;
    }

}