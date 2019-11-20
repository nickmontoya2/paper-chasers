package com.skillstorm.service;

import com.skillstorm.data.UserDAO;
import com.skillstorm.projectObjects.User;

public class UserService {
	
	private UserDAO userDAO = new UserDAO(); 
	
	public User login(String username, String password) {
		// Call userDAO to login by username, password
		return userDAO.findUserByUsernamePassword(username, password); 
	}
}
