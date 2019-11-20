package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skillstorm.projectObjects.User;

public class UserDAO {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/paper_chasers", "root", "");
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findUserByUsernamePassword(String username, String password) {
		Connection conn = getConnection();
		User user = null;
		try {
			String sql = "select ID, first_name, last_name, username, password, role_ID from User where username=? AND password=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2,  password);
			ResultSet results = stmt.executeQuery();
			// should do something to check if there are actually results here
			results.next();
			user = new User(results.getInt("ID"), results.getString("first_name"), results.getString("last_name"), results.getString("username"), results.getString("password"), results.getInt("role_ID"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e){
				throw new RuntimeException(e);
			}
		}
		
		return user;
	}

}
