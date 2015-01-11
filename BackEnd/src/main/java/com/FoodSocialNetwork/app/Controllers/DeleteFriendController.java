package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.FriendDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class DeleteFriendController {

	@Resource
	private UserDAO userDAO;
	
	@Resource
	private FriendDAO friendDAO;
	
	@RequestMapping("/deleteFriend")
	public DefaultResponse addFriend(@RequestParam(value = "sessionID") String session,
                                     @RequestParam(value = "follower") String follower)
    {
		DefaultResponse returnValue = new DefaultResponse();
		
		User user = userDAO.getUserFromSession(session);
		if(user != null){
			if(friendDAO.doesFriendExist(user.getEmail(), follower)){
				
				friendDAO.deleteAFriend(user.getEmail(), follower);
				returnValue.setSuccess(true);
			}
			else
			{
				returnValue.setSuccess(false);
				returnValue.setError("You should added it first then remove it!");
			}
			
		}
		else
		{
			returnValue.setSuccess(false);
			returnValue.setError("Invalid session");
		}
		
		return returnValue;
		
    }
}
