package com.FoodSocialNetwork.app.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserName(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setCountry(rs.getString("country"));
		user.setProfilePicturePath(rs.getString("profilePicurePath"));
		user.setSessionID(rs.getString("sessionID"));
		return user;
	}
	
}
