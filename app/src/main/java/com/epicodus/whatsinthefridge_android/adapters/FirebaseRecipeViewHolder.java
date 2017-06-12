package com.epicodus.whatsinthefridge_android.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.epicodus.whatsinthefridge_android.R;
import com.epicodus.whatsinthefridge_android.models.Recipe;
import com.epicodus.whatsinthefridge_android.ui.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

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
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }
}
