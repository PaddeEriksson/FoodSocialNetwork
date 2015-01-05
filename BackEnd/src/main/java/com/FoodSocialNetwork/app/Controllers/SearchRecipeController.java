package com.FoodSocialNetwork.app.Controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Favorite;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.FavoriteDAO;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;
import com.FoodSocialNetwork.app.responce.ShowAllResponse;
import com.FoodSocialNetwork.app.responce.ShowRecipeListResponse;


@RestController
public class SearchRecipeController {

	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recipeDAO;
	
	@Resource
	private IngredientDAO ingredientDAO;
	
	@Resource
	private FavoriteDAO favoriteDAO;

	@RequestMapping("/searchRecipe")
	public DefaultResponse searchRecipe(@RequestParam(value = "sessionID") String session,
							 @RequestParam(value = "searchString") String serachString,
							 @RequestParam(value = "Ingredients", required = false) String[] searchIngredeints,
							 @RequestParam(value = "favorites", required = false) boolean favorite)
	{
		DefaultResponse returnValue = null;
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			returnValue = new ShowAllResponse();
			//Get set of all with the search string
			List<Recipe> result = recipeDAO.searchRecipe(serachString);
			//Get set of all with each of the ingredients
			//Get set of all with favorites
			if(favorite)
			{
				List<Favorite> favorites = favoriteDAO.getUsersFavorites(user);
				result.retainAll(favorites);
			}
			
			//Intersect all ingredients sets
			//Intersect search string set with ingredients set
			ShowRecipeListResponse[] srlr = new ShowRecipeListResponse[result.size()];
			
			for(int i = 0; i < srlr.length; i++)
			{
				ShowRecipeListResponse input = new ShowRecipeListResponse();
				input.setCreator(result.get(i).getCreator());
				input.setRecipeID(result.get(i).getId());
				input.setRecipeTitle(result.get(i).getRecipeTitle());
				srlr[i] = input;
			}
			returnValue.setSuccess(true);
			((ShowAllResponse)returnValue).setRecipes(srlr);
		}
		else
		{
			returnValue = new DefaultResponse();
			returnValue.setError("Invalid Session");
			returnValue.setSuccess(false);
		}
		return returnValue;
	}
}