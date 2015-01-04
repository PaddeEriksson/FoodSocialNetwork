package com.FoodSocialNetwork.app.database.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Favorite;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.Mapper.FavoriteMapper;
import com.FoodSocialNetwork.app.database.Mapper.RecipeMapper;

@Service
public class FavoriteDAO {

	@Resource
	private JdbcOperations jdbcOperations;
	
	public void deleteFavoritesOfRecipeID(long recipeID)
	{
		String sql = "Delete from favorite where recipeID = ?";
		Object[] params = {recipeID};
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			
		}
	}

	public List<Favorite> getUsersFavorites(User user) {
		
		String sql = "Select * from favorite where user = ?";
		
		Object[] params = {user.getEmail()};
		List<Favorite> returnValue = null;
		try
		{
			returnValue = jdbcOperations.query(sql, params,new FavoriteMapper());
		}
		catch(Exception e)
		{
			
		}
		return returnValue;
	}
}
