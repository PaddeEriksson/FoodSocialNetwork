package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.ShowAllResponse;
import com.FoodSocialNetwork.app.responce.ShowRecipeListResponse;

@RestController
public class ShowAllController {

	@Resource
	private RecipeDAO recipeDAO;
	@Resource
	private UserDAO userDAO;
	
	@RequestMapping("/ShowAll")
	public ShowAllResponse showall(@RequestParam(value = "sessionID")String session) {
		ShowAllResponse returnValue = new ShowAllResponse();
		
	
		if(userDAO.getUserFromSession(session) != null){
			returnValue.setSuccess(true);
			Recipe[] ar = recipeDAO.getAllRecipe();
			returnValue = new ShowAllResponse();
			
			ShowRecipeListResponse[] filler = new ShowRecipeListResponse[ar.length];
			for(int i = 0; i < ar.length; i++)
			{
				ShowRecipeListResponse temp = new ShowRecipeListResponse();
				temp.setCreator(ar[i].getCreator());
				temp.setRecipeTitle(ar[i].getRecipeTitle());
				temp.setRecipeID(ar[i].getId());
				filler[i] = temp;
				
			}
			returnValue.setRecipes(filler);
			
		}
		else
		{
			returnValue.setSuccess(false);
			returnValue.setError("Wrong session");
		}
		return returnValue;
	}
}
