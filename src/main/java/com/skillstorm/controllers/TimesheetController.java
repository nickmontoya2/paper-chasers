package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.projectObjects.Timesheet;
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
	
	// API calls
	public void getAllTimesheets() throws JsonProcessingException, IOException {
		// get user_ID from session
		int user_ID = getUserId();
		// TODO: make sure user_ID is valid here
		
		// call TimesheetService to find all timesheets based on user_ID
		response.setContentType("application/json");
		response.getWriter().println(
				new ObjectMapper().writeValueAsString(timesheetService.findTimesheetsByUserID(user_ID))); 
	}
	
	// Save timesheet from api call
	public void saveTimesheet() { 
		int user_ID = getUserId();
		int saved = 1;
		// get parameters from request
		Float mHours = Float.parseFloat(request.getParameter("monday_hours"));
		Float tHours = Float.parseFloat(request.getParameter("tuesday_hours"));
		Float wHours = Float.parseFloat(request.getParameter("wednesday_hours"));
		Float rHours = Float.parseFloat(request.getParameter("thursday_hours"));
		Float fHours = Float.parseFloat(request.getParameter("friday_hours"));
		String weekEnding = request.getParameter("week_ending");
		
		if (mHours!=null && tHours!=null && wHours!=null && rHours!=null && fHours!=null && weekEnding!=null) {
			// create new Timesheet & pass to service. default timesheet_ID, will overwrite after
			// auto-increment value returns from database
			Timesheet newTimesheet = new Timesheet(0, user_ID, saved, mHours, tHours, wHours, 
					rHours, fHours, weekEnding);
			// pass to service
			timesheetService.saveNewTimesheet(newTimesheet);
		} // End if
	} // end saveTimesheet()
	
	
	// Helper methods
	private int getUserId() {
		return ((int) request.getSession().getAttribute("user_ID"));
	}

}
