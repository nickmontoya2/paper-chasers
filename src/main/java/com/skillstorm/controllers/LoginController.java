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
		User user = null;
		
		response.setContentType("application/json");
		
		if (request.getParameter("username") != null && request.getParameter("password") != null) {
			user = userService.login(request.getParameter("username"), request.getParameter("password"));
			response.getWriter().println(new ObjectMapper().writeValueAsString(user));			
		} // end if
		// Should end up redirecting to other HTML page
		// where all timesheets for this user will show up 
		// with options to edit (update), create new (insert), or submit (alter status)

		if (user != null) {
			// Create a session to store user_ID 
			System.out.println(request.getSession().getAttribute("user_ID"));
			
			request.getSession().setAttribute("user_ID", user.getUser_id());
			
			System.out.println(request.getSession().getAttribute("user_ID"));
			// redirect to timesheetPortal.html
			response.sendRedirect("/paper-chasers/timesheetPortal.html");
			
		} else {
			// user didn't exist, tell user that login info was wrong
		}
		
	}
	
}
