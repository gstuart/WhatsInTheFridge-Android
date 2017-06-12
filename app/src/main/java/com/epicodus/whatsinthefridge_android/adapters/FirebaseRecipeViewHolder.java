package com.epicodus.whatsinthefridge_android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.whatsinthefridge_android.Constants;
import com.epicodus.whatsinthefridge_android.R;
import com.epicodus.whatsinthefridge_android.models.Recipe;
import com.epicodus.whatsinthefridge_android.ui.ItemTouchHelperViewHolder;
import com.epicodus.whatsinthefridge_android.ui.RecipeDetailActivity;
import com.epicodus.whatsinthefridge_android.util.ItemTouchHelperAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseRecipeViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public ImageView dragIcon;

    View mView;
    Context mContext;

    public FirebaseRecipeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindRecipe(Recipe recipe) {
        dragIcon = (ImageView) mView.findViewById(R.id.dragIcon);
        ImageView recipeImageView = (ImageView) mView.findViewById(R.id.recipeImageView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.recipeTitleTextView);

        Picasso.with(mContext)
                .load(recipe.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(recipeImageView);
        titleTextView.setText(recipe.getTitle());
    }

    @Override
    public void onItemSelected() {
        Log.d("Animation", "onItemSelected");
    }

    @Override
    public void onItemClear() {
        Log.d("Animation", "onItemClear");

    }
}
