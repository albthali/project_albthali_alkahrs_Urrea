package org.thinktanktutoringservice.software;
import org.thinktanktutoringservice.hardware.*;
import org.thinktanktutoringservice.people.*;
import java.util.ArrayList;

import org.thinktanktutoringservice.people.Tutor;


public class MasterSchedule {

	public ArrayList<Tutor>tutors;
	public ArrayList<Room>rooms;
	public ArrayList<Department>departments;
	public Schedule dropinSchedule;
	
	public MasterSchedule() {
		tutors = new ArrayList<Tutor>();
		rooms = new ArrayList<Room>();
		departments = new ArrayList<Department>();
		dropinSchedule = new Schedule();
	}
	
	public void addRoom(Room newRoom) {
		if (!rooms.contains(newRoom))
			rooms.add(newRoom);
		else
			System.out.println("Room Already exist.");
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void addTutor(Tutor newTutor) {
		if (!tutors.contains(newTutor))
			tutors.add(newTutor);
		else
			System.out.println("Tutor already exist.");
	}
	
	public ArrayList<Tutor> getTutors() {
		return tutors;
	}
	
	public boolean addDropinSlot(Slot newSlot) {
		if (!dropinSchedule.getSlots().contains(newSlot)) {
			dropinSchedule.addSlot(newSlot);
			return true;
		}
		else {
			System.out.println("Slot already exist.");
			return false;
		}
	}
	
	public Schedule getDropinSchedule() {
		return dropinSchedule;
	}
	
	public void addDepartment(Department newDepartment) {
		if (!departments.contains(newDepartment))
			departments.add(newDepartment);
		else
			System.out.println("Department already exist.");
	}
	
	public ArrayList<Department> getDepartments() {
		return departments;
	}

	public boolean removeDropinSlot(Slot newSlot) {
		if (!dropinSchedule.getSlots().contains(newSlot)) {
			return false;
		} 
		else {
			dropinSchedule.removeSlot(newSlot);
			return true;
		}
	}
}
