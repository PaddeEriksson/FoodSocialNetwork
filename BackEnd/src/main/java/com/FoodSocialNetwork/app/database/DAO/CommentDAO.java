package com.FoodSocialNetwork.app.database.DAO;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

@Service
public class CommentDAO {

	@Resource
	private JdbcOperations jdbcOperations;
	
	
	public void deleteCommentsWithRecipeID(long recipeID)
	{
		
		String sql = "Delete from comment where recipeID = ?";
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
