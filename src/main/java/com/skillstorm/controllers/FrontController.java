package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Front Controller received GET from URI: " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/paper-chasers/api/timesheets":
			System.out.println("GET request for timesheets");
			new TimesheetController(req, resp).getAllTimesheets();
			break;
		default:
			break;
		} // switch
	} // doGet
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Front Controller received POST from URI: " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/paper-chasers/api/login":
			System.out.println("Login Route");
			// call login controller
			new LoginController(req, resp).userLogin();
			break;
		case "/paper-chasers/api/saveTimesheet":
			System.out.println("Save timesheet route");
			new TimesheetController(req, resp).saveTimesheet();
			break;
		default:
			break;
		} // switch
	} // End doPost
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Front Controller received PUT from URI: " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
		case "/paper-chasers/api/timesheets":
			System.out.println("Submit timesheet route");
			// Call timesheet controller
			new TimesheetController(req, resp).submitTimesheet();
			break;
		default:
			break;
		}
	} // End doPut
	
}
