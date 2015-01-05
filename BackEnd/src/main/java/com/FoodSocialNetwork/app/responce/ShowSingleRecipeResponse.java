package com.FoodSocialNetwork.app.responce;

import com.FoodSocialNetwork.app.database.Comment;
import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Recipe;

public class ShowSingleRecipeResponse extends DefaultResponse{

	private Ingredient[] ingridients;
	private Comment[] comments;
	private Recipe recipe;
	private String username;
	private String[] tools;
	
	public Ingredient[] getIngridients() {
		return ingridients;
	}

	public void setIngridients(Ingredient[] ingridients) {
		this.ingridients = ingridients;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Comment[] getComments() {
		return comments;
	}

	public void setComments(Comment[] comments) {
		this.comments = comments;
	}

	public String[] getTools() {
		return tools;
	}

	public void setTools(String[] tools) {
		this.tools = tools;
	}	
}
