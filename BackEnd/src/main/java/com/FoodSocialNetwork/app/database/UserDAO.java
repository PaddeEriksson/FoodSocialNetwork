package com.FoodSocialNetwork.app.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;


@Service
public class UserDAO {
	
	@Resource
	private JdbcOperations jdbcOperations;
	@Resource
	private DataSource dataSource;
	
	
	public void testIfWorking()
	{
		System.out.println("Database is " + jdbcOperations.queryForObject("SELECT CURRENT_TIMESTAMP", Date.class)); 
	}
	
	public boolean createUser(User user)
	{
		String sql = "Insert into user(username,password,email,country) value(?,?,?,?)";
		
		boolean returnValue = true;
		Object[] params = { user.getUserName(), user.getPassword(), user.getEmail(), user.getCountry() };

		
		try
		{
			jdbcOperations.update(sql, params);
		}
		catch(Exception x)
		{
			returnValue = false;
		}
		
		return returnValue;
	}
	
	public boolean loginUser(User user)
	{
		
		String sql = "Select * from user where email = ? AND password = ?";
		boolean returnValue = true;
		
		Object[] params = { user.getEmail(), user.getPassword()};
		
		try
		{
			User returnUser = jdbcOperations.queryForObject(sql, params, new UserMapper());
			returnValue = returnUser != null;
		}
		catch(Exception e)
		{
			returnValue = false;
		}
		return returnValue;
	}
	
	public boolean setSession(User user)
	{
		String sql = "Update user SET sessionID = ? WHERE email = ?";
		
		Object[] params = { user.getSessionID(), user.getEmail()};

		int value = jdbcOperations.update(sql, params);

		return value != 0;
	}
	
}
