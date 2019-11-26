package com.skillstorm.service;

import java.util.List;

import com.skillstorm.data.TimesheetDAO;
import com.skillstorm.projectObjects.Timesheet;

public class TimesheetService {
	private TimesheetDAO timesheetDAO = new TimesheetDAO();
	
	// Make function to call appropriate function from TimesheetDAO
	
	public List<Timesheet> findTimesheetsByUserID(int user_ID) {
		// return all timesheets for a user
		return timesheetDAO.findTimesheetsByUserID(user_ID);
	}
	
	public Timesheet saveNewTimesheet(Timesheet timesheet) {
		return timesheetDAO.save(timesheet);
	}
	
	public Timesheet submitTimesheet(int timesheetID) {
		return timesheetDAO.updateStatus(timesheetID);
	}
	
	public Timesheet updateTimesheet(int timesheetID, float mHours, float tHours, 
			float wHours, float rHours, float fHours, String weekEnding) {
		return timesheetDAO.updateTimesheet(timesheetID, mHours, tHours, wHours,
				rHours, fHours, weekEnding);
	}
}
