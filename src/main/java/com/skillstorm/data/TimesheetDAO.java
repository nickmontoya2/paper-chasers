package com.skillstorm.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.projectObjects.Timesheet;

public class TimesheetDAO {
	
	// Will need a User class
	//public User findbyUsername(String username) {};
	
	public List<Timesheet> findTimesheetsByUserID(int id) {
		new ConnectionFactory();
		Connection conn = ConnectionFactory.getConnection();
		LinkedList<Timesheet> results = new LinkedList<>();
		
		try {
			String sql = "select * from Timesheet where user_ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			// Add all timesheets back to list
			while(rs.next()) {
				Timesheet ts = new Timesheet(rs.getInt("ID"),
						rs.getInt("user_ID"), rs.getString("status_ID"), rs.getFloat("monday_hours"),
						rs.getFloat("tuesday_hours"), rs.getFloat("wednesday_hours"),
						rs.getFloat("thursday_hours"), rs.getFloat("friday_hours"),
						rs.getString("week_ending"));
				results.add(ts);
			} //end while
			
		} catch (SQLException e) {	
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return results;
	};
	
	// For being able to edit a timesheet when you have the id
	//public Timesheet findTimesheetById(int id) {};
	
	// To save a timesheet 
	//public Timesheet save(Timesheet t) {};
	
	// To change the timesheet status from saved to submitted
	//public Timesheet update(Timesheet t) {};
	
}
