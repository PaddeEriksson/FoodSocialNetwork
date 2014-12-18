package com.FoodSocialNetwork.app.controllers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.FoodSocialNetwork.app.Controllers.CreateAccountController;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

public class TestCreateAccountController {
	
	private CreateAccountController cac = new CreateAccountController();
	
	private UserDAO userDAO = Mockito.mock(UserDAO.class);
	
//	@Before
//	public void setUp()
//	{
//		cac.setDAO(userDAO);
//	}
//	
//	@Test
//	public void testCreateAccount()
//	{
//		Mockito.when(userDAO.createUser(any(User.class))).thenReturn(true);
//		DefaultResponse  ds = cac.createAccount("name", "password", "email", "country");
//		
//        assertEquals(true, ds.isSuccess());
//	}
//	
//	@Test
//	public void testAlreadyInUse()
//	{		
//		Mockito.when(userDAO.createUser(any(User.class))).thenReturn(false);
//		DefaultResponse ds = cac.createAccount("name", "password", "email", "country");
//		
//		assertEquals(false, ds.isSuccess());
//		assertEquals("Email already in use", ds.getError());
//	}
//	
//	@Test
//	public void testEmptyEmail()
//	{
//		Mockito.when(userDAO.createUser(any(User.class))).thenReturn(false);
//		DefaultResponse ds = cac.createAccount("name", "password", "", "country");
//		
//		assertEquals(false, ds.isSuccess());
//		assertEquals("Email cannot be empty", ds.getError());
//	}

}
