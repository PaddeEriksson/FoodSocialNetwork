package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class DeleteRecipeController {
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recDAO;
	
	@Resource
	private IngredientDAO ingredientDAO;
	
	public void setDAOs(UserDAO userDAO, RecipeDAO recDAO, IngredientDAO ingredientDAO)
	{
		this.userDAO = userDAO;
		this.recDAO = recDAO;
		this.ingredientDAO = ingredientDAO;
	}
	
	@RequestMapping("/deleteRecipe")
	public DefaultResponse deleteRecipe(@RequestParam(value = "sessionID") String session,
										@RequestParam(value = "recipeTitle") String recipeTitle)
	{
		DefaultResponse dr = new DefaultResponse();
		
		if(userDAO.getUserFromSession(session) != null)
		{
			if(recDAO.doesRecipeExist(recipeTitle))
			{				
				recDAO.deleteRecipe(recipeTitle);
				ingredientDAO.deleteIngredientsFromRecipe(recipeTitle);
				dr.setSuccess(true);
			}
			else
			{
				dr.setSuccess(false);
				dr.setError("Recipe not found");
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
