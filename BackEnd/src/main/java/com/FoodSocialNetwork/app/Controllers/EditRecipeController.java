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
import com.FoodSocialNetwork.app.responce.DefaultResponse;


@RestController
public class EditRecipeController {
	
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private RecipeDAO recDAO;
	
	@Resource
	private IngredientDAO ingredDAO;
	
	@RequestMapping("/editRecipe")
	public DefaultResponse editRecipe(
			@RequestParam(value = "sessionID") String session,
			@RequestParam(value = "recipeID") long recipeID,
			@RequestParam(value = "recipeTitle") String newRecipeTitle,
			@RequestParam(value = "instruction") String instructions,
			@RequestParam(value = "time") long time,
			@RequestParam(value = "category", defaultValue = "") long category,
			@RequestParam(value = "ingredients") JSONArray ingredients,
			@RequestParam(value = "tools") String[] tools)
	{
		DefaultResponse dr = new DefaultResponse();
		
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			Recipe rec = recDAO.getRecipe(recipeID);
			
			if(rec != null)
			{
				if(rec.getCreator().equals(user.getEmail()))
				{
					rec.setCategory((int) category);
					rec.setIMG(null);
					
					try {
						PrintWriter pw = new PrintWriter(new File(rec.getInstruction()));
						pw.write(instructions);
						pw.flush();
						pw.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					rec.setRecipeTitle(newRecipeTitle);
					rec.setTime((int) time);
					
					recDAO.editRecipe(recipeID, rec);
					ingredDAO.deleteIngredientsFromRecipe(recipeID);
					
					for(int i = 0; i < ingredients.length(); i++)
					{
						Ingredient ingredient = new Ingredient();
						try {
							JSONObject obj = new JSONObject(ingredients.getString(i));
							ingredient.setName(obj.getString("name"));
							if(obj.getBoolean("isOptional"))
							{
								ingredient.setIsOptional(1);
							}
							else
							{
								ingredient.setIsOptional(0);
							}
							ingredient.setRecipeTitle(rec.getRecipeTitle());
							ingredient.setAmount((float) obj.getDouble("amount"));
							ingredient.setAmountType(obj.getString("amountType"));
							ingredient.setRecipeID(rec.getId());
							
							ingredDAO.createIngridient(ingredient);
								
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				else
				{
					dr.setSuccess(false);
					dr.setError("You do not have permission to edit this recipe");
				}
			}
			else
			{
				dr.setSuccess(false);
				dr.setError("Recipe does not exist");
			}
		}
		else
		{
			dr.setSuccess(false);
			dr.setError("Invalid session");
		}
		
		
		return dr;
	}
}
