package com.example.softwareproject2.Services;

import com.example.softwareproject2.Model.Recipe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Temporary class that provides data which will ultimately
 *  be provided by the NetworkManager and RestAPI.
 */
public enum TempBackendProvider {

    // Instance variables.
    INSTANCE;

    /**
     * Get instance of TempBackendProvider.
     * @return - Instance of TempBackendProvider.
     */
    public TempBackendProvider getInstance(){
        return INSTANCE;
    }

    /**
     * Provides a list of pre-made recipes which temporarily
     *  represent the entire database of recipes from the backend.
     * @return - recipes: List of all recipes.
     */
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = null;

        // Populate list.
        HashSet<String> ingredients = new HashSet<>(); ingredients.add("Rice"); ingredients.add("Milk"); ingredients.add("Raisins");
        recipes.add(new Recipe("Porridge", ingredients, "Add small amount of water and bring it to a boil. " +
                "Then add the rice and milk and let it cook at low heat. Add raisings once it starts getting thicker.", "Porridge"));
        HashSet<String> ingredients2 = new HashSet<>(); ingredients2.add("Minced-Meat"); ingredients2.add("Spaghetti"); ingredients2.add("Pasta-Sauce"); ingredients2.add("Vegetable");
        recipes.add(new Recipe("Spaghetti", ingredients2, "Boil spaghetti. Cook the meat until brown and add sauce. Add vegetables if desired." +
                " Combine.","Spaghetti"));
        HashSet<String> ingredients3 = new HashSet<>(); ingredients3.add("Tuna"); ingredients3.add("Pasta"); ingredients3.add("Egg"); ingredients3.add("Vegetable");
        recipes.add(new Recipe("Tuna-Egg-Pasta", ingredients3, "Boil eggs and pasta for 10 minutes. Combine in a bowl and add tuna and vegetables.", "Tuna-Egg-Pasta"));

        HashSet<String> ingredients4 = new HashSet<>(); ingredients4.add("Noodles"); ingredients4.add("Cheese"); ingredients4.add("Egg");
        recipes.add(new Recipe("Slightly better noodles", ingredients4, "Fry an egg. Put noodles and sauce in a pan. " +
                "Sprinkle cheese on top and let it melt. Put egg on top.", "Slightly-better-noodles"));
        HashSet<String> ingredients5 = new HashSet<>(Arrays.asList("Leftover", "Egg", "Vegetable"));
        recipes.add(new Recipe("Leftover omelette", ingredients5,
                "Mix eggs in a bowl. Fry in a pan on low heat. When bottom of egg starts to cook add any vegetables and leftovers. Fold the base into a half moon shape.",
                "LeftoverOmelette"));
        HashSet<String> ingredients6 = new HashSet<>(Arrays.asList("Chicken-Breast", "Peanuts", "Noodles", "Vegetable", "Bean-Sauce"));
        recipes.add(new Recipe("Chicken Noodles stir-fry", ingredients6, "Grind up the peanuts. " +
                "Cook chicken in a pan with bean sauce. " +
                "Boil the noodles and add vegetables to the pot. " +
                "Mix everything with peanuts on top.",
                "Chicken-Noodles-stir-fry"));
        HashSet<String> ingredients7= new HashSet<>(Arrays.asList("Spaghetti", "Cheese", "Garlic", "Olive-Oil"));
        recipes.add(new Recipe("Spaghetti aglio e olio", ingredients7, "Cook pasta. Cook garlic in pan with olive oil until golden. Mix pasta with garlic in pan." +
                " Add any spices or herbs.",
                "aglio-e-olio"));
        HashSet<String> ingredients8= new HashSet<>(Arrays.asList("Pasta", "Garlic", "Mushroom", "Cheese", "Creme-Fraiche"));
        recipes.add(new Recipe("Mushroom Garlic Pasta", ingredients8, "Cook pasta. Fry garlic and mushrooms. Put pasta into pan and mix in cheese and" +
                " creme fraiche.",
                "mushroom-pasta"));

        // Return list of recipes.
        return recipes;
    }
}
