package com.FoodSocialNetwork.app.database.DAO;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Tool;

@Service
public class ToolDAO {

	@Resource
	private JdbcOperations jdbcOperations;

	public void createTool(Tool t) {

		String sql = "Insert into tool(recipeID,name) values(?,?)";
		Object[] params = {t.getRecipeID(),t.getName()};
		try
		{
			jdbcOperations.update(sql, params);
		}
		catch(Exception e)
		{
			
		}
	}
}
