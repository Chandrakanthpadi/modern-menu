package com.modernmenu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConfigAndUtil {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");  
		
		Connection connection=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/dynamicmenu","root","Chandu@0502");  

		return connection;
		
	}
	
	public static int getTableCount(String name) {
		
		String value = "0";
		
		try {

			Connection connection = DBConfigAndUtil.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT MAX("+name+"_id) FROM dynamicmenu."+name+";");

			while (resultSet.next()) {
				value = resultSet.getString(1).substring(1);
			}
			connection.close();
			
		} catch (Exception e1) {
			System.out.println(e1.getStackTrace());
		}
		
		return Integer.parseInt(value);
		
	}

}
