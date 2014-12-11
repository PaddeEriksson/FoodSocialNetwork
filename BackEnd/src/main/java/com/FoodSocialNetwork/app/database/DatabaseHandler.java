package com.FoodSocialNetwork.app.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class DatabaseHandler {
	
	private Connection connection;
	
	public DatabaseHandler() throws SQLException, ClassNotFoundException
	{
			
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "abc123");
	 }
	
	public boolean initDatabase()
	{
		
		boolean returnValue = false;
		String sql = "";
		try {
			Scanner scan = new Scanner(new File("databaseSQL"));
			while(scan.hasNext())
			{
				sql += scan.nextLine();
			}
			
			PreparedStatement ps = connection.prepareStatement(sql.replace("\t", ""));
			ps.execute();
			
			returnValue = true;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
		
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
