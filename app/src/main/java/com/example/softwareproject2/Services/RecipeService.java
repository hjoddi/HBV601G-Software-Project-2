package com.example.softwareproject2.Services;

import com.example.softwareproject2.Model.Recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Service that works on the recipe data, for instance
 *  by filtering.
 */
public class RecipeService {

    // Instance variables.
    private List<Recipe> mRecipeBank;

    /**
     * Constructor.
     */
    public RecipeService() {
        TempBackendProvider TBP = TempBackendProvider.INSTANCE;
        this.mRecipeBank = TBP.getRecipes();
    }

    public List<Recipe> allRecipes() {
        return mRecipeBank;
    }

    /**
     * Returns a list of recipes whose name or ingredients match the filter string.
     * @param filter - The string which should match a recipe's name or ingredients.
     * @return - List of recipes whose name or ingredients match the filter string.
     */
    public List<Recipe> filteredRecipeList(String filter) {
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();

        for (int i = 0; i < mRecipeBank.size(); i++) {
            Recipe currentRecipe = mRecipeBank.get(i);
            HashSet<String> currentRecipeIngredients = currentRecipe.getIngredients();
            Iterator<String> currentRecipeIngredientsIterator = currentRecipeIngredients.iterator();

            if (currentRecipe.getName().contains(filter)) {
                filteredRecipes.add(currentRecipe);
            }
            else {
                while (currentRecipeIngredientsIterator.hasNext()) {
                    String ingredient = currentRecipeIngredientsIterator.next();

                    if (ingredient.contains(filter)) {
                        filteredRecipes.add(currentRecipe);
                    }
                }
            }
        }

        return filteredRecipes;
    }
}
