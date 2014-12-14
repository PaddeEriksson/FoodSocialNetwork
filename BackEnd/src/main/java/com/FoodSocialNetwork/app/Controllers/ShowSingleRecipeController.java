package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Ingredient;
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
	private IngredientDAO ingridientDAO;
	
	
	@RequestMapping("/recipe/{recipeTitle}")
	public DefaultResponse showSingleRecipe(@RequestParam(value = "sessionID") String session, @PathVariable String recipeTitle)
	{ 
		System.out.println("Hello World " + session);
		
		ShowSingleRecipeResponse response = new ShowSingleRecipeResponse();
		
		Ingredient[] in = new Ingredient[2];
		in[0] = new Ingredient();
		in[1] = new Ingredient();
		
		response.setIngridients(in);
		
		return response;
	}
}
