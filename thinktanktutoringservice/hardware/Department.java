package org.thinktanktutoringservice.hardware;

import java.util.ArrayList;

import org.thinktanktutoringservice.people.Student;
import java.io.Serializable;
public class Department implements Serializable{
	private String name;
	private ArrayList<Course>courses;
	private ArrayList<Student>students;
	
	public Department() {
		setName(null);
		courses = new ArrayList<Course>();
		students = new ArrayList<Student>();
	}
	
	public Department(String name) {
		this.setName(name);
		courses = new ArrayList<Course>();
		students = new ArrayList<Student>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		if (!courses.contains(course)) 
			courses.add(course);
		else
			System.out.println("Course Already exist");
	}

	public void addStudent(Student student) {
		if (!(students.contains(student)))
			students.add(student);
		else
			System.out.println("Student Already exist");
		
	}
	
	
}
