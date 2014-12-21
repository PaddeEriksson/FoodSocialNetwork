package com.FoodSocialNetwork.app.Controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;
import com.FoodSocialNetwork.app.responce.ShowAllResponse;
import com.FoodSocialNetwork.app.responce.ShowRecipeListResponse;

@RestController
public class GetMyRecipesController {
	
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recDAO;
	
	public void setDAOs(UserDAO userDAO, RecipeDAO recDAO)
	{
		this.userDAO = userDAO;
		this.recDAO = recDAO;
	}
	
	@RequestMapping("/showMyRecipes")
	public DefaultResponse showMyRecipes(@RequestParam(value = "sessionID") String sessionID)
	{
		DefaultResponse dr = null;
		
		User user = userDAO.getUserFromSession(sessionID);
		if(user != null)
		{
			dr = new ShowAllResponse();
			List<Recipe> recipes = recDAO.getAllRecipeFromUser(user.getEmail());
			ShowRecipeListResponse[] srlr = new ShowRecipeListResponse[recipes.size()];
			for(int i = 0; i < recipes.size(); i++)
			{
				srlr[i] = new ShowRecipeListResponse();
				srlr[i].setCreator(recipes.get(i).getCreator());
				srlr[i].setRecipeID(recipes.get(i).getId());
				srlr[i].setRecipeTitle(recipes.get(i).getRecipeTitle());
			}
			((ShowAllResponse) dr).setRecipes(srlr);
			dr.setSuccess(true);
		}
		else
		{
			dr = new DefaultResponse();
			dr.setSuccess(false);
			dr.setError("Invalid session");
		}
		
		
		return dr;
	}
	
}
