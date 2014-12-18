package com.FoodSocialNetwork.app.Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Ingredient;
import com.FoodSocialNetwork.app.database.Recipe;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.IngredientDAO;
import com.FoodSocialNetwork.app.database.DAO.RecipeDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.*;

@RestController
public class CreateRecipeController {

	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recipeDAO;
	
	@Resource
	private IngredientDAO ingredientDAO;
	
	public void setDAOS(UserDAO userDAO, RecipeDAO recipeDAO, IngredientDAO ingridientDAO)
	{
		this.userDAO = userDAO;
		this.recipeDAO = recipeDAO;
		this.ingredientDAO = ingridientDAO;
	}
	
	@RequestMapping("/createRecipe")
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
			r.setIMG("");
			r.setCreator(user.getEmail());
			r.setTime((int) time);
			PrintWriter pw;
			try {
				File file = new File(title + ".txt");
				pw = new PrintWriter(file);
				pw.write(instruction);
				pw.flush();
				pw.close();
				r.setInstruction(file.getAbsolutePath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(recipeDAO.createRecipe(r))
			{
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
						//TODO Create the ingridients
						
						ingredientDAO.createIngridient(ingridient);
							
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	
}
