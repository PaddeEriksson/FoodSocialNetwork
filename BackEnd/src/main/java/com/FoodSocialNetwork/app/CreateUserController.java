package com.FoodSocialNetwork.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.DatabaseHandler;
import com.FoodSocialNetwork.app.responce.CreateAccountResponce;

@RestController
public class CreateUserController {
	
    @RequestMapping("/createAccount")
    public CreateAccountResponce greeting(@RequestParam(value="username") String name,@RequestParam(value="password") String password, @RequestParam(value="email") String email,@RequestParam(value="country") String country) {
    	    	
    	return new DatabaseHandler().createAccount(name, password, email, country);
    }
}
