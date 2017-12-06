package org.thinktanktutoringservice.software;
import org.thinktanktutoringservice.hardware.*;
import java.io.Serializable;
import java.util.ArrayList;

import org.thinktanktutoringservice.people.*;
public abstract class Slot implements Serializable {
	
	protected String timestart;
	protected String timend;
	protected String date;
	protected String day;
	
	public String getTimestart() {
		return timestart;
	}

	public void setTimestart(String timestart) {
		this.timestart = timestart;
	}

	public String getTimend() {
		return timend;
	}

	public void setTimend(String timend) {
		this.timend = timend;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	protected Room room;
	
	public Slot() {
		timestart = "";
		timend = "";
		date = "";
		day = "";
		room = null;
	}
	
	public Slot(String date,String day, String timestart, String timend , Room room) {
		this.date = date;
		this.day = day;
		this.timestart = timestart;
		this.timend = timend;
		this.room = room;
	}
	
	
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
	abstract boolean isTutorslot();
	abstract Student getstudent();
	public static boolean checkconflict(Slot s, Slot c) {
		if(s.getDate().equals(c.getDate())) {
			Integer starttimes = Integer.parseInt(Character.toString(s.getTimestart().charAt(0))) ;
			Integer endtimes =  Integer.parseInt(Character.toString(s.getTimend().charAt(0)));
			if(starttimes >= 10 && endtimes < 10) starttimes = starttimes- 12;
			
			Integer difference = endtimes - starttimes;
			Integer starttimec = Integer.parseInt(Character.toString(c.getTimestart().charAt(0))) ;
			Integer endtimec =  Integer.parseInt(Character.toString(c.getTimend().charAt(0)));
			if(endtimec >= starttimes && endtimec - starttimes <= difference && endtimec !=  starttimes) return true;
			else if (starttimec <= endtimes && endtimes - starttimec <= difference && starttimec !=endtimes  ) return true;
			else return false;
			
			
		}
		else return false;
	}
	public abstract ArrayList<Course> getcourse();
}
