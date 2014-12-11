package com.FoodSocialNetwork.app.database.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.FoodSocialNetwork.app.database.Ingredient;

public class IngredientMapper implements RowMapper<Ingredient>{

	@Override
	public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ingredient ingredient = new Ingredient();
		ingredient.setRecipeTitle(rs.getString("recipeTitle"));
		ingredient.setName(rs.getString("name"));
		ingredient.setIsOptional(rs.getInt("isOptional"));
		ingredient.setAmount(rs.getFloat("amount"));
		ingredient.setAmountType(rs.getString("amountType"));
		return ingredient;
	}
	
}
