package com.example.softwareproject2.Services;

import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Temporary class that provides data which will ultimately
 *  be provided by the NetworkManager and RestAPI.
 *  !DEPRECATED!
 */
public enum TempBackendProvider {

    // Instance variables.
    INSTANCE;

    private ArrayList<User> userList;
    private User loggedIn;

    /**
     * Get instance of TempBackendProvider.
     * @return - Instance of TempBackendProvider.
     */
    public TempBackendProvider getInstance(){
        if(userList.isEmpty()){
            User nr1 = new User("Arnar", "1234");
            User nr2 = new User("Hjorvar", "1234");
            userList.add(nr1); userList.add(nr2);
        }
        return INSTANCE;
    }

    /**
     * Provides a list of pre-made recipes which temporarily
     *  represent the entire database of recipes from the backend.
     * @return - recipes: List of all recipes.
     */
    public List<Recipe> getRecipes() {

        // Populate list.
        HashSet<String> ingredients = new HashSet<>(); ingredients.add("Rice"); ingredients.add("Milk"); ingredients.add("Raisins");
        Recipe r1 = new Recipe("Porridge", ingredients, "Add small amount of water and bring it to a boil. " +
                "Then add the rice and milk and let it cook at low heat. Add raisings once it starts getting thicker.", "porridge");
        r1.setId(1L);
        HashSet<String> ingredients2 = new HashSet<>(); ingredients2.add("Minced-Meat"); ingredients2.add("Spaghetti"); ingredients2.add("Pasta-Sauce"); ingredients2.add("Vegetable");
        Recipe r2 = new Recipe("Spaghetti", ingredients2, "Boil spaghetti. Cook the meat until brown and add sauce. Add vegetables if desired." +
                " Combine.","spaghetti");
        r2.setId(2L);
        HashSet<String> ingredients3 = new HashSet<>(); ingredients3.add("Tuna"); ingredients3.add("Pasta"); ingredients3.add("Egg"); ingredients3.add("Vegetable");
        Recipe r3 = new Recipe("Tuna-Egg-Pasta", ingredients3, "Boil eggs and pasta for 10 minutes. Combine in a bowl and add tuna and vegetables.", "tuna_egg_pasta");
        r3.setId(3L);
        HashSet<String> ingredients4 = new HashSet<>(); ingredients4.add("Noodles"); ingredients4.add("Cheese"); ingredients4.add("Egg");
        Recipe r4 = new Recipe("Slightly better noodles", ingredients4, "Fry an egg. Put noodles and sauce in a pan. " +
                "Sprinkle cheese on top and let it melt. Put egg on top.", "slightly_better_noodles");
        r4.setId(4L);
        HashSet<String> ingredients5 = new HashSet<>(Arrays.asList("Leftover", "Egg", "Vegetable"));
        Recipe r5 = new Recipe("Leftover omelette", ingredients5,
                "Mix eggs in a bowl. Fry in a pan on low heat. When bottom of egg starts to cook add any vegetables and leftovers. Fold the base into a half moon shape.",
                "leftoveromelette");
        r5.setId(5L);
        HashSet<String> ingredients6 = new HashSet<>(Arrays.asList("Chicken-Breast", "Peanuts", "Noodles", "Vegetable", "Bean-Sauce"));
        Recipe r6 = new Recipe("Chicken Noodles stir-fry", ingredients6, "Grind up the peanuts. " +
                "Cook chicken in a pan with bean sauce. " +
                "Boil the noodles and add vegetables to the pot. " +
                "Mix everything with peanuts on top.",
                "chicken_noodles_stir_fry");
        r6.setId(6L);
        HashSet<String> ingredients7= new HashSet<>(Arrays.asList("Spaghetti", "Cheese", "Garlic", "Olive-Oil"));
        Recipe r7 = new Recipe("Spaghetti aglio e olio", ingredients7, "Cook pasta. Cook garlic in pan with olive oil until golden. Mix pasta with garlic in pan." +
                " Add any spices or herbs.",
                "aglio_e_olio");
        r7.setId(7L);
        HashSet<String> ingredients8= new HashSet<>(Arrays.asList("Pasta", "Garlic", "Mushroom", "Cheese", "Creme-Fraiche"));
        Recipe r8 = new Recipe("Mushroom Garlic Pasta", ingredients8, "Cook pasta. Fry garlic and mushrooms. Put pasta into pan and mix in cheese and" +
                " creme fraiche.",
                "mushroom_pasta");
        r8.setId(8L);

        // Return list of recipes.
        Recipe[] recipesArray = {r1, r2, r3, r4, r5, r6, r7, r8,r8,r8,r8,r8,r8};
        List<Recipe> recipes = Arrays.asList(recipesArray);
        return recipes;
    }




    public ArrayList<User> getUsers(){
        return userList;
    }
    public int logIn(String user, String pw){
        if(loggedIn != null){
            return -1;
        }
        for (int i = 0; i < userList.size(); i++) {
            if((userList.get(i).getPassword() == pw) && (userList.get(i).getUsername() == user)){
                loggedIn = userList.get(i);
                return 1;
            }
        }
        return -1;
    }

    public int logOut(){
        if(loggedIn != null){
            loggedIn = null;
        }
        return 1;
    }

}
