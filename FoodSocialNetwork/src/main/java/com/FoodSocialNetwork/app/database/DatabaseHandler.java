package com.FoodSocialNetwork.app.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FoodSocialNetwork.app.responce.CreateAccountResponce;


public class DatabaseHandler {
	
	private Connection connection;
	
	public DatabaseHandler()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
	 
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/test","root", "abc123");
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}
	
	public CreateAccountResponce createAccount(String userName,String password,String email,String country)
	{
		String sql = "Insert into user(username,password,email,country) value(?,?,?,?)";
		CreateAccountResponce CAR = new CreateAccountResponce();
		try
		{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, country);
			
			ps.execute();
			CAR.setSuccess(true);
		}
		catch(Exception e)
		{
			CAR.setSuccess(false);
			CAR.setError(e.getMessage());
		}
		
		return CAR;
	}
	
	public void createName(String name) throws SQLException
	{
		  String sql = "Insert into pet(name) values(?)";
		  
		  PreparedStatement ps = connection.prepareStatement(sql);
		  
		  ps.setString(1, name);
		  
		  ps.execute();
		  
		  ps.setString(1, "GOOGLE");
		  ps.execute();  
	}
	  
	  public String[] getNames() throws SQLException
	  {		 
		  
		  String sql = "Select * from pet";
		 
		  PreparedStatement ps = connection.prepareStatement(sql);
		  
		  ResultSet rs = ps.executeQuery();
		  String temp = "";
		  while(rs.next())
		  {				 
			  temp += rs.getString("name") + " ";
		  }
		  
		  return temp.split(" ");
	  }
}
