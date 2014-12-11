package com.FoodSocialNetwork.app.database.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.FoodSocialNetwork.app.database.Comment;

public class CommentMapper implements RowMapper<Comment>{

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setTitle(rs.getString("title"));
		comment.setUser(rs.getString("user"));
		comment.setScore(rs.getInt("score"));
		comment.setCommentText(rs.getString("commonText"));
		comment.setRecipeTitle(rs.getString("recipeTitle"));
		return comment;
	}

}
