package com.FoodSocialNetwork.app.Controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.Comment;
import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.CommentDAO;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;

@RestController
public class CreateRateAndCommentController {
	
	
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private CommentDAO commentDAO;
	
	@RequestMapping("/rateAndComment")
	public void rateAndComment(@RequestParam (value = "sessionID") String session,
							   @RequestParam (value = "recipeID") long recipeID,
							   @RequestParam (value = "commentTitle") String commentTitle,
							   @RequestParam (value = "comment") String comment,
							   @RequestParam (value = "rate") int rate)
	{
		Comment com = new Comment();
		
		User user = userDAO.getUserFromSession(session);
		if(user != null)
		{
			commentDAO.removeComment(com);
			com.setCommentText(comment);
			com.setRecipeID(recipeID);
			com.setTitle(commentTitle);
			com.setScore(rate);
			com.setUser(user.getEmail());
			commentDAO.addComment(com);
		}
		
	}
	
}
