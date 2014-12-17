package com.FoodSocialNetwork.app.Controllers;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class CreateAccountController {
	
	@Resource
	private UserDAO userDAO;
	
	public void setDAO(UserDAO dao)
	{
		this.userDAO = dao;
	}
	
    @RequestMapping("/createAccount")
    public DefaultResponse createAccount(@RequestParam(value="username") String name,@RequestParam(value="password") String password, @RequestParam(value="email") String email,@RequestParam(value="country") String country) {
    	DefaultResponse returnValue = new DefaultResponse();	
    	User user = new User();
    	user.setUserName(name);
    	user.setEmail(email);
    	user.setPassword(password);
    	user.setCountry(country);
    	
    	if(userDAO.createUser(user))
    	{
    		returnValue.setSuccess(true);
    	}
    	else 
    	{
    		returnValue.setSuccess(false);
    		if(email.compareTo("") == 0)
    		{
    			returnValue.setError("Email cannot be empty");
    		}
    		else
    		{
    			returnValue.setError("Email already in use");
    		}
    	}

    	return returnValue;
    }
}
