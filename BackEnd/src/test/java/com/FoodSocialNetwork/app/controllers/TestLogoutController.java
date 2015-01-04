package com.FoodSocialNetwork.app.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.FoodSocialNetwork.app.Controllers.LogoutController;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

public class TestLogoutController 
{
	private LogoutController lc = new LogoutController();
	
	private UserDAO userDAO = Mockito.mock(UserDAO.class);
	
	@Before
	public void setUp()
	{
		this.lc.setDAOS(userDAO);
	}
	
	@Test
	public void testLogout()
	{
		User user = new User();
		user.setEmail("email");
		
		Mockito.when(this.userDAO.getUserFromSession("session")).thenReturn(user);
		Mockito.when(this.userDAO.setSession(user)).thenReturn(true);
		
		DefaultResponse dr = lc.logout("session");
				
		assertEquals(true, dr.isSuccess());
	}
	
	@Test
	public void testWrongSession()
	{
		User user = new User();
		user.setEmail("email");
		
		Mockito.when(this.userDAO.getUserFromSession("session")).thenReturn(user);
		Mockito.when(this.userDAO.setSession(user)).thenReturn(true);
		
		DefaultResponse dr = lc.logout("session2");
				
		assertEquals(false, dr.isSuccess());
		assertEquals("Invalid session",dr.getError());
	}
	
}
