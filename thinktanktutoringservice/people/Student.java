package org.thinktanktutoringservice.people;
import org.thinktanktutoringservice.hardware.*;
import java.io.Serializable;
import org.thinktanktutoringservice.software.*;
import java.util.*;
public class Student extends Profile implements Serializable {
private int iD;
private Department department;
public int getiD() {
	return iD;
}
public void setiD(int iD) {
	this.iD = iD;
}
public Department getDepartment() {
	return department;
}
public void setDepartment(Department department) {
	this.department = department;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
private String year;
private ArrayList<Course> courses;
private Schedule schedule;
public Student(){
this.iD = 0;
this.department = null;
this.year = "";
this.courses = new ArrayList<Course>();
this.schedule = null;
}
public Student(int id, String year, Department department, String name, String email, String DOB , String username , String password){
super( name ,  email,  DOB ,  username ,  password);
this.iD = id;
this.department = department;
department.addStudent(this);
this.year = year;
this.courses = new ArrayList<Course>();
this.schedule = null;

}
public boolean addCourse(Course newcourse) {
	for(Course c: this.courses) {
		if(c.getName().equals(newcourse.getName()) && c.getNumber() == newcourse.getNumber()) {
			return true;
		}
		
	}
	this.courses.add(newcourse);
	return false;
	
}
public ArrayList<Course> getCourses() {
	return courses;
}
public void setCourses(ArrayList<Course> courses) {
	this.courses = courses;
}
public Schedule getSchedule() {
	return schedule;
}
public void setSchedule(Schedule schedule) {
	this.schedule = schedule;
}
public boolean makeAppointment(Slot newslot) {
	for(Slot s: this.schedule.getSlots()) {
		if(newslot.getTimestart().equals(s.getTimestart())) return false;
				
	}
	this.schedule.addSlot(newslot);
	return true;
}




}