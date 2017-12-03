package org.thinktanktutoringservice.software;

import org.thinktanktutoringservice.people.*;
import org.thinktanktutoringservice.hardware.*;

public class SoftwareDrive {

	public static void main(String[] args) {
		Schedule schedule;
		
		schedule = new Schedule();
		
		Tutor tutor1;
		Tutor tutor2;
		
		tutor1 = new Tutor();
		tutor2 = new Tutor();
		
		tutor1.setiD(100);
		tutor2.setiD(200);
		
		Student student1;
		Student student2;
		
		student1 = new Student();
		student2 = new Student();
		
		student1.setiD(300);
		student2.setiD(400);
		
		String time1 = "4:50";
		String time2 = "10:20";
		
		Room room1;
		Room room2;
		
		room1 = new Room();
		room2 = new Room();
		
		room1.setName("RoomA");
		room2.setName("RoomB");
		
		TutorSlot tutorSlot1;
		TutorSlot tutorSlot2;
		
		tutorSlot1 = new TutorSlot();
		//tutorSlot2 = new TutorSlot(tutor2);
		
		tutorSlot1.setTutor(tutor1);
		tutorSlot1.setStudent(student1);
		tutorSlot1.setTime(time1);
		tutorSlot1.setRoom(room1);
		
//		tutorSlot2.setStudent(student2);
//		tutorSlot2.setTime(time2);
//		tutorSlot2.setRoom(room2);
		
		Course course1;
		Course course2;
		
		course1 = new Course();
		course2 = new Course();
		
		course1.setName("CourseA");
		course2.setName("CourseB");
		
		DropinSlot dropinSlot;
		dropinSlot = new DropinSlot();
		
		dropinSlot.addCourse(course1);
		dropinSlot.addCourse(course2);
		dropinSlot.addTutor(tutor1);
		dropinSlot.addTutor(tutor2);
		dropinSlot.setRoom(room1);
		dropinSlot.setTime(time1);
		
//		schedule.addSlot(tutorSlot1);
//		schedule.addSlot(tutorSlot2);
//		schedule.addSlot(dropinSlot);
		
		MasterSchedule masterSchedule;
		
		masterSchedule = new MasterSchedule();
		
		Department department1;
		Department department2;
		
		department1 = new Department();
		department2 = new Department();
		
		department1.setName("DepartmentA");
		department2.setName("DepartmentB");
		
		masterSchedule.addDropinSlot(dropinSlot);
		masterSchedule.addTutor(tutor1);
		masterSchedule.addTutor(tutor2);
		masterSchedule.addRoom(room1);
		masterSchedule.addRoom(room2);
		masterSchedule.addDepartment(department1);
		masterSchedule.addDepartment(department2);
		
		/* Begin Test */
		
		/* Schedule Test*/
		schedule.addSlot(tutorSlot1);
		schedule.removeSlot(tutorSlot1);
		schedule.removeSlot(tutorSlot1);
		schedule.addSlot(tutorSlot1);
		
		/* TutorSlot Test */
//		System.out.println(tutorSlot2.getTutor().getiD());
//		System.out.println(tutorSlot2.getStudent().getiD());
//		System.out.println(tutorSlot2.getRoom().getName());
//		System.out.println(tutorSlot2.getTime());
		
		/* DropinSlot Test */
		dropinSlot.addCourse(course1);
		dropinSlot.addTutor(tutor1);
		
		/* MasterSchedule Test */
		masterSchedule.addTutor(tutor1);
		masterSchedule.addRoom(room1);
		masterSchedule.addDepartment(department1);
		masterSchedule.addDropinSlot(dropinSlot);
	}
}
