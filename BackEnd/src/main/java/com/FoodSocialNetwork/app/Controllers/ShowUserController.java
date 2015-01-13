package com.FoodSocialNetwork.app.Controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;
import com.FoodSocialNetwork.app.responce.UserProfileResponse;


@RestController
public class ShowUserController {
	
	
	
	@Resource
	private UserDAO userDAO;
	
	@RequestMapping(value = "/profile/{email}")
	public DefaultResponse showUser(@RequestParam(value = "sessionID") String session,
						 @PathVariable(value = "email") String email)
	{
		//This should give name, email and country
		DefaultResponse returnValue = null;
		
		if(userDAO.getUserFromSession(session) != null)
		{
			User user = userDAO.getUserFromEmail(email);
			if(user != null)
			{
				returnValue = new UserProfileResponse();
				returnValue.setSuccess(true);
				((UserProfileResponse) returnValue).setUsername(user.getUserName());
				((UserProfileResponse) returnValue).setCountry(user.getCountry());
				((UserProfileResponse) returnValue).setEmail(user.getEmail());
			}
			else
			{
				returnValue = new DefaultResponse();
				returnValue.setError("Profile does not exist");
				returnValue.setSuccess(false);
			}
		}
		else
		{
			returnValue = new DefaultResponse();
			returnValue.setSuccess(false);
			returnValue.setError("Invalid session");
		}
		
		return returnValue;
	}
	
    @RequestMapping(value = "/userPicture",
            method = RequestMethod.GET,
            headers="Accept=image/jpeg, image/jpg, image/png, image/gif")
	public byte[] showUserPicture(@RequestParam(value = "sessionID") String session,
								  @RequestParam(value = "email") String email) throws IOException
	{
		//This will return picture of user
    	byte[] returnValue = null;
    	if(userDAO.getUserFromSession(session) != null)
    	{
    		
    		User user = userDAO.getUserFromEmail(email);
    		if(user != null)
    		{
    	      
    			String path = user.getProfilePicturePath();
    			if(path == null)
    			{
    				path = "image/imageMissing.jpg";
    				FileInputStream fis = new FileInputStream(path);
    		        InputStream is = fis;

    		        // Prepare buffered image.
    		        BufferedImage img = ImageIO.read(is);

    		        // Create a byte array output stream.
    		        ByteArrayOutputStream bao = new ByteArrayOutputStream();

    		        // Write to output stream
    		        ImageIO.write(img, "jpg", bao);
    		        returnValue = bao.toByteArray();
    			}
    			else
    			{
    				FileInputStream fis = new FileInputStream(path);
    		        InputStream is = fis;

    		        // Prepare buffered image.
    		        BufferedImage img = ImageIO.read(is);

    		        // Create a byte array output stream.
    		        ByteArrayOutputStream bao = new ByteArrayOutputStream();
    		        String fileType = "png";
    		        // Write to output stream
    		        if(path.contains(".jpg") || path.contains(".jpeg"))
    		        {
    		        	fileType = "jpg";
    		        }
    		        ImageIO.write(img, fileType, bao);
    		        returnValue = bao.toByteArray();
    			}
    	        
    		}
    		
    		
    		

    	}

        return returnValue;
	}
}
