package com.example.softwareproject2.Model;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Recipe object. Contains information pertaining to a particular recipe.
 */
public class Recipe implements Serializable {

	// Instance variables.
	private Long id;
	private HashSet<String> ingredients;
	//@Column(columnDefinition = "TEXT", length = 1000)
	private String instructions;
	private String imageName;
	private String name;
	private HashSet<String> comments;
	private User user;
	private HashSet<Integer> ratings;	// Contains individual ratings.
	private HashSet<String> raters; 	// Contains usernames of those who have rated.
	private int rating;	// Recipe's rating. Average of ratings.


	/**
	 * Adds a rating to recipe.
	 * @param rating - The rating to add to the recipe.
	 * @param username - The user adding the rating.
	 */
	public void addRating(int rating, String username) {
		System.out.println("DEBUG - addRating()");
		if (!this.raters.contains(username)) {
			System.out.println("DEBUG - !this.raters.contains(username)");
			this.ratings.add(rating);
			this.raters.add(username);
			updateRatings();
		}
	}

	/**
	 * Updates the recipe's rating by averaging the list of ratings.
	 */
	public void updateRatings() {

		int ratingSum = 0;
		for (int i : ratings) {
			ratingSum += i;
		}

		double ratingAvg = (double) ratingSum / ratings.size();
		if (ratingAvg > 4.5) {
			this.rating = 5;
		}
		else if (ratingAvg > 3.5) {
			this.rating = 4;
		}
		else if (ratingAvg > 2.5) {
			this.rating = 3;
		}
		else if (ratingAvg > 1.5) {
			this.rating = 2;
		}
		else {
			this.rating = 1;
		}
	}



	/**
	 * Constructor for Recipe object.
	 * @param name - Name of the recipe.
	 * @param ingredients - Ingredients in the recipe.
	 * @param instructions - Instructions on how to create the recipe.
	 */
	public Recipe(String name, HashSet<String> ingredients, String instructions, String imageName) {
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.name = name;
		this.rating = 5;
		this.raters = new HashSet<>();
		this.ratings = new HashSet<>();
		this.comments = new HashSet<>();
		this.imageName = imageName;
	}
	/**
	 * Generic constructor without parameters.
	 */
	public Recipe() {}

	/**
	 * Adds a comment to the recipe.
	 * @param comment - The comment in String-form.
	 */
	public void AddComment(String comment) {
		this.comments.add(comment);
	}

	/**
	 * Describes the recipe in String format.
	 * @return - String containing the recipe's variables.
	 */
	@Override
	public String toString() {
		return "Recipe{" +
				"id=" + id +
				", ingredients=" + ingredients +
				", instructions='" + instructions +
				", comments='" + comments + '\'' +
				'}';
	}

	/**
	 * Getters and setters.
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public HashSet<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(HashSet<String> ingredients) {
		this.ingredients = ingredients;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HashSet<String> getComments() {
		return comments;
	}
	public void setComments(HashSet<String> comments) {
		this.comments = comments;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public HashSet<Integer> getRatings() {
		return ratings;
	}
	public void setRatings(HashSet<Integer> ratings) {
		this.ratings = ratings;
	}
	public HashSet<String> getRaters() {
		return raters;
	}
	public void setRaters(HashSet<String> raters) {
		this.raters = raters;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
