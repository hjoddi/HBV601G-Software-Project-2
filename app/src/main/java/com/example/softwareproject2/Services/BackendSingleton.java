package com.example.softwareproject2.Services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.softwareproject2.Model.Recipe;
import com.example.softwareproject2.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Temporary class that provides data which will ultimately
 *  be provided by the NetworkManager and RestAPI.
 */
public class BackendSingleton {
    private static final BackendSingleton instance = new BackendSingleton();
    private ArrayList<User> userList;
    private String loggedIn;
    private ArrayList<Recipe> recipesList;
    private String adminUsername;
    private String adminPassword;


    // Constructor with example users already created.
    private BackendSingleton() {
        if(userList == null){
            userList = new ArrayList<>();
  /*          User nr1 = new User("Arnar", "1234");
            User nr2 = new User("Hjorvar", "1234");
            userList.add(nr1); userList.add(nr2);*/
        }
        if (recipesList == null) {
            recipesList = new ArrayList<>();
        }

            /*
            if (recipesList.isEmpty()) {
            recipesList = getPreMadeRecipeBank();
        }*/
        adminUsername = "admin";
        adminPassword = "admin";
    }


    // Who is logged in getter/setter.
    public User getLoggedIn() {
        for (User usr: userList) {
            if(usr.getUsername().equals(loggedIn)){
                return usr;
            }
        }
        return null;
    }
    public void setLoggedIn(String user, String pass) {
        User current = new User(user, pass);
        userList.add(current);
        this.loggedIn = user;
    }


    /**
     * Get instance of backendSingleton.
     * @return - Instance of TempBackendProvider.
     */
    public static BackendSingleton getInstance(){
        return instance;
    }


    /**
     * Provides a list of pre-made recipes which temporarily
     *  represent the entire database of recipes from the backend.
     * @return - recipes: List of all recipes.
     */

    public List<Recipe> getRecipes() {
        return recipesList;
    }



    /**
     * Retrieves a list of recipes from the backend.
     * @return List of recipes from the backend.
     */
    public ArrayList<Recipe> getAllRecipesFromBackend(Context context){
        String URL = "http://10.0.2.2:8080/restRecipes";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                response -> {
                    Gson gson = new Gson();
                    recipesList = gson.fromJson(response, new TypeToken<ArrayList<Recipe>>(){}.getType());

                }, error -> {
            System.out.println(error);
        });
        requestQueue.add(stringRequest);
        return recipesList;
    }

    public Recipe getRecipeFromBackend(Context context, Long id){
        String URL = "http://10.0.2.2:8080/restRecipes";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                response -> {
                    Gson gson = new Gson();
                    recipesList = gson.fromJson(response, new TypeToken<ArrayList<Recipe>>(){}.getType());

                }, error -> {
            System.out.println(error);
        });
        requestQueue.add(stringRequest);
        for (Recipe recipe: recipesList) {
            if(recipe.getId().equals(id)){
                return recipe;
            }
        }
        return null;
    }

    public void updateRecipeInTheBackend(Context context, Recipe changedRecipe){
        String URL = "http://10.0.2.2:8080/restUpdateRecipe";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Gson gson = new Gson();
        String requestBody = gson.toJson(changedRecipe);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                response -> {
                    System.out.println(response);
                }, error -> {
            System.out.println(error);
        }) {
            @Override
            public byte[] getBody(){
                return requestBody.getBytes();
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        requestQueue.add(stringRequest);
    }

    public ArrayList<User> getAllUsersFromBackendAndSingleton(Context context){
        String URL = "http://10.0.2.2:8080/restUsers";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                response -> {
                    Gson gson = new Gson();
                    ArrayList<User> remoteUserList = gson.fromJson(response, new TypeToken<ArrayList<User>>(){}.getType());
                    Set<User> userSet = new LinkedHashSet<>(userList);
                    userSet.addAll(remoteUserList);
                    System.out.println(userSet);
                    userList = new ArrayList<>(userSet);

                }, error -> {
            System.out.println(error);
        });
        requestQueue.add(stringRequest);
        return userList;
    }

    public void addUserToBackendAndSingleton(String userName, String password, Context ctx){
        User current = new User(userName, password);
        userList.add(current);

        String URL = "http://10.0.2.2:8080/restAddUser";
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        Gson gson = new Gson();
        String requestBody = gson.toJson(current);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                response -> {
                    System.out.println(response);
                }, error -> {
            System.out.println(error);
        }) {
            @Override
            public byte[] getBody(){
                return requestBody.getBytes();
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        requestQueue.add(stringRequest);
    }

    public void updateUserOnTheBackendAndSingleton(Context context, User changedUser){
        String URL = "http://10.0.2.2:8080/restChangeUser";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Gson gson = new Gson();
        String requestBody = gson.toJson(changedUser);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                response -> {
                    System.out.println(response);
                }, error -> {
            System.out.println(error);
        }) {
            @Override
            public byte[] getBody(){
                return requestBody.getBytes();
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        requestQueue.add(stringRequest);
    }

    public void deleteAllUsersOnTheBackend(Context context){
        String URL = "http://10.0.2.2:8080/restDeleteAllUsers";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                null, // response listener
                error -> {
                    System.out.println(error);
                });
        requestQueue.add(stringRequest);
    }





    public ArrayList<User> getUsers(){
        return userList;
    }

    public int logIn(String user, String pw){
        if (user.equals(adminUsername) && pw.equals(adminPassword)) {
            return 2;
        }
        if(loggedIn != null){
            return -1;
        }
        for (int i = 0; i < userList.size(); i++) {
            if((userList.get(i).getPassword().equals(pw)) && (userList.get(i).getUsername().equals(user))){
                loggedIn = userList.get(i).getUsername();
                return 1;
            }
        }
        return -1;
    }

    public int logOut(){
        System.out.println("Attempted to log out.");
        if(loggedIn != null){
            loggedIn = null;
        }
        return 1;
    }

    public Boolean isValidUsername(String userName){
        if(userName.length()==0){
            return false;
        }
        for (User usr:userList){
            if(usr.getUsername().equals(userName)){
                return false;
            }
        }
        return true;
    }


    // OLD
    public void addUser(String userName, String password){
        User current = new User(userName, password);
        userList.add(current);
    }

    public User getUser(String userName){
        for (User usr:userList){
            if(usr.getUsername().equals(userName)){
                return usr;
            }
        }
        return null;
    }
/*    public void updateUser(){
        for(User usr:userList){
            if(usr.getUsername().equals(loggedIn.getUsername())){
                userList.remove(usr);
                userList.add(loggedIn);
                break;
            }
        }
    }*/


    /**
     * Rates a recipe.
     * TODO: Have this update the database as well, once RestAPI works.
     * @param recipe Recipe to be rated.
     * @param user The user rating the recipe.
     * @param rating The rating to give the recipe.
     */
    public void rateRecipe(Recipe recipe, User user, int rating) {
        recipe.addRating(rating, user.getUsername());
    }

    /**
     * Returns the recipe corresponding to a given ID.
     * @param id The ID of the to-be-returned recipe.
     * @return The recipe corresponding to the given ID.
     */
    public Recipe getRecipeById(Long id) {
        Recipe r = null;
        for (int i = 0; i < recipesList.size(); i++) {
            if (recipesList.get(i).getId() == id) {
                r = recipesList.get(i);
                return r;
            }
        }
        return r;
    }

    /**
     * Adds a comment to a recipe.
     * TODO: Have this update the database as well, once RestAPI works.
     * @param recipe Recipe to be rated.
     * @param comment Comment to be added to recipe.
     */
    public void addComment(Recipe recipe, String comment) {
        recipe.AddComment(comment);
    }

    /**
     * Deletes all recipes.
     */
    public void deleteRecipes() {
        recipesList = new ArrayList<>();
    }

    /**
     * Deletes all users.
     */
    public void deleteUsers() {
        userList = new ArrayList<>();
    }

    /**
     * Deletes all comments on a specified recipe.
     * @param id The id of the recipe whose comments are to be deleted.
     * @return 0 if no recipe matching the given ID, 1 if comments on
     *  recipe matching given ID successfully deleted.
     */
    public int deleteCommentsOnRecipeByID(Long id, Context ctx) {
        Recipe r = getRecipeFromBackend(ctx, id);
        r.setComments(new HashSet<>());
        updateRecipeInTheBackend(ctx,r);
        return 1;
        /*
        if (r == null) {
            return 0;
        }
        else {
            r.setComments(new HashSet<>());
            return 1;
        }
        */
    }

    public int deleteUserByUsername(String username) {
        User user = getUser(username);
        if (user == null) {
            return 0;
        }
        else {
            userList.remove(user);
            return 1;
        }
    }

    public int deleteRecipeByID(Long id) {
        Recipe r = getRecipeById(id);
        if (r == null) {
            return 0;
        }
        else {
            recipesList.remove(r);
            return 1;
        }
    }

    public void saveRecipe(Recipe r) {
        recipesList.add(r);
    }
}
