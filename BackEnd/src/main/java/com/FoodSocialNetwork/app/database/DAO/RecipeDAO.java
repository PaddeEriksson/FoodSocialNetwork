package com.FoodSocialNetwork.app.database.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.Mapper.RecipeMapper;

@Service
public class RecipeDAO {

	@Resource
	private JdbcOperations jdbcOperations;
	
	
	public boolean doesRecipeExist(String title)
	{
		boolean returnValue = false;
		String sql = "Select * from recipe where recipeTitle = ?";
		
		Object[] params = { title };
		
		try
		{
			Recipe r = jdbcOperations.queryForObject(sql, params,new RecipeMapper());
			returnValue = true;
		}
		catch(Exception e)
		{
			
		}
		
		return returnValue;
	}


	public boolean createRecipe(Recipe r) {
		
		String sql = "Insert into recipe(recipeTitle,instruction,time,category,creator,IMG) values(?,?,?,?,?,?)";
		boolean returnValue = true;
		Object[] params = { r.getRecipeTitle(), r.getInstruction(), r.getTime(), r.getCategory(), r.getCreator(), r.getIMG() };
		
		try
		{
			jdbcOperations.update(sql, params);
		}
		catch(Exception e)
		{
			returnValue = false;
		}
		
		return returnValue;
	}


	public Recipe getRecipe(String recipeTitle) {
		Recipe returnValue = null;
		
		String sql = "Select * from recipe where recipetitle = ?";
		
		Object[] params = { recipeTitle };
		
		try
		{
			returnValue = jdbcOperations.queryForObject(sql,params,new RecipeMapper());
			
		}
		catch(Exception e)
		{
			
		}
		
		
		return returnValue;
	}
	
	public boolean deleteRecipe(String recipeTitle)
	{
		String sql = "Delete from Recipe where recipeTitle = ?";
		Object[] params = {recipeTitle};
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
		String sql = "Select * from Recipe";
		Recipe[] returnValue = null;
		try
		{
			List<Recipe> list = jdbcOperations.query(sql,new RecipeMapper());
			returnValue = new Recipe[list.size()];
			list.toArray(returnValue);
		}
		catch(Exception e){
			System.out.println("Eror" + e.getMessage());
		}
		return returnValue;
		
	}
	
}


	
