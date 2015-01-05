package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.DAO.FavoriteDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class DeleteFavoriteController {
	
	@Resource
	private FavoriteDAO favoriteDAO;
	
	@Resource
	private UserDAO userDAO;
	
	@RequestMapping("/deleteFavorite")
	
	public DefaultResponse deleteFavorite(@RequestParam(value = "sessionID") String session,
			                              @RequestParam(value = "recipeID") long recipeID)
    {
		DefaultResponse returnValue = new DefaultResponse();
		
		if(userDAO.getUserFromSession(session) != null)
		{
			if(favoriteDAO.doesFavoriteExist(recipeID))
			{
				favoriteDAO.deleteFavoritesOfRecipeID(recipeID);
				returnValue.setSuccess(true);
			}
			
			else
			{
				returnValue.setSuccess(false);
				returnValue.setError("You haven't added this recipe to your favorite!");
			}
		}
		
        
		else
		{
			returnValue.setSuccess(false);
			returnValue.setError("You have to login first!");
		}
		
		return returnValue;
    }
}
