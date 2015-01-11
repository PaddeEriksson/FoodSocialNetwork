package com.FoodSocialNetwork.app.Controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FoodSocialNetwork.app.database.Recipe;
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
			dr.setSuccess(true);
		}
		else
		{
			dr.setError("Invalid session");
			dr.setSuccess(false);
		}
		
		return dr;
	}
	
	@RequestMapping("/editCountry")
	public DefaultResponse editCountry(@RequestParam(value = "sessionID") String session,
										@RequestParam(value = "country") String newcountry)
	{
		DefaultResponse dr = new DefaultResponse();
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			user.setCountry(newcountry);
			userDAO.updateUser(user);
			dr.setSuccess(true);
		}
		else
		{
			dr.setError("Invalid session");
			dr.setSuccess(false);
		}
		
		return dr;
	}
	
	@RequestMapping("/editPassword")
	public DefaultResponse editPassword(@RequestParam(value = "sessionID") String session,
										@RequestParam(value = "password") String newPassword)
	{
		DefaultResponse dr = new DefaultResponse();
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			user.setPassword(newPassword);
			userDAO.updateUser(user);
			dr.setSuccess(true);
		}
		else
		{
			dr.setError("Invalid session");
			dr.setSuccess(false);
		}
		
		return dr;
	}
	
	@RequestMapping("/editProfilePicture")
	public DefaultResponse editProfilePicture(@RequestParam(value = "sessionID") String session,
										@RequestParam(value = "image") MultipartFile newFile)
	{
		DefaultResponse dr = new DefaultResponse();
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			
			this.uploadPicture(newFile, user);
			userDAO.updateUser(user);
			dr.setSuccess(true);
		}
		else
		{
			dr.setError("Invalid session");
			dr.setSuccess(false);
		}
		
		return dr;
	}
	
	public boolean uploadPicture(MultipartFile file, User user)
	{
		boolean returnValue = false;
		byte[] bytes;
		try {
			bytes = file.getBytes();
			
			if(file.getContentType().equals("image/png"))
			{
				File f = new File("image/recipe" + user.getEmail() + ".png");
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
				bos.write(bytes);
				bos.flush();
				bos.close();
				returnValue = true;
				user.setProfilePicturePath(f.getAbsolutePath());
			}
			else if(file.getContentType().equals("image/jpeg") ||  file.getContentType().equals("image/jpg"))
			{
				File f = new File("image/recipe" + user.getEmail() + ".jpg");
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
				bos.write(bytes);
				bos.flush();
				bos.close();
				returnValue = true;
				user.setProfilePicturePath(f.getAbsolutePath());
			}
			else
			{
				user.setProfilePicturePath(null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}
