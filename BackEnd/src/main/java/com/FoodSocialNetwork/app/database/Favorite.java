package com.FoodSocialNetwork.app.database;

public class Favorite {

	
	private long recipeID;
	private String recipeTitle;
	private String user;
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public long getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(long recipeID) {
		this.recipeID = recipeID;
	}
}
