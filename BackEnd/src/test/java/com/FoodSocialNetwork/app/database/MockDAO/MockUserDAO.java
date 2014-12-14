package com.FoodSocialNetwork.app.database.MockDAO;

import com.FoodSocialNetwork.app.database.User;
import com.FoodSocialNetwork.app.database.DAO.UserDAO;

public class MockUserDAO extends UserDAO{

	private boolean createUser;
	
	
	
	@Override
	public boolean createUser(User user)
	{
		return createUser;
	}



	public boolean isCreateUser() {
		return createUser;
	}



	public void setCreateUser(boolean createUser) {
		this.createUser = createUser;
	}
	
}
