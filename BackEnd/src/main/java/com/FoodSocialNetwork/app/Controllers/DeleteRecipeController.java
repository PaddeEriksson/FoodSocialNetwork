package com.FoodSocialNetwork.app.Controllers;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.DAO.CommentDAO;
import com.FoodSocialNetwork.app.database.DAO.FavoriteDAO;
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
	
	@Resource
	private CommentDAO commentDAO;
	
	@Resource
	private FavoriteDAO favoriteDAO;
	
	public void setDAOs(UserDAO userDAO, RecipeDAO recDAO, IngredientDAO ingredientDAO)
	{
		this.userDAO = userDAO;
		this.recDAO = recDAO;
		this.ingredientDAO = ingredientDAO;
	}
	
	@RequestMapping("/deleteRecipe")
	public DefaultResponse deleteRecipe(@RequestParam(value = "sessionID") String session,
										@RequestParam(value = "recipeID") long recipeID)
	{
		DefaultResponse dr = new DefaultResponse();
		
		if(userDAO.getUserFromSession(session) != null)
		{
			if(recDAO.doesRecipeExist(recipeID))
			{	
				Recipe rec = recDAO.getRecipe(recipeID);
				if(rec.getIMG() != null)
				{
					File f = new File(rec.getIMG());
					f.delete();
				}
				if(rec.getInstruction() != null)
				{
					File f = new File(rec.getInstruction());
					f.delete();
				}
				recDAO.deleteRecipe(recipeID);
				ingredientDAO.deleteIngredientsFromRecipe(recipeID);
				commentDAO.deleteCommentsWithRecipeID(recipeID);
				favoriteDAO.deleteFavoritesOfRecipeID(recipeID);
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
