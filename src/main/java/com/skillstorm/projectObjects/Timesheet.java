package com.skillstorm.projectObjects;

public class Timesheet {
	
	private int timesheet_ID;
	private int user_ID;
	private int status_ID;
	private float monday_hours;
	private float tuesday_hours;
	private float wednesday_hours;
	private float thursday_hours;
	private float friday_hours;
	private String week_ending;
	
	// Constructors
	public Timesheet() {
		super();
	}

	public Timesheet(int timesheet_ID, int user_ID, int status_ID, float monday_hours, float tuesday_hours,
			float wednesday_hours, float thursday_hours, float friday_hours, String week_ending) {
		super();
		this.timesheet_ID = timesheet_ID;
		this.user_ID = user_ID;
		this.status_ID = status_ID;
		this.monday_hours = monday_hours;
		this.tuesday_hours = tuesday_hours;
		this.wednesday_hours = wednesday_hours;
		this.thursday_hours = thursday_hours;
		this.friday_hours = friday_hours;
		this.week_ending = week_ending;
	}

	// Getters & Setters
	public int getTimesheet_ID() {
		return timesheet_ID;
	}

	public void setTimesheet_ID(int timesheet_ID) {
		this.timesheet_ID = timesheet_ID;
	}

	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public int getStatus() {
		return status_ID;
	}

	public void setStatus(int status_ID) {
		this.status_ID = status_ID;
	}

	public float getMonday_hours() {
		return monday_hours;
	}

	public void setMonday_hours(float monday_hours) {
		this.monday_hours = monday_hours;
	}

	public float getTuesday_hours() {
		return tuesday_hours;
	}

	public void setTuesday_hours(float tuesday_hours) {
		this.tuesday_hours = tuesday_hours;
	}

	public float getWednesday_hours() {
		return wednesday_hours;
	}

	public void setWednesday_hours(float wednesday_hours) {
		this.wednesday_hours = wednesday_hours;
	}

	public float getThursday_hours() {
		return thursday_hours;
	}

	public void setThursday_hours(float thursday_hours) {
		this.thursday_hours = thursday_hours;
	}

	public float getFriday_hours() {
		return friday_hours;
	}

	public void setFriday_hours(float friday_hours) {
		this.friday_hours = friday_hours;
	}

	public String getWeek_ending() {
		return week_ending;
	}

	public void setWeek_ending(String week_ending) {
		this.week_ending = week_ending;
	}

	// Extra methods
	@Override
	public String toString() {
		return "Timesheet [timesheet_ID=" + timesheet_ID + ", user_ID=" + user_ID + ", status_ID=" + status_ID
				+ ", week_ending=" + week_ending + "]";
	}	

}
