package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;


@RestController
public class EditRecipeController {
	
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recDAO;
	
	@Resource
	private Ingredient ingredDAO;
	
	@RequestMapping("/editRecipe")
	public DefaultResponse editRecipe(
			@RequestParam(value = "sessionID") String session,
			@RequestParam(value = "recipeID") long recipeID,
			@RequestParam(value = "newTitle") String newRecipeTitle)
	{
		DefaultResponse dr = new DefaultResponse();
		
		
		if(userDAO.getUserFromSession(session) != null)
		{
			if(recDAO.doesRecipeExist(recipeID))
			{
				recDAO.editRecipe("",new Recipe());
				
				
			}
			else
			{
				dr.setSuccess(false);
				dr.setError("Recipe dosent exist");
			}
		}
		else
		{
			dr.setSuccess(false);
			dr.setError("Invalid session");
		}
		
		
		return dr;
	}
}
