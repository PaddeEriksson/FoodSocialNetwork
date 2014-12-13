package com.FoodSocialNetwork.app;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;

public class MockUserDAO extends UserDAO{

	private boolean returnTester;
	
	
	
	@Override
	public boolean createUser(User user)
	{
		return returnTester;
	}



	public boolean isReturnTester() {
		return returnTester;
	}



	public void setReturnTester(boolean returnTester) {
		this.returnTester = returnTester;
	}
	
}
