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

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredient1 = mIngredient1.getText().toString();


                Intent intent = new Intent(Fridge.this, RecipeList.class);
                intent.putExtra("ingredient1", ingredient1);
                startActivity(intent);
            }
        });

    }
}
