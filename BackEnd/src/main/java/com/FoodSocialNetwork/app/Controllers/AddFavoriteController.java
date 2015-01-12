package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Favorite;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.FavoriteDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class AddFavoriteController {
	
	@Resource
	private FavoriteDAO favoriteDAO;
	@Resource
	private UserDAO userDAO;
	
	
	public void setDAOs(FavoriteDAO favDAO,UserDAO userDAO)
	{
		this.favoriteDAO = favDAO;
		this.userDAO = userDAO;
	}
	
	@RequestMapping("/addFavorite")
	public DefaultResponse addFavorite(@RequestParam(value = "sessionID") String session,
			                           @RequestParam(value = "recipeID") long recipeID)
			                           
    {
		DefaultResponse returnValue = new DefaultResponse();

    	User user = userDAO.getUserFromSession(session);
		if(user != null){
			
			if(!favoriteDAO.doesFavoriteExist(recipeID,user.getEmail()))
			{
				
				Favorite favorite = new Favorite();
				favorite.setRecipeID(recipeID);;
		    	favorite.setUser(user.getEmail());

				
				System.out.println("HERE?");
				favoriteDAO.addFavorite(favorite);
				returnValue.setSuccess(true);
			}
			else
			{
				returnValue.setSuccess(false);
				returnValue.setError("You have already added this to your favorite!");
			}
		}
		
		else
		{
			returnValue.setSuccess(false);
			returnValue.setError("Please login first!");
		}
		
		return returnValue;
    }
		
}
