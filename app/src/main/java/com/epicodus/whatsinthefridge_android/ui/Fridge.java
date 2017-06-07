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

import static com.epicodus.whatsinthefridge_android.R.id.ingredient1;

public class Fridge extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.instructionView) TextView mInstructionView;
    @Bind(ingredient1) EditText mIngredient1;
    @Bind(R.id.searchButton) Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        ButterKnife.bind(this);

        Typeface text = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        mInstructionView.setTypeface(text);
        mIngredient1.setTypeface(text);
        mSearchButton.setTypeface(text);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mSearchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mSearchButton) {
            String ingredient1 = mIngredient1.getText().toString();
            if(!(ingredient1).equals("")) {
                addToSharedPreferences(ingredient1);
            }
            Intent intent = new Intent(Fridge.this, RecipeList.class);
            startActivity(intent);
        }
    }
    private void addToSharedPreferences(String ingredient1) {
        mEditor.putString(Constants.PREFERENCES_INGREDIENT_KEY, ingredient1).apply();
    }
}
