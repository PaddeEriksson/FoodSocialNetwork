package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Comment;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.CommentDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;
import com.FoodSocialNetwork.app.responce.DefaultResponse;

@RestController
public class CreateRateAndCommentController {
	
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private CommentDAO commentDAO;
	
	@RequestMapping("/rateAndComment")
	public DefaultResponse rateAndComment(@RequestParam (value = "sessionID") String session,
							   @RequestParam (value = "recipeID") long recipeID,
							   @RequestParam (value = "comment") String comment,
							   @RequestParam (value = "rate") int rate)
	{
		Comment com = new Comment();
		DefaultResponse dr = new DefaultResponse();
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			com.setCommentText(comment);
			com.setRecipeID(recipeID);
			com.setScore(rate);
			com.setUser(user.getEmail());
			
			commentDAO.removeComment(com);

			commentDAO.addComment(com);
			dr.setSuccess(true);
		}
		else
		{
			dr.setError("Invalid session");
			dr.setSuccess(false);
		}
		return dr;
	}
	
}
