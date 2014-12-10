package com.FoodSocialNetwork.app.database;

import java.sql.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;


@Service
public class UserDAO {
	
	@Resource
	private JdbcOperations jdbcOperations;
	
	
	
	public void testIfWorking()
	{
		
		
		System.out.println("Database is " + jdbcOperations.queryForObject("SELECT CURRENT_TIMESTAMP", Date.class)); 
		
	}
	
	
}
