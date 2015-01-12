package com.FoodSocialNetwork.app.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.FoodSocialNetwork.app.Controllers.AddFavoriteController;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.FavoriteDAO;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

public class TestAddFavoriteController {
	
	private AddFavoriteController afc = new AddFavoriteController();

	private UserDAO userDAO = Mockito.mock(UserDAO.class);
	private FavoriteDAO favDAO = Mockito.mock(FavoriteDAO.class);
	
	@Before
	public void setUp()
	{
		afc.setDAOs(favDAO, userDAO);
	}
	
	
	
	@Test
	public void testSuccess()
	{
		User u = new User();
		u.setEmail("testMail");
		
		Mockito.when(userDAO.getUserFromSession("session")).thenReturn(u);
		Mockito.when(favDAO.doesFavoriteExist(2, "testMail")).thenReturn(false);
		
		DefaultResponse dr = afc.addFavorite("session", 2);
		
		assertEquals(true, dr.isSuccess());
	}
	
	@Test
	public void testIfAlreadyAdded()
	{
		User u = new User();
		u.setEmail("testMail2");
		
		Mockito.when(userDAO.getUserFromSession("session")).thenReturn(u);
		Mockito.when(favDAO.doesFavoriteExist(2L, "testMail2")).thenReturn(true);
		
		DefaultResponse dr = afc.addFavorite("session", 2L);
		
		assertEquals(false, dr.isSuccess());
		assertEquals("You have already added this to your favorite!", dr.getError());

	}
}
