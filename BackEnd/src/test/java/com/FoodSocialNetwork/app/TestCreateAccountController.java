package com.FoodSocialNetwork.app;

import static org.junit.Assert.*;

import org.junit.Test;

import com.FoodSocialNetwork.app.Controllers.CreateAccountController;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

public class TestCreateAccountController {
	
	private CreateAccountController cac = new CreateAccountController();
	private MockUserDAO mocDAO = new MockUserDAO();
	
	@Test
	public void testCreateAccount()
	{
		mocDAO.setReturnTester(true);
		
		cac.setDAO(mocDAO);
		DefaultResponse  ds = cac.createAccount("name", "password", "email", "country");
		
        assertEquals(true, ds.isSuccess());
	}
	
	@Test
	public void testAlreadyInUse()
	{
		mocDAO.setReturnTester(false);
		
		cac.setDAO(mocDAO);
		DefaultResponse ds = cac.createAccount("name", "password", "email", "country");
		
		assertEquals(false, ds.isSuccess());
		assertEquals("Email already in use", ds.getError());
	}
	
	@Test
	public void testEmptyEmail()
	{
		mocDAO.setReturnTester(false);
		
		cac.setDAO(mocDAO);
		DefaultResponse ds = cac.createAccount("name", "password", "", "country");
		
		assertEquals(false, ds.isSuccess());
		assertEquals("Email cannot be empty", ds.getError());
	}

}
