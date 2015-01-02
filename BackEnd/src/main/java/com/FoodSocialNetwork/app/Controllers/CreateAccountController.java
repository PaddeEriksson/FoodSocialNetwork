package com.FoodSocialNetwork.app.Controllers;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	//public DefaultResponse createAccount(@RequestBody CreateAccountRequest car)
    @RequestMapping(value = "/createAccount", method=RequestMethod.GET)
    public DefaultResponse createAccount(@RequestParam(value="username") String name,@RequestParam(value="password") String password, @RequestParam(value="email") String email,@RequestParam(value="country") String country)
    {
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
    		if(user.getEmail().compareTo("") == 0)
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
 
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
        	System.out.println(file.getName());
        	file.getOriginalFilename();
            String name = file.getOriginalFilename();
        	try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + " because the file was empty.";
        }
    }
}
