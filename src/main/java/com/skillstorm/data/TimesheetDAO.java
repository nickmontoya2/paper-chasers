package com.skillstorm.data;

public class TimesheetDAO {
	
	// Will need a User class
	public User findbyUsername(String username) {};
	
	// Will need a timesheet class
	public List<Timesheet> findTimesheetsByUser(int id) {};
	
	// For being able to edit a timesheet when you have the id
	public Timesheet findTimesheetById(int id) {};
	
	// To save a timesheet 
	public Timesheet save(Timesheet t) {};
	
	// To change the timesheet status from saved to submitted
	public Timesheet update(Timesheet t) {};
	
}
