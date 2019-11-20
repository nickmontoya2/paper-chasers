package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection newConnection() throws SQLException {
		// return a connection here
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/paper_chasers", "root", ""); // No password on DB right now
	}
	/*
	public static void main(String[] args) throws SQLException {
		// testing if connection works
		int id = 1;
		Connection conn = newConnection();
		PreparedStatement stmt = conn.prepareStatement("select first_name, last_name from User where ID=?");
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		results.next();
		System.out.println("First user is " + results.getNString("first_name") + " " + results.getNString("last_name"));
		conn.close();
	}
	*/
}
