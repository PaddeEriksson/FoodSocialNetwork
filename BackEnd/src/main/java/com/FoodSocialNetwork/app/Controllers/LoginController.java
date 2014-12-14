package com.FoodSocialNetwork.app.Controllers;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;
import com.FoodSocialNetwork.app.responce.LoginResponse;

@RestController
public class LoginController {
	
	@Resource
	private UserDAO userDAO;
	
	@RequestMapping("/login")
	public DefaultResponse login(@RequestParam(value="email") String email,@RequestParam(value="password") String password)
	{
		DefaultResponse returnValue;
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		if(userDAO.loginUser(user))
		{
			user.setSessionID(UUID.randomUUID().toString());
			if(userDAO.setSession(user))
			{
				returnValue = new LoginResponse();
				returnValue.setSuccess(true);
				((LoginResponse) returnValue).setSessionID(user.getSessionID());
			}
			else
			{
				returnValue = new DefaultResponse();
				returnValue.setSuccess(false);
				returnValue.setError("Cannot create Session");
			}
		}
		else
		{
			returnValue = new DefaultResponse();
			returnValue.setSuccess(false);
			returnValue.setError("Wrong email password combination");
		}
		
		return returnValue;
	}
}
