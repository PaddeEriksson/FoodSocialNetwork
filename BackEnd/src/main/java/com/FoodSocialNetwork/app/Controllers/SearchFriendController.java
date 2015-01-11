package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;
import com.FoodSocialNetwork.app.responce.ShowAllFriendsResponce;
import com.FoodSocialNetwork.app.responce.ShowFriendListResponce;


@RestController
public class SearchFriendController {
	
	
	@Resource
	private UserDAO userDAO;
	
	
	@RequestMapping("/searchUser")
	public DefaultResponse searchFriend(@RequestParam(value = "sessionID") String session,
										@RequestParam(value = "searchString") String searchString)
	{
		
		DefaultResponse dr = null;
		
		if(userDAO.getUserFromSession(session) != null)
		{
			User[] users = userDAO.searchUser(searchString);
			if(users != null)
			{
				dr = new ShowAllFriendsResponce();
				dr.setSuccess(true);
				ShowFriendListResponce[] temp = new ShowFriendListResponce[users.length];
				for(int i = 0; i < users.length; i++)
				{
					temp[i] = new ShowFriendListResponce();
					temp[i].setCountry(users[i].getCountry());
					temp[i].setEmail(users[i].getEmail());
					temp[i].setUsername(users[i].getUserName());
				}
				((ShowAllFriendsResponce)dr).setFriends(temp);
				
			}
			else
			{
				dr = new DefaultResponse();
				dr.setError("No Matching user");
				dr.setSuccess(false);
			}
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
