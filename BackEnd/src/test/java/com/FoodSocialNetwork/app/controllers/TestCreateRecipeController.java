package com.FoodSocialNetwork.app.controllers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.FoodSocialNetwork.app.Controllers.CreateRecipeController;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

public class TestCreateRecipeController {

	private CreateRecipeController crc = new CreateRecipeController();
	
	private UserDAO userDAO = Mockito.mock(UserDAO.class);
	private RecipeDAO recDAO = Mockito.mock(RecipeDAO.class);
	private IngredientDAO ingredDAO = Mockito.mock(IngredientDAO.class);
		
	@Before
	public void setUp()
	{
		this.crc.setDAOS(userDAO, recDAO, ingredDAO);
	}
	
	@Test
	public void testCreateRecipe()
	{
		Mockito.when(userDAO.getUserFromSession("session")).thenReturn(new User());
		Mockito.when(recDAO.createRecipe(any(Recipe.class))).thenReturn(true);
		
		DefaultResponse  dr = crc.createRecipe("session", "title", 2L, "instruction", 2L, "image", new JSONArray(), "tool tool2".split(" "));
		
		assertEquals(true, dr.isSuccess());
		
	}
	
	@Test
	public void testWrongSession()
	{
		Mockito.when(userDAO.getUserFromSession("session")).thenReturn(null);
		
		DefaultResponse  dr = crc.createRecipe("session", "title", 2L, "instruction", 2L, "image", new JSONArray(), "tool tool2".split(" "));
		
		assertEquals(false, dr.isSuccess());
		assertEquals("Session does not exist", dr.getError());

	}
}
