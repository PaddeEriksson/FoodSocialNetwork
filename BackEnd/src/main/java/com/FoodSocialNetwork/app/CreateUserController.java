package com.FoodSocialNetwork.app;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.DatabaseHandler;
import com.FoodSocialNetwork.app.responce.DefaultResponce;

@RestController
public class CreateUserController {
	
    @RequestMapping("/createAccount")
    public DefaultResponce greeting(HttpServletResponse resp, @RequestParam(value="username") String name,@RequestParam(value="password") String password, @RequestParam(value="email") String email,@RequestParam(value="country") String country) {
    	    	
    	
    	return new DatabaseHandler().createAccount(name, password, email, country);
    }
}
