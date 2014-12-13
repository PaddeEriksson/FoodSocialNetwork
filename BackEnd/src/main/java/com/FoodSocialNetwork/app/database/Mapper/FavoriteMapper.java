package com.FoodSocialNetwork.app.database.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.FoodSocialNetwork.app.database.Favorite;

public class FavoriteMapper implements RowMapper<Favorite>{

	@Override
	public Favorite mapRow(ResultSet rs, int rowNum) throws SQLException {
		Favorite favorite = new Favorite();
		favorite.setRecipeTitle(rs.getString("recipeTitle"));
		favorite.setUser(rs.getString("user"));
		return favorite;
	}

}
