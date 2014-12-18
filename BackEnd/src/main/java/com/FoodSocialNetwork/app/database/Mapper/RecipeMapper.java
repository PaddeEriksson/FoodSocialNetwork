package com.FoodSocialNetwork.app.database.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.FoodSocialNetwork.app.database.Recipe;

public class RecipeMapper implements RowMapper<Recipe>{

	@Override
	public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
		Recipe recipe = new Recipe();
		recipe.setId(rs.getLong("id"));
		recipe.setRecipeTitle(rs.getString("recipeTitle"));
		recipe.setInstruction(rs.getString("instruction"));
		recipe.setTime(rs.getInt("time"));
		recipe.setCategory(rs.getInt("category"));
		recipe.setCreator(rs.getString("creator"));
		recipe.setIMG(rs.getString("IMG"));
		return recipe;
	}
	

}
