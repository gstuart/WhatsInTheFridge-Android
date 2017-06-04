package com.epicodus.whatsinthefridge_android;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeList extends AppCompatActivity {
    @Bind(R.id.recipeListView) ListView mRecipeListView;
    @Bind(R.id.ingredientTextView) TextView mIngredientTextView;

    private String[] recipes = new String[] {"onion soup", "carrot cake", "cheddar cheese fondue", "black bean burrito", "spaghetti"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipes);
        mRecipeListView.setAdapter(adapter);

        Intent intent = getIntent();
        String ingredient1 = intent.getStringExtra("ingredient1");
        String ingredient2 = intent.getStringExtra("ingredient2");
        String ingredient3 = intent.getStringExtra("ingredient3");
        String ingredient4 = intent.getStringExtra("ingredient4");
        String ingredient5 = intent.getStringExtra("ingredient5");

        mIngredientTextView.setText("Here are all the recipes with: " + ingredients);
    }
}
