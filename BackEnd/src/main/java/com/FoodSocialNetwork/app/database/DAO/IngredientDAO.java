package com.FoodSocialNetwork.app.database.DAO;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Ingredient;

@Service
public class IngredientDAO {

	@Resource
	private JdbcOperations jdbcOperations;
	
	
	
	
	public boolean createIngridient(Ingredient ingridient)
	{
		boolean returnValue = true;
		
		String sql = "Insert into ingredient(recipeTitle,name,isOptional,amount,amountType) values(?,?,?,?,?)";
		
		Object[] params = { ingridient.getRecipeTitle(), ingridient.getName(), ingridient.getIsOptional(), ingridient.getAmount(), ingridient.getAmountType() };
		
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			returnValue = false;
		}
		
		
		return returnValue;
	}
	
	public boolean deleteIngredientsFromRecipe(long recipeID)
	{
		boolean returnValue = false;
		
		String sql = "Delete from ingredient where id = ?";
		Object[] params = {recipeID};
		
		try
		{
			jdbcOperations.update(sql,params);
			returnValue = false;
		}
		catch(Exception e)
		{
			
		}
		return returnValue;
	}
}
