package com.FoodSocialNetwork.app.Controllers;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.UserDAO;
import com.FoodSocialNetwork.app.responce.LoginResponse;

@RestController
public class LoginController {
	
	@Resource
	private UserDAO userDAO;
	
	@RequestMapping("/login")
	public LoginResponse login(@RequestParam(value="email") String email,@RequestParam(value="password") String password)
	{
		LoginResponse returnValue = new LoginResponse();
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		if(userDAO.loginUser(user))
		{
			user.setSessionID(UUID.randomUUID().toString());
			if(userDAO.setSession(user))
			{
				returnValue.setSuccess(true);
				returnValue.setSessionID(user.getSessionID());
			}
			else
			{
				returnValue.setSuccess(false);
				returnValue.setError("Cannot create Session");
			}
		}
		else
		{
			returnValue.setSuccess(false);
			returnValue.setError("Wrong email password combination");
		}
		
		return returnValue;
	}
}
