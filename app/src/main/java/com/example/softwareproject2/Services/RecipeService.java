package com.example.softwareproject2.Services;

import com.example.softwareproject2.Model.Recipe;

import java.util.List;

/**
 * Service that works on the recipe data, for instance
 *  by filtering.
 */
public class RecipeService {

    // Instance variables.
    private List<Recipe> mRecipeBank;

    public RecipeService() {
        TempBackendProvider TBP = TempBackendProvider.INSTANCE;
        this.mRecipeBank = TBP.getRecipes();
    }
}
