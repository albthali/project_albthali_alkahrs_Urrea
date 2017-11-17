package org.thinktanktutoringservice.people;
import org.thinktanktutoringservice.hardware.*;
import org.thinktanktutoringservice.software.*;
public class peopleDriver {
	public static void main(String[] args) {
		Department d1 = new Department();
		d1.setName("ECE");
		Course c1 = new Course();
		c1.setName("Object Oriented Software Design");
		c1.setNumber(373);
		c1.setDepartment(d1);
		Course c2 = new Course();
		c2.setName("Intro to communication");
		c2.setNumber(340);
		c2.setDepartment(d1);
		 // Initiating Students
		Student s1 = new Student(232, "Senior",  d1, "Abdalrahman", "albthali@email.arizona.edu",  "1995-3-9" , "Abdal" , "Abdal95");
		Student s2= new Student();
		s2.setName("Mohammed Alkhars");
		s2.setYear("Senior");
		s2.setDepartment(d1);
		s2.setDOB("1993-5-4");
		s2.setEmail("MohammedAlkhars@email.arizona.edu");
		s2.setiD(323);
		s2.changePassword("nopassword", "1993Mohammed");
		s1.addCourse(c1);
		// Checking that addCourse works properly 
		if(s1.addCourse(c1)) System.out.println("Cannot add " + c1.getName() + " since its already in " + s1.getName() + " Course roaster" );
		if(!(s1.addCourse(c2))) System.out.println("Course " + c2.getName() + "added to " + s1.getName() +" Course roaster");
		s2.addCourse(c1);
		 // initiating Tutors
		Tutor t1 = new Tutor();
		t1.setName("Xavier Martin Urrea");
		t1.setEmail("XavierMartinUrrea@email.arizona.edu");
		t1.setDOB("1993-7-4");
		t1.setiD(1337);
		t1.setPayrate(30.00);
		t1.addCourse(c1);
		t1.addCourse(c2);
		// checking that addcourse works properly for Tutor class
		System.out.println(t1.getProficiency().get(0).getName());
		System.out.println(c1.getTutors().get(0).getName());
		
	}
	
}
