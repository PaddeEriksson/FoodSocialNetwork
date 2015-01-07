package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class EditAccountController {
	
	
	@Resource
	private UserDAO userDAO;
	
	
	@RequestMapping("/editUsername")
	public DefaultResponse editUserName(@RequestParam(value = "sessionID") String session,
										@RequestParam(value = "username") String newUsername)
	{
		DefaultResponse dr = new DefaultResponse();
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			user.setUserName(newUsername);
			userDAO.updateUser(user);
		}
		else
		{
			dr.setError("Invalid session");
			dr.setSuccess(false);
		}
		
		return null;
	}
}
