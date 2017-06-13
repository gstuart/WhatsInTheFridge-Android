package com.epicodus.whatsinthefridge_android.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.whatsinthefridge_android.Constants;
import com.epicodus.whatsinthefridge_android.R;
import com.epicodus.whatsinthefridge_android.models.Recipe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.recipeImageView) ImageView mRecipeImageView;
    @Bind(R.id.recipeTitleTextView) TextView mRecipeTitleTextView;
    @Bind(R.id.ingredientListTextView) TextView mIngredientListTextView;
    @Bind(R.id.websiteTextView) TextView mWebsiteTextView;
    @Bind(R.id.saveRecipeButton) Button mSaveRecipeButton;

    private Recipe mRecipe;
    private ArrayList<Recipe> mRecipes;
    private int mPosition;

    public static RecipeDetailFragment newInstance(ArrayList<Recipe> recipes, Integer position) {
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_RECIPES, Parcels.wrap(recipes));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        recipeDetailFragment.setArguments(args);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipes = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_RECIPES));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mRecipe = mRecipes.get(mPosition);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mRecipe.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mRecipeImageView);

        mRecipeTitleTextView.setText(mRecipe.getTitle());
        mWebsiteTextView.setText(mRecipe.getLink());
        mIngredientListTextView.setText(mRecipe.getIngredients());

        mRecipeTitleTextView.setOnClickListener(this);
        mWebsiteTextView.setOnClickListener(this);
        mIngredientListTextView.setOnClickListener(this);
        mSaveRecipeButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v)     {
        if (v == mWebsiteTextView) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRecipe.getLink()));
            startActivity(webIntent);
        }
        if (v == mSaveRecipeButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference recipeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RECIPES)
                    .child(uid);

            DatabaseReference pushRef = recipeRef.push();
            String pushId = pushRef.getKey();
            mRecipe.setPushId(pushId);
            pushRef.setValue(mRecipe);

            Toast.makeText(getContext(), "Recipe Saved", Toast.LENGTH_LONG).show();
        }
    }

}
