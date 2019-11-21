package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.service.TimesheetService;
import com.skillstorm.service.UserService;

public class TimesheetController {

	private TimesheetService timesheetService = new TimesheetService();

	private HttpServletRequest request;
	private HttpServletResponse response;
	
	// Constructors
	public TimesheetController() {
		super();
	}

	public TimesheetController(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}
	
	public void getAllTimesheets() throws JsonProcessingException, IOException {
		// get user_ID from session
		int user_ID = (int) request.getSession().getAttribute("user_ID");
		// TODO: make sure user_ID is valid here
	
		// call TimesheetService to find all timesheets based on user_ID
		response.getWriter().println(
				new ObjectMapper().writeValueAsString(timesheetService.findTimesheetsByUserID(user_ID))); 
	}
	

}
