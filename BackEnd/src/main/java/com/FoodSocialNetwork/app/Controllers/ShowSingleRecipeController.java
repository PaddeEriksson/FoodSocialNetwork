package com.FoodSocialNetwork.app.Controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

import com.FoodSocialNetwork.app.database.Comment;
import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.CommentDAO;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.ToolDAO;
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
	
	@Resource
	private CommentDAO commentDAO;
	
	@Resource
	private ToolDAO toolDAO;
	
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
						tempIntructions += scan.nextLine() + "\n";
					}
					
					rec.setInstruction(tempIntructions);
					scan.close();
					response.setRecipe(rec);
					response.setSuccess(true);
					
					Ingredient[] in = ingredientDAO.getIngredients(recipeID);
					response.setIngridients(in);
					
					Comment[] com = commentDAO.getCommentsFromRecipe(recipeID);
					response.setComments(com);
					
					String[] tools = toolDAO.getToolsOfRecipe(recipeID);
					response.setTools(tools);
					
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
    		produces = "application/json; charset=utf-8",
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

        return returnValue;
	}
}
