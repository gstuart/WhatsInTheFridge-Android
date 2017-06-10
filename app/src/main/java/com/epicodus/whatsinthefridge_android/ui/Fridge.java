package com.epicodus.whatsinthefridge_android.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.whatsinthefridge_android.Constants;
import com.epicodus.whatsinthefridge_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Fridge extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.instructionView) TextView mInstructionView;
    @Bind(R.id.ingredient) EditText mIngredient;
    @Bind(R.id.searchButton) Button mSearchRecipesButton;
    @Bind(R.id.savedButton) Button mSavedRecipesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        ButterKnife.bind(this);

        Typeface text = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
            mInstructionView.setTypeface(text);
            mIngredient.setTypeface(text);
            mSearchRecipesButton.setTypeface(text);
            mSavedRecipesButton.setTypeface(text);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSearchRecipesButton.setOnClickListener(this);
        mSavedRecipesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchRecipesButton) {
            String ingredient = mIngredient.getText().toString();
            if(!(ingredient).equals("")) {
                addToSharedPreferences(ingredient);
            }
            Intent intent = new Intent(Fridge.this, RecipeList.class);
            intent.putExtra("ingredient", ingredient);
            startActivity(intent);
        }
        if (v == mSavedRecipesButton) {
            Intent intent = new Intent(Fridge.this, SavedRecipesListActivity.class);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String ingredient) {
        mEditor.putString(Constants.PREFERENCES_INGREDIENT_KEY, ingredient).apply();
    }
}
