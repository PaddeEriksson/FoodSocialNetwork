package com.FoodSocialNetwork.app.database;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
	
	
	
//	  public static void main(String[] argv) throws SQLException {
//	 
//		System.out.println("-------- MySQL JDBC Connection Testing ------------");
//	 
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Where is your MySQL JDBC Driver?");
//			e.printStackTrace();
//			return;
//		}
//	 
//		System.out.println("MySQL JDBC Driver Registered!");
//		Connection connection = null;
//	 
//		try {
//			connection = DriverManager
//			.getConnection("jdbc:mysql://localhost:3306/test","root", "abc123");
//	 
//		} catch (SQLException e) {
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//			return;
//		}
//	 
//		if (connection != null) {
//			System.out.println("You made it, take control your database now!");
//			String sql = "Select * from pet where name=?";
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setString(1, "here");
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next())
//			{
//				String name = rs.getString("name");
//				
//				System.out.println(name);
//			}
//		} else {
//			System.out.println("Failed to make connection!");
//		}
//	  }
	  
	  
	  public void createName(String name) throws SQLException
	  {
		  String sql = "Insert into pet(name) values(?)";
		  
		  PreparedStatement ps = connection.prepareStatement(sql);
		  
		  ps.setString(1, name);
		  
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
