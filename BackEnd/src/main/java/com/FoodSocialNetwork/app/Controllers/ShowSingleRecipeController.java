package com.FoodSocialNetwork.app.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;
import com.FoodSocialNetwork.app.responce.ShowSingleRecipeResponse;

@RestController
public class ShowSingleRecipeController {
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recipeDAO;
	
	@Resource
	private IngredientDAO ingredientDAO;
	
	public void setDAOS(UserDAO userDAO, RecipeDAO recipeDAO, IngredientDAO ingredientDAO)
	{
		this.userDAO = userDAO;
		this.recipeDAO = recipeDAO;
		this.ingredientDAO = ingredientDAO;
	}
	
	@RequestMapping("/recipe/{recipeID}")
	public DefaultResponse showSingleRecipe(@RequestParam(value = "sessionID") String session, @PathVariable long recipeID)
	{ 
		
		ShowSingleRecipeResponse response = new ShowSingleRecipeResponse();
		
		if(userDAO.getUserFromSession(session) != null)
		{
			if(recipeDAO.doesRecipeExist(recipeID))
			{
				Recipe rec = recipeDAO.getRecipe(recipeID);
				
				try {
					Scanner scan = new Scanner(new File(rec.getInstruction()));
					String tempIntructions = "";
					
					while(scan.hasNext())
					{
						tempIntructions += scan.nextLine();
					}
					
					rec.setInstruction(tempIntructions);
					scan.close();
					response.setRecipe(rec);
					response.setSuccess(true);
					
					Ingredient[] in = ingredientDAO.getIngredients(recipeID);
					response.setIngridients(in);
					response.setUsername(rec.getCreator());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else
			{
				response.setSuccess(false);
				response.setError("There is no such recipe");
			}
		}
		else
		{
			response.setSuccess(false);
			response.setError("Invalid session");
		}
		
		return response;
	}
}
