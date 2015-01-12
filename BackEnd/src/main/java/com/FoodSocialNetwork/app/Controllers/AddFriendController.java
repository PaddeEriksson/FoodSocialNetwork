package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Favorite;
import com.FoodSocialNetwork.app.database.Friend;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.FriendDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class AddFriendController {

	@Resource
	private UserDAO userDAO;
	
	@Resource
	private FriendDAO friendDAO;
	
	@RequestMapping("/addFriend")
	public DefaultResponse addFriend(@RequestParam(value = "sessionID") String session,
			                           @RequestParam(value = "follower") String follower
			                         )
    {
		DefaultResponse returnValue = new DefaultResponse();
		Friend friend = new Friend();
		friend.setFollowee(follower);
    	friend.setFollower(userDAO.getUserFromSession(session).getEmail());
		
		User user = userDAO.getUserFromSession(session);
		
		if(user != null){
			if(!friendDAO.doesFriendExist(user.getEmail(), follower))
			{
				friendDAO.addFriend(friend);
				returnValue.setSuccess(true);
				 
			}
			else
			{
				returnValue.setSuccess(false);
				returnValue.setError("You have already added this friend!");
			}
		}
		
		else
		{
			returnValue.setSuccess(false);
			returnValue.setError("Please login first!");
		}
		return returnValue;
		
    }
}
