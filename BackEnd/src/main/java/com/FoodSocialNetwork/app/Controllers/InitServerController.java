package com.FoodSocialNetwork.app.Controllers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FoodSocialNetwork.app.database.DatabaseHandler;


@RestController
public class InitServerController {
	
	@RequestMapping("/initServer")
	public boolean initServer(@RequestParam(value="adminPass")String name)
	{
		boolean returnValue = false;
		DatabaseHandler db;
		try {
			db = new DatabaseHandler();
			returnValue = db.initDatabase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
}
