package com.FoodSocialNetwork.app.database.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.Mapper.UserMapper;


@Service
public class UserDAO{
	
	@Resource
	private JdbcOperations jdbcOperations;
	
	
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
			jdbcOperations.query(sql, params, new UserMapper());
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
	
	public User getUserFromSession(String session)
	{
		User user = null;
		
		if(session != null)
		{	
			String sql = "Select * from user where sessionID = ?";
			
			Object[] params = { session };
			
			try
			{
				user = jdbcOperations.queryForObject(sql, params, new UserMapper());
			}
			catch(Exception e)
			{
				
			}
		}
		return user;
	}

	public User getUserFromEmail(String email) {
		User user = null;
		
		if(email != null)
		{	
			String sql = "Select * from user where email = ?";
			
			Object[] params = { email };
			
			try
			{
				user = jdbcOperations.queryForObject(sql, params, new UserMapper());
			}
			catch(Exception e)
			{
				
			}
		}
		return user;		
	}

	public void updateUser(User user) {
		String sql = "Update user set userName = ?, "
				+ "						password = ?, "
				+ "						country = ?, "
				+ "						profilePicurePath = ?"
				+ "						where email = ?";
		
		Object[] params = {user.getUserName(), user.getPassword(), user.getCountry(), user.getProfilePicturePath(), user.getEmail() };
		
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			
		}
		
	}

	public User[] searchUser(String searchString) {
		
		
		String sql = "Select * from user where email like ?";
		searchString = "%" + searchString + "%";
		
		Object[] params = { searchString };
		
		User[] returnValue = null;
		try
		{
			List<User> users = jdbcOperations.query(sql, params,new UserMapper());
			returnValue = new User[users.size()];
			users.toArray(returnValue);
		}
		catch(Exception e)
		{
			
		}
		
		return returnValue;
	}
}
