package com.example.softwareproject2.Model;


import java.util.HashSet;

/**
 * User object. Contains information pertaining to a particular user.
 */
public class User {
    /**
     * Variables.
     */
    private Long ID;

    private String username;
    private String password;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)

    //TODO: Changing from HashSet<Long> to HashSet<String> for temporary backend. May need to change back when backend goes live.
    private HashSet<Long> favoriteRecipes;

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
    public void setID(Long ID) {
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
    public HashSet<Long> getFavoriteRecipes() {
        if(favoriteRecipes == null){
            favoriteRecipes = new HashSet<>();
        }
        return favoriteRecipes;
    }
    public void setFavoriteRecipes(HashSet<Long> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    /**
     * Adds a recipe to a user's list of favourite recipes.
     * @param id - Id of recipe to be added to the user's list of favourite recipes.
     */
    public void addToFavoriteRecipes(Long id) {
        if(favoriteRecipes == null){
            favoriteRecipes = new HashSet<>();
        }
        favoriteRecipes.add(id);
    }

    /**
     * Removes a recipe from a user's list of favourite recipes.
     * @param id - name of recipe to be added to the user's list of favourite recipes.
     */
    public void removeFromFavouriteRecipes(Long id) {
        favoriteRecipes.remove(id);
    }

}