package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;


@RestController
public class LogoutController {
	
	@Resource
	private UserDAO userDAO;
	
	public void setDAOS(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	
	@RequestMapping("/logout")
	public DefaultResponse logout(@RequestParam(value = "sessionID") String session)
	{
		
		User user =userDAO.getUserFromSession(session);
		DefaultResponse dr = new DefaultResponse();
		
		if(user != null)
		{
			user.setSessionID(null);
			if(userDAO.setSession(user))
			{
				dr.setSuccess(true);
			}
			else
			{
				dr.setSuccess(false);
				dr.setError("Cannot remove session");
			}
		}
		else 
		{
			dr.setSuccess(false);
			dr.setError("Invalid session");
		}
		
		
		return dr;
	}
	
	
}
