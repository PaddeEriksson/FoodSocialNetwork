package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Friend;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.FriendDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;
import com.FoodSocialNetwork.app.responce.ShowAllFriendsResponce;
import com.FoodSocialNetwork.app.responce.ShowFriendListResponce;

@RestController
public class ShowMyFriendsController {
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private FriendDAO friendDAO;
	
	
	@RequestMapping("/showMyFriends")
	public DefaultResponse showMyFriends(@RequestParam(value = "sessionID") String session)
	{
		
		DefaultResponse dr = null;
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			Friend[] friends = friendDAO.getUsersFriends(user.getEmail());
			
			dr = new ShowAllFriendsResponce();
			dr.setSuccess(true);
			ShowFriendListResponce[] temp = new ShowFriendListResponce[friends.length];
			for(int i = 0; i < friends.length; i++)
			{
				User tempuser = userDAO.getUserFromEmail(friends[i].getFollowee());
				temp[i] = new ShowFriendListResponce();
				temp[i].setCountry(tempuser.getCountry());
				temp[i].setEmail(tempuser.getEmail());
				temp[i].setUsername(tempuser.getUserName());
			}
			((ShowAllFriendsResponce) dr).setFriends(temp);
		}
		else
		{
			dr = new DefaultResponse();
			dr.setError("Invalid Session");
			dr.setSuccess(false);
		}
		
		return dr;
	}
	
}
