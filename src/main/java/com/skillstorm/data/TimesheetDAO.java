package com.skillstorm.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
						rs.getInt("user_ID"), Integer.parseInt(rs.getString("status_ID")), rs.getFloat("monday_hours"),
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
	
	// To grab all information for a single timesheet by it's unique ID
	public Timesheet findTimesheetById(int id) {
		new ConnectionFactory();
		Connection conn = ConnectionFactory.getConnection();
		Timesheet ts = null;
		
		try {
			String sql = "select * from Timesheet where ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			// Construst timesheet ts from results
			rs.next();
			ts = new Timesheet(rs.getInt("ID"),
					rs.getInt("user_ID"), Integer.parseInt(rs.getString("status_ID")), rs.getFloat("monday_hours"),
					rs.getFloat("tuesday_hours"), rs.getFloat("wednesday_hours"),
					rs.getFloat("thursday_hours"), rs.getFloat("friday_hours"),
					rs.getString("week_ending"));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return ts;
	} // End findTimesheetById()
	
	// For being able to edit a timesheet when you have the id
	//public Timesheet findTimesheetById(int id) {};
	
	// To save a timesheet 
	public Timesheet save(Timesheet t) {
		new ConnectionFactory();
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			String sql = "insert into Timesheet(user_ID, status_ID, monday_hours, tuesday_hours, "
					+ "wednesday_hours, thursday_hours, friday_hours, week_ending) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, t.getUser_ID());
			stmt.setInt(2, t.getStatus());
			stmt.setFloat(3, t.getMonday_hours());
			stmt.setFloat(4, t.getTuesday_hours());
			stmt.setFloat(5, t.getWednesday_hours());
			stmt.setFloat(6, t.getThursday_hours());
			stmt.setFloat(7, t.getFriday_hours());
			stmt.setString(8, t.getWeek_ending());
			
			stmt.executeUpdate();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			// Update ID to be the auto-incremented number from database
			//System.out.println(keys.getInt(1));
			t.setTimesheet_ID(keys.getInt(1));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return t;
	}; // End save()
	
	// To change the timesheet status from saved to submitted
	public Timesheet updateStatus(int timesheetID) {
		new ConnectionFactory();
		Timesheet ts = null;
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			String sql = "update Timesheet set status_ID=2 where ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, timesheetID);
			
			stmt.executeUpdate();
			//ResultSet keys = stmt.getGeneratedKeys();
			// At this point update ts to be new timesheet with updated values.
			// Create timesheet object based on values in keys
			//System.out.println("Timesheet ID: " + keys.getInt(1));
			ts = findTimesheetById(timesheetID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return ts;
	}; // End updateStatus()
	
	public Timesheet updateTimesheet(int timesheetID) {
		
		return;
	} // End updateTimesheet()
	
}
