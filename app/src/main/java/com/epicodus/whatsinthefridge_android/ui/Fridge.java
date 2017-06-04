package com.epicodus.whatsinthefridge_android.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.whatsinthefridge_android.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Fridge extends AppCompatActivity {
    @Bind(R.id.instructionView) TextView mInstructionView;

    @Bind(R.id.ingredient1) EditText mIngredient1;
    @Bind(R.id.ingredient2) EditText mIngredient2;
    @Bind(R.id.ingredient3) EditText mIngredient3;
    @Bind(R.id.ingredient4) EditText mIngredient4;
    @Bind(R.id.ingredient5) EditText mIngredient5;

    @Bind(R.id.searchButton) Button mSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        ButterKnife.bind(this);

        Typeface text = Typeface.createFromAsset(getAssets(), "fonts/caviar_dreams.ttf");
        mInstructionView.setTypeface(text);
        mIngredient1.setTypeface(text);
        mIngredient2.setTypeface(text);
        mIngredient3.setTypeface(text);
        mIngredient4.setTypeface(text);
        mIngredient5.setTypeface(text);
        mSearchButton.setTypeface(text);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredient1 = mIngredient1.getText().toString();
                String ingredient2 = mIngredient2.getText().toString();
                String ingredient3 = mIngredient3.getText().toString();
                String ingredient4 = mIngredient4.getText().toString();
                String ingredient5 = mIngredient5.getText().toString();

                Intent intent = new Intent(Fridge.this, RecipeList.class);
                intent.putExtra("ingredient1", ingredient1);
                intent.putExtra("ingredient2", ingredient2);
                intent.putExtra("ingredient3", ingredient3);
                intent.putExtra("ingredient4", ingredient4);
                intent.putExtra("ingredient5", ingredient5);
                startActivity(intent);
            }
        });

    }
}
