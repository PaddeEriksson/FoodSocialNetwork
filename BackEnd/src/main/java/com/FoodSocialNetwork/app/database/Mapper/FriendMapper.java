package com.FoodSocialNetwork.app.database.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.FoodSocialNetwork.app.database.Favorite;
import com.FoodSocialNetwork.app.database.Friend;

public class FriendMapper implements RowMapper<Friend>{

	@Override
	public Friend mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Friend friend = new Friend();
		
		friend.setFollower(rs.getString("follower"));
		friend.setFollowee(rs.getString("followee"));
		
		return friend;
	}

}
