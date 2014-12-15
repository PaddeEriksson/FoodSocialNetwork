package com.FoodSocialNetwork.app.responce;

import java.util.ArrayList;

public class ShowAllResponse  extends DefaultResponse{
	
	private ShowRecipeListResponse[] recipes;

	public ShowRecipeListResponse[] getRecipes() {
		return recipes;
	}

	public void setRecipes(ShowRecipeListResponse[] recipes) {
		this.recipes = recipes;
	}

	
}
