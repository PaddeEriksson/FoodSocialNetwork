package com.FoodSocialNetwork.app.database.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Comment;
import com.FoodSocialNetwork.app.database.Tool;
import com.FoodSocialNetwork.app.database.Mapper.CommentMapper;
import com.FoodSocialNetwork.app.database.Mapper.ToolMapper;

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

	public String[] getToolsOfRecipe(long recipeID) {
		
		String sql = "Select * from tool where recipeID = ?";
		
		Object[] params = { recipeID };
		
		String[] returnValue = null;
		try
		{
			List<Tool> tools = jdbcOperations.query(sql, params,new ToolMapper());
			
			returnValue = new String[tools.size()];
			for(int i = 0; i < tools.size(); i++)
			{
				returnValue[i] = tools.get(i).getName();
			}
		}
		catch(Exception e)
		{
			
		}
		return returnValue;
	}
}
