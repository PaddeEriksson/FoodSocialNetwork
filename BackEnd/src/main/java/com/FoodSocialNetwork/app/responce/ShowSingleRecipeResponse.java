package com.FoodSocialNetwork.app.responce;

import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Recipe;

public class ShowSingleRecipeResponse extends DefaultResponse{

	private Ingredient[] ingridients;
	private Recipe recipe;
	private String username;
	
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
}
