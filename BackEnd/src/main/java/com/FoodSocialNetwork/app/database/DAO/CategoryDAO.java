package com.FoodSocialNetwork.app.database.DAO;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

@Service
public class CategoryDAO {

	@Resource
	private JdbcOperations jdbcOperations;
	
	
}
