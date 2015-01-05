package com.FoodSocialNetwork.app.database.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Comment;
import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Mapper.CommentMapper;
import com.FoodSocialNetwork.app.database.Mapper.IngredientMapper;

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


	public void addComment(Comment com) {
		
		String sql = "Insert into comment(title,user,score,commentText,recipeID) values(?,?,?,?,?)";
		
		Object[] params = { com.getTitle(),com.getUser(),com.getCommentText(),com.getRecipeID() };
		
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			
		}
	}


	public void removeComment(Comment com) {
		
		String sql = "Delete from comment where recipeID = ? and user = ?";
		Object[] params = {com.getRecipeID(),com.getUser()};
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			
		}
	}


	public Comment[] getCommentsFromRecipe(long recipeID) {
		
		String sql = "Select * from ingredient where recipeID = ?";
		Object[] params = {recipeID};
		
		Comment[] returnValue = null;
		try
		{
			List<Comment> ingredients = jdbcOperations.query(sql, params,new CommentMapper());
			
			returnValue = new Comment[ingredients.size()];
			for(int i = 0; i < returnValue.length; i++)
			{
				returnValue[i] = ingredients.get(i);
			}
		}
		catch(Exception e)
		{
			
		}

		
		return returnValue;
	}
	
}
