package com.FoodSocialNetwork.app.database.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.FoodSocialNetwork.app.database.Tool;

public class ToolMapper implements RowMapper<Tool>{

	@Override
	public Tool mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tool tool = new Tool();
		tool.setRecipeTitle(rs.getString("recipeTitle"));
		tool.setName(rs.getString("name"));
		return tool;
	}

}
