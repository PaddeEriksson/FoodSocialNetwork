package com.FoodSocialNetwork.app.Controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;
import com.FoodSocialNetwork.app.responce.ShowSingleRecipeResponse;

@RestController
public class ShowSingleRecipeController {
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recipeDAO;
	
	@Resource
	private IngredientDAO ingredientDAO;
	
	public void setDAOS(UserDAO userDAO, RecipeDAO recipeDAO, IngredientDAO ingredientDAO)
	{
		this.userDAO = userDAO;
		this.recipeDAO = recipeDAO;
		this.ingredientDAO = ingredientDAO;
	}
	
	@RequestMapping("/recipe/{recipeID}")
	public DefaultResponse showSingleRecipe(@RequestParam(value = "sessionID") String session, @PathVariable long recipeID)
	{ 
		
		ShowSingleRecipeResponse response = new ShowSingleRecipeResponse();
		
		if(userDAO.getUserFromSession(session) != null)
		{
			if(recipeDAO.doesRecipeExist(recipeID))
			{
				Recipe rec = recipeDAO.getRecipe(recipeID);
				
				try {
					Scanner scan = new Scanner(new File(rec.getInstruction()));
					String tempIntructions = "";
					
					while(scan.hasNext())
					{
						tempIntructions += scan.nextLine();
					}
					
					rec.setInstruction(tempIntructions);
					scan.close();
					response.setRecipe(rec);
					response.setSuccess(true);
					
					Ingredient[] in = ingredientDAO.getIngredients(recipeID);
					response.setIngridients(in);
					response.setUsername(rec.getCreator());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else
			{
				response.setSuccess(false);
				response.setError("There is no such recipe");
			}
		}
		else
		{
			response.setSuccess(false);
			response.setError("Invalid session");
		}
		
		return response;
	}
    @RequestMapping(value = "/recipePicture/{id}",
            method = RequestMethod.GET,
            headers="Accept=image/jpeg, image/jpg, image/png, image/gif")
	public byte[] showUserPicture(@RequestParam(value = "sessionID") String session,
								  @PathVariable(value = "id") long recipeID) throws IOException
	{
		//This will return picture of user
    	byte[] returnValue = null;
    	if(userDAO.getUserFromSession(session) != null)
    	{
    		Recipe rec = recipeDAO.getRecipe(recipeID);
    		
    		rec.getIMG();
			String path = rec.getIMG();
			if(path == null)
			{
				path = "/profilePictures/defaultprofile.jpg";
			}
			
	        InputStream is = this.getClass().getResourceAsStream(path); 

	        // Prepare buffered image.
	        BufferedImage img = ImageIO.read(is);

	        // Create a byte array output stream.
	        ByteArrayOutputStream bao = new ByteArrayOutputStream();

	        // Write to output stream
	        ImageIO.write(img, "jpg", bao);
	        returnValue = bao.toByteArray();
    	}

        return returnValue;
	}
}
