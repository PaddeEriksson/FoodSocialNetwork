package com.FoodSocialNetwork.app.Controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.Tool;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.ToolDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.postRequest.CreateRecipeRequest;
import com.FoodSocialNetwork.app.responce.*;

@RestController
public class CreateRecipeController {

	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recipeDAO;
	
	@Resource
	private IngredientDAO ingredientDAO;
	
	@Resource
	private ToolDAO toolDAO;
	
	public void setDAOS(UserDAO userDAO, RecipeDAO recipeDAO, IngredientDAO ingridientDAO)
	{
		this.userDAO = userDAO;
		this.recipeDAO = recipeDAO;
		this.ingredientDAO = ingridientDAO;
	}
	
	@RequestMapping(value = "/createRecipe", method = RequestMethod.GET)
	public DefaultResponse createRecipe(
			@RequestParam(value = "session") String session,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "time") long time,
			@RequestParam(value = "instruction") String instruction,
			@RequestParam(value = "category", defaultValue = "0") long category,
			@RequestParam(value = "image", defaultValue = "None") String image,
			@RequestParam(value = "ingredients") JSONArray ingridients,
			@RequestParam(value = "tools", defaultValue = "") String[] tools)
	{
		DefaultResponse dr = new DefaultResponse();
		User user = userDAO.getUserFromSession(session);
		
		dr.setSuccess(true);
		
		
		if(user != null)//Session is not working
		{
			
			//Test if title already exist
			Recipe r = new Recipe();
			r.setRecipeTitle(title);
			r.setCategory((int) category);
			r.setIMG(null);
			r.setCreator(user.getEmail());
			r.setTime((int) time);

			long recipeID = recipeDAO.createRecipe(r);
			if(recipeID != -1)
			{
				//Write instructions to file
				PrintWriter pw;
				try {
					File file = new File("recipe/" + title + recipeID + ".txt");
					pw = new PrintWriter(file);
					pw.write(instruction);
					pw.flush();
					pw.close();
					r.setInstruction(file.getAbsolutePath());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recipeDAO.updateRecipeInstructions(recipeID, r.getInstruction());
				//Create Ingredients
				for(int i = 0; i < ingridients.length(); i++)
				{
					Ingredient ingridient = new Ingredient();
					try {
						JSONObject obj = new JSONObject(ingridients.getString(i));
						ingridient.setName(obj.getString("name"));
						if(obj.getBoolean("isOptional"))
						{
							ingridient.setIsOptional(1);
						}
						else
						{
							ingridient.setIsOptional(0);
						}
						ingridient.setRecipeTitle(r.getRecipeTitle());
						ingridient.setAmount((float) obj.getDouble("amount"));
						ingridient.setAmountType(obj.getString("amountType"));
						ingridient.setRecipeID(recipeID);
						//TODO Create the ingridients
						
						ingredientDAO.createIngridient(ingridient);
							
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//Create tools
				for(int i = 0; i < tools.length; i++)
				{
					Tool t = new Tool();
					t.setName(tools[i]);
					t.setRecipeID(recipeID);
					toolDAO.createTool(t);
				}
			}
			else
			{
				dr.setError("Recipe could not be created");
				dr.setSuccess(false);
			}
		}
		else 
		{
			dr.setSuccess(false);
			dr.setError("Session does not exist");
		}
		return dr;
	}
	
	@RequestMapping(value = "/createRecipe", method = RequestMethod.POST)
	public DefaultResponse createRecipeWithPicture(@RequestParam(value = "image", defaultValue = "") MultipartFile image,
												   @RequestParam(value = "session") String session,
												   @RequestParam(value = "title") String title,
												   @RequestParam(value = "time") long time,
												   @RequestParam(value = "instruction") String instruction,
												   @RequestParam(value = "category", defaultValue = "0") long category,
												   @RequestParam(value = "ingredients") JSONArray ingridients,
												   @RequestParam(value = "tools", defaultValue = "") String[] tools)
	{
		DefaultResponse dr = new DefaultResponse();
		User user = userDAO.getUserFromSession(session);
		
		dr.setSuccess(true);		
		if(user != null)//Session is not working
		{
			
			//Test if title already exist
			Recipe r = new Recipe();
			r.setRecipeTitle(title);
			r.setCategory((int) category);
			r.setIMG("");
			r.setCreator(user.getEmail());
			r.setTime((int) time);

			long recipeID = recipeDAO.createRecipe(r);
			if(recipeID != -1)
			{
				r.setId(recipeID);
				//Write instructions to file
				PrintWriter pw;
				try {
					File file = new File("recipe/" + title + recipeID + ".txt");
					pw = new PrintWriter(file);
					pw.write(instruction);
					pw.flush();
					pw.close();
					r.setInstruction(file.getAbsolutePath());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recipeDAO.updateRecipeInstructions(recipeID, r.getInstruction());
				//Create Ingredients
				for(int i = 0; i < ingridients.length(); i++)
				{
					Ingredient ingridient = new Ingredient();
					try {
						JSONObject obj = new JSONObject(ingridients.getString(i));
						ingridient.setName(obj.getString("name"));
						if(obj.getBoolean("isOptional"))
						{
							ingridient.setIsOptional(1);
						}
						else
						{
							ingridient.setIsOptional(0);
						}
						ingridient.setRecipeTitle(r.getRecipeTitle());
						ingridient.setAmount((float) obj.getDouble("amount"));
						ingridient.setAmountType(obj.getString("amountType"));
						ingridient.setRecipeID(recipeID);
						//TODO Create the ingridients
						
						ingredientDAO.createIngridient(ingridient);
							
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//Create tools
				for(int i = 0; i < tools.length; i++)
				{
					Tool t = new Tool();
					t.setName(tools[i]);
					t.setRecipeID(recipeID);
					toolDAO.createTool(t);
				}
				if(image != null)
				{
					this.uploadPicture(image, r);
					recipeDAO.updateRecipeImage(r);
				}
			}
			else
			{
				dr.setError("Recipe could not be created");
				dr.setSuccess(false);
			}
		}
		else 
		{
			dr.setSuccess(false);
			dr.setError("Session does not exist");
		}
		return dr;
	}
	
	public boolean uploadPicture(MultipartFile file, Recipe rec)
	{
		boolean returnValue = false;
		byte[] bytes;
		try {
			bytes = file.getBytes();
			
			if(file.getContentType().equals("image/png"))
			{
				File f = new File("image/recipe" + rec.getId() + ".png");
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
				bos.write(bytes);
				bos.flush();
				bos.close();
				returnValue = true;
				rec.setIMG(f.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
}
