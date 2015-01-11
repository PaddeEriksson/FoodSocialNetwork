package com.FoodSocialNetwork.app.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.FoodSocialNetwork.app.Controllers.CreateRateAndCommentController;
import com.FoodSocialNetwork.app.database.Comment;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.CommentDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;



public class TestRateAndCommentController {
	
	
	private CreateRateAndCommentController cracc = new CreateRateAndCommentController();
	
	private UserDAO userDAO = Mockito.mock(UserDAO.class);
	private CommentDAO comDAO = Mockito.mock(CommentDAO.class);
	
	@Before
	public void setUp()
	{
		cracc.setDAOs(userDAO,comDAO);
	}
	
	@Test
	public void testSuccess()
	{
		Mockito.when(userDAO.getUserFromSession("session")).thenReturn(new User());
		
		DefaultResponse dr = cracc.rateAndComment("session", 2, "", 2);
		
		assertEquals(true, dr.isSuccess());
	}
	
	
}
