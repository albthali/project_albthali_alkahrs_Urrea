package org.thinktanktutoringservice.hardware;
import org.thinktanktutoringservice.people.*;	
import org.thinktanktutoringservice.software.*;	
import java.util.*;
import java.io.Serializable;
public class Course implements Serializable {

	private String name;
	private Integer number;
	private Department department;
	private ArrayList<Tutor> tutors;
	public Course() {
	this.name ="";
	this.number = 0;
	this.department = null;
	this.tutors = new ArrayList<Tutor>();
	}
	public Course(String name,  Integer number, Department department) {
		this.name = name;
		this.number = number;
		this.department = department;
		this.tutors = new ArrayList<Tutor>();
		department.addCourse(this);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public ArrayList<Tutor> getTutors() {
		return tutors;
	}
	public void setTutors(ArrayList<Tutor> tutors) {
		this.tutors = tutors;
	}
	public boolean addTutor(Tutor newtutor) {
		for(Tutor t: this.tutors) {
			if(t.getName().equals(newtutor.getName())) return true;
			
		}
		this.tutors.add(newtutor);
		boolean s = newtutor.addCourse(this);
		return s;
		
	}
	
}
