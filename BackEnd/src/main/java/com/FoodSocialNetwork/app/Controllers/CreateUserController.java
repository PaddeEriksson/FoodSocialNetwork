package com.FoodSocialNetwork.app.Controllers;


import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.DatabaseHandler;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class CreateUserController {
	
    @RequestMapping("/createAccount")
    public DefaultResponse createAccount(HttpServletResponse resp, @RequestParam(value="username") String name,@RequestParam(value="password") String password, @RequestParam(value="email") String email,@RequestParam(value="country") String country) {
    	    	
    	
    	DefaultResponse dr = new DefaultResponse();
    	
    	try
    	{
    		new DatabaseHandler().createAccount(name, password, email, country);
    		dr.setSuccess(true);
    	}
    	catch(Exception e)
    	{
    		dr.setSuccess(false);
    		dr.setError(e.getMessage());
    	}
    	
    	return dr;
    }
}
