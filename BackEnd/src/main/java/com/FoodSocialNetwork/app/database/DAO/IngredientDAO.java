package com.FoodSocialNetwork.app.database.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Mapper.IngredientMapper;

@Service
public class IngredientDAO {

	@Resource
	private JdbcOperations jdbcOperations;
	
	
	
	
	public boolean createIngridient(Ingredient ingridient)
	{
		boolean returnValue = true;
		
		String sql = "Insert into ingredient(recipeID,recipeTitle,name,isOptional,amount,amountType) values(?,?,?,?,?,?)";
		
		Object[] params = {ingridient.getRecipeID(), ingridient.getRecipeTitle(), ingridient.getName(), ingridient.getIsOptional(), ingridient.getAmount(), ingridient.getAmountType() };
		
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

	public Ingredient[] getIngredients(long recipeID) {
		
		String sql = "Select * from ingredient where recipeID = ?";
		Object[] params = {recipeID};
		
		Ingredient[] returnValue = null;
		try
		{
			List<Ingredient> ingredients = jdbcOperations.query(sql, params,new IngredientMapper());
			
			returnValue = new Ingredient[ingredients.size()];
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
