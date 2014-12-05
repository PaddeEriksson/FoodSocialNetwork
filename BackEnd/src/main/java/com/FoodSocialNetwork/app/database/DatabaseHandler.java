package com.FoodSocialNetwork.app.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FoodSocialNetwork.app.responce.DefaultResponce;


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
	 	 
		try {
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/test","root", "abc123");
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}
	
	public void createAccount(String userName,String password,String email,String country) throws SQLException
	{
		String sql = "Insert into user(username,password,email,country) value(?,?,?,?)";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setString(4, country);
			
			ps.execute();

		
	}
	
	public boolean login(String email,String password) throws SQLException
	{
		String sql = "Select * from users where email = ? AND password = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
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
