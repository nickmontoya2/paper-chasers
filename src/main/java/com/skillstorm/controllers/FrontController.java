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
		case "":
			System.out.println("");
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
		default:
			break;
		} // switch
	} // doPost
	
}
