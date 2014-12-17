package com.FoodSocialNetwork.app.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.FoodSocialNetwork.app.Controllers.ShowSingleRecipeController;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.ShowSingleRecipeResponse;

public class TestShowSingleRecipeController {
	
	private ShowSingleRecipeController ssrc = new ShowSingleRecipeController();

	private UserDAO userDAO = Mockito.mock(UserDAO.class);
	private RecipeDAO recDAO = Mockito.mock(RecipeDAO.class);
	private IngredientDAO ingredDAO = Mockito.mock(IngredientDAO.class);

	private Recipe rec = Mockito.mock(Recipe.class);

	@Before
	public void setUp()
	{
		this.ssrc.setDAOS(userDAO, recDAO, ingredDAO);
	}
	
	@Test
	public void testShowRecipe()
	{
		Mockito.when(userDAO.getUserFromSession("session")).thenReturn(new User());
		Mockito.when(recDAO.doesRecipeExist("recipeTitle")).thenReturn(true);
		Mockito.when(rec.getInstruction()).thenReturn("title.txt","whatever");
		Mockito.when(recDAO.getRecipe("recipeTitle")).thenReturn(rec);
		
		ShowSingleRecipeResponse ssrr = (ShowSingleRecipeResponse) ssrc.showSingleRecipe("session", "recipeTitle");
		
		
		
		assertEquals(true, ssrr.isSuccess());
	}
}
