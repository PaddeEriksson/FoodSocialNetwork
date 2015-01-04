package com.FoodSocialNetwork.app.responce;

public class ShowRecipeListResponse {
	
	private long recipeID;
	private String recipeTitle;
	private String creator;
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public long getRecipeID() {
		return recipeID;
	}
	public void setRecipeID(long recipeID) {
		this.recipeID = recipeID;
	}


}
