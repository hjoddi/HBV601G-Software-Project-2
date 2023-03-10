package com.example.softwareproject2.Model;


import java.util.HashSet;

/**
 * User object. Contains information pertaining to a particular user.
 */
public class User {
    /**
     * Variables.
     */
    private long ID;

    private String username;
    private String password;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)

    //TODO: Changing from HashSet<Long> to HashSet<String> for temporary backend. May need to change back when backend goes live.
    private HashSet<String> favoriteRecipes;

    /**
     * Constructor without arguments.
     */
    public User(){}

    /**
     * Constructor with arguments.
     * @param username - Name of the user.
     * @param password - Password of the user.
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
        favoriteRecipes = new HashSet<>();
    }

    /**
     * Getters and setters.
     */
    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public HashSet<String> getFavoriteRecipes() {
        if(favoriteRecipes == null){
            favoriteRecipes = new HashSet<>();
        }
        return favoriteRecipes;
    }
    public void setFavoriteRecipes(HashSet<String> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    /**
     * Adds a recipe to a user's list of favourite recipes.
     * @param name - Id of recipe to be added to the user's list of favourite recipes.
     */
    public void addToFavoriteRecipes(String name) {
        if(favoriteRecipes == null){
            favoriteRecipes = new HashSet<>();
        }
        favoriteRecipes.add(name);
    }

    /**
     * Removes a recipe from a user's list of favourite recipes.
     * @param name - name of recipe to be added to the user's list of favourite recipes.
     */
    public void removeFromFavouriteRecipes(String name) {
        favoriteRecipes.remove(name);
    }

}