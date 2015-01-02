package com.FoodSocialNetwork.app.database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.Mapper.RecipeMapper;

@Service
public class RecipeDAO {

	@Resource
	private JdbcOperations jdbcOperations;
	
	
	public boolean doesRecipeExist(long recipeID)
	{
		boolean returnValue = false;
		String sql = "Select * from recipe where id = ?";
		
		Object[] params = { recipeID };
		
		try
		{
			jdbcOperations.queryForObject(sql, params,new RecipeMapper());
			returnValue = true;
		}
		catch(Exception e)
		{
			
		}
		
		return returnValue;
	}


	public long createRecipe(final Recipe r) {
		
		final String sql = "Insert into recipe(recipeTitle,instruction,time,category,creator,IMG) values(?,?,?,?,?,?)";
		long returnValue = -1;
		KeyHolder kh = new GeneratedKeyHolder();
		try
		{
			
			jdbcOperations.update(new PreparedStatementCreator() {           
                @Override
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, r.getRecipeTitle());
                    ps.setString(2, r.getInstruction());
                    ps.setLong(3, r.getTime());
                    ps.setLong(4, r.getCategory());
                    ps.setString(5, r.getCreator());
                    ps.setString(6, r.getIMG());
                    return ps;
                }
            }, kh);
			returnValue = kh.getKey().longValue();
		}
		catch(Exception e)
		{
			returnValue = -1;
		}
		
		return returnValue;
	}
	
	public void updateRecipeInstructions(long id, String instructionsPath)
	{
		String sql = "Update recipe set instruction = ? where id = ?";
		
		Object[] params = {instructionsPath, id };
		
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			
		}
	}

	public Recipe getRecipe(long recipeID) {
		Recipe returnValue = null;
		
		String sql = "Select * from recipe where id = ?";
		
		Object[] params = { recipeID };
		
		try
		{
			returnValue = jdbcOperations.queryForObject(sql,params,new RecipeMapper());
			
		}
		catch(Exception e)
		{
			
		}
		
		
		return returnValue;
	}
	
	public boolean deleteRecipe(long recipeID)
	{
		String sql = "Delete from recipe where id = ?";
		Object[] params = {recipeID};
		boolean returnValue = false;
		
		try
		{
			jdbcOperations.update(sql,params);
			returnValue = true;
		}
		catch(Exception e)
		{
			
		}
		
		return returnValue;
	}
	
	
	
	public Recipe[] getAllRecipe(){
		String sql = "Select * from recipe";
		Recipe[] returnValue = null;
		try
		{
			List<Recipe> list = jdbcOperations.query(sql,new RecipeMapper());
			returnValue = new Recipe[list.size()];
			list.toArray(returnValue);
		}
		catch(Exception e){
			System.out.println("Error" + e.getMessage());
		}
		return returnValue;	
	}
	
	public boolean editRecipe(long recipeID,Recipe recipe)
	{
		boolean returnValue = false;
		
		String sql = "Delete from recipe where recipeTitle = ?";
		String sql2 = "Update recipe SET recipeTitle=?,category=?,time=? where id = ?";
		
		Object[] params = {recipe.getRecipeTitle(),recipe.getCategory(),recipe.getTime(),recipeID};		
		
		try
		{
			jdbcOperations.update(sql2, params);
			returnValue = true;
		}
		catch(Exception e)
		{
			
		}
		return returnValue;
	}
	

	
	public List<Recipe> getAllRecipeFromUser(String email) {
		
		
		String sql = "Select * from recipe where creator = ?";
		Object[] params = {email};
		List<Recipe> recipes = null;
		
		try
		{
			recipes = jdbcOperations.query(sql, params,new RecipeMapper());
		}
		catch(Exception e)
		{
			System.out.println("ERROR WITH SQL CLAUSE");
			System.out.println(e.getMessage());
		}		
		return recipes;
	}


	public void updateRecipeImage(Recipe r) {
		String sql = "Update recipe set IMG = ? where id = ?";
		
		Object[] params = {r.getIMG() , r.getId()};
		
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			
		}		
	}


	public List<Recipe> searchRecipe(String serachString) {
		
		String sql = "Select * from Recipe where recipeTitle like %?%";
		
		Object[] params = {serachString};
		List<Recipe> returnValue = null;
		try
		{
			returnValue = jdbcOperations.query(sql, params,new RecipeMapper());
		}
		catch(Exception e)
		{
			
		}
		
		return returnValue;
	}	
}
