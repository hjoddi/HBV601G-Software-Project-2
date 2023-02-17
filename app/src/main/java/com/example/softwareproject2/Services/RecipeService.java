package com.example.softwareproject2.Services;

import com.example.softwareproject2.Model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
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
     * Returns a list of Recipe objects that match the search parameter.
     * If the search parameter is empty, it will return all recipes in
     *  the database.
     * If the search parameter is a single word, it will look for
     *  recipes with the name of the search parameter.
     * If the search parameter is multiple words it will look for
     *  recipes where all the ingredients match a word in the search
     *  parameter.
     *
     * @param search the search parameters used for filtering
     * @return       a list of Recipe objects that match
     *                the search parameters.
     */
    public ArrayList<Recipe> filter(String search){
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        ArrayList<Recipe> allRecipes = new ArrayList<>(mRecipeBank);

        // If search string is empty
        if(search.isEmpty()){
            System.out.println("Empty search string.");
            filteredRecipes = allRecipes;
        }

        // If search string is one word
        else if(search.split("\\s+").length == 1){
            System.out.println("One word search string: " + search);

            for (Recipe r:allRecipes){
                String name = r.getName().toLowerCase();
                if(name.contains(search.toLowerCase())){
                    filteredRecipes.add(r);
                }
            }
        }

        // If the search string is multiple words
        else{
            System.out.println("Multiword search string.");
            String[] splitted = search.split("[\\s,]+");

            //Make the ingredients we have lower case
            for (int i = 0; i < splitted.length; i++) {
                splitted[i] = splitted[i].toLowerCase();
            }

            // Check for each recipe if the search parameters contain all the ingredients necessary, and only then add
            // it to the filtered list
            for(Recipe r:allRecipes){
                HashSet<String> ingredientsToMatch = r.getIngredients();
                HashSet<String> ingredientsWeGot =  new HashSet<>(Arrays.asList(splitted));
                int sizeSoFar = 0;
                for(String ingr:ingredientsToMatch){
                    ingr = ingr.toLowerCase();
                    if(sizeSoFar == ingredientsToMatch.size()){
                        filteredRecipes.add(r);
                        break;
                    }
                    if(!ingredientsWeGot.contains(ingr)){
                        sizeSoFar = 0;
                        break;
                    }else{
                        sizeSoFar = sizeSoFar + 1;
                    }
                }
                if(sizeSoFar == ingredientsToMatch.size()){
                    filteredRecipes.add(r);
                }
            }
        }
        return filteredRecipes;
    }

    /**
     * -- DEPRECIATED - USE filter() INSTEAD --
     * TODO: Remove this if it's not used.
     *
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

    public Recipe findById(Long id) {
        Recipe r = null;

        for (int i = 0; i < mRecipeBank.size(); i++) {
            if (mRecipeBank.get(i).getId().equals(id)) {
                r = mRecipeBank.get(i);
            }
        }

        return r;
    }
}
