package com.epicodus.whatsinthefridge_android.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.whatsinthefridge_android.Constants;
import com.epicodus.whatsinthefridge_android.R;
import com.epicodus.whatsinthefridge_android.models.Recipe;
import com.epicodus.whatsinthefridge_android.util.OnRecipeSelectedListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class RecipeList extends AppCompatActivity implements OnRecipeSelectedListener {
    private Integer mPosition;
    ArrayList<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        if (savedInstanceState != null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mPosition = savedInstanceState.getInt(Constants.EXTRA_KEY_POSITION);
                mRecipes = Parcels.unwrap(savedInstanceState.getParcelable(Constants.EXTRA_KEY_RECIPES));

                if (mPosition != null && mRecipes != null) {
                    Intent intent = new Intent(this, RecipeDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, mPosition);
                    intent.putExtra(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(mRecipes));
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPosition != null && mRecipes != null) {
            outState.putInt(Constants.EXTRA_KEY_POSITION, mPosition);
            outState.putParcelable(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(mRecipes));
        }
    }

    @Override
    public void onRecipeSelected(Integer position, ArrayList<Recipe> recipes) {
        mPosition = position;
        mRecipes = recipes;
    }

}