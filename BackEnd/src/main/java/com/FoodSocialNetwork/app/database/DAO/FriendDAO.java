package com.FoodSocialNetwork.app.database.DAO;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import com.FoodSocialNetwork.app.database.Friend;
import com.FoodSocialNetwork.app.database.Mapper.FriendMapper;

@Service
public class FriendDAO {
	
	@Resource
	private JdbcOperations jdbcOperations;

	public void deleteAFriend(String follower,String followee)
	{
		String sql = "Delete from friend where follower = ? AND followee = ?";
		Object[] params = {follower, followee};
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public boolean doesFriendExist(String follower,String followee)
	{
		boolean returnValue = false;
		String sql = "Select * from friend where follower = ? AND followee = ?";
		
		Object[] params = { follower,followee };
		
		try
		{
			jdbcOperations.queryForObject(sql, params,new FriendMapper());
			returnValue = true;
		}
		catch(Exception e)
		{
			
		}
		
		return returnValue;
	}
	
	public boolean addFriend(Friend friend)
	{
		boolean returnValue = true;
		
	
	    String sql = "Insert into friend(follower,followee) values(?,?)";
		
		Object[] params = {friend.getFollower(),friend.getFollowee()};
		
		try
		{
			jdbcOperations.update(sql,params);
		}
		catch(Exception e)
		{
			returnValue = false;
		}
	
		return returnValue;
	}
}
