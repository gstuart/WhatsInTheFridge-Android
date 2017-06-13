package com.epicodus.whatsinthefridge_android.util;

import com.epicodus.whatsinthefridge_android.models.Recipe;
import java.util.ArrayList;

public interface OnRecipeSelectedListener {
    public void onRecipeSelected(Integer position, ArrayList<Recipe> recipes);

}
