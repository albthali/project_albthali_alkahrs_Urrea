package org.thinktanktutoringservice.people;
import org.thinktanktutoringservice.software.*;
import java.io.Serializable;
import org.thinktanktutoringservice.hardware.*;
import java.util.*;
public class Tutor extends Profile implements Serializable{
	private int iD;
	private ArrayList<Course> proficiency;
	private Schedule schedule;
	private double payrate;
	public Tutor(){
		this.iD = 0;
		this.proficiency = new ArrayList<Course>();
		this.schedule = new Schedule();
		this.payrate = 0;
		
	}
	public Tutor(int id, double payrate ,String name, String email, String DOB , String username , String password){
		super(name, email, DOB, username, password);
		this.iD = id;
		this.payrate = payrate;
		this.proficiency = new ArrayList<Course>();
		this.schedule = new Schedule();
	}
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public ArrayList<Course> getProficiency() {
		return proficiency;
	}
	public void setProficiency(ArrayList<Course> proficiency) {
		this.proficiency = proficiency;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public double getPayrate() {
		return payrate;
	}
	public void setPayrate(double payrate) {
		this.payrate = payrate;
	}
	public boolean addSlot(Slot newslot) {
		for(Slot s: this.schedule.getSlots()) {
			if(newslot.getTime().equals(s.getTime())) return false;
					
		}
		this.schedule.addSlot(newslot);
		return true;
	}
	public boolean addCourse(Course newcourse) {
		for(Course c: this.proficiency) {
			if(c.getName().equals(newcourse.getName()) && c.getNumber() == newcourse.getNumber()) {
				return true;
			}
			
		}
		this.proficiency.add(newcourse);
		boolean s = newcourse.addTutor(this);
		return false;
		
	}
	
}
