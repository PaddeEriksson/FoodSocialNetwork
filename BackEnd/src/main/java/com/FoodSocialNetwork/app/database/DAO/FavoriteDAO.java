package com.FoodSocialNetwork.app.database.DAO;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

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
}
