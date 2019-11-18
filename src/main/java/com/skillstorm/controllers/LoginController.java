package com.skillstorm.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skillstorm.projectObjects.User;

/**
 * Allows users to 
 * 
 * 
 * @author nickm
 *
 */
public class LoginController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public LoginController() {
		super();
	}

	public LoginController(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	public void userLogin() {
		// Make call to UserDAO to implement login check
	}
	
}
