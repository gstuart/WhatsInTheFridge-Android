package com.epicodus.whatsinthefridge_android.util;

import com.epicodus.whatsinthefridge_android.models.Recipe;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface OnRecipeSelectedListener {
    public void OnRecipeSelected(Integer position, ArrayList<Recipe> recipes);

}
