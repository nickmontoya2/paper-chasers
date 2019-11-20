package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.projectObjects.User;
import com.skillstorm.service.UserService;

/**
 * Allows users to login
 * 
 * 
 * @author nickm
 *
 */
public class LoginController {
	
	private UserService userService = new UserService();
	
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

	public void userLogin() throws JsonProcessingException, IOException {
		// Make call to UserService to implement login check
		// if username & password match database user allow login
		// else tell user that login failed
		
		response.setContentType("application/json");
		if (request.getParameter("username") != null && request.getParameter("password") != null) {
			User user = userService.login(request.getParameter("username"), request.getParameter("password"));
			response.getWriter().println(new ObjectMapper().writeValueAsString(user));
			System.out.println("Users name is: " + user.getFirst_name() + " " + user.getLast_name());
			// Should end up redirecting to other HTML page
			// where all timesheets for this user will show up 
			// with options to edit (update), create new (insert), or submit (alter status)
		}
		
		
	}
	
}
