package org.thinktanktutoringservice.software;

import org.thinktanktutoringservice.hardware.Room;
import org.thinktanktutoringservice.people.Student;
import org.thinktanktutoringservice.people.Tutor;
import java.io.Serializable;
public class TutorSlot extends Slot implements Serializable {

	private Tutor tutor;
	private Student student;
	
	public TutorSlot() {
		tutor = null;
		student = null;
	}
	
	public TutorSlot(String date,String day, String timestart, String timend , Room room, Tutor tutor) {
		super(date, day, timestart, timend ,  room);
		this.tutor = tutor;
		student = null;
	}
	
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	public Tutor getTutor() {
		return tutor;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Student getstudent() {
		return student;
	}
	public boolean isTutorslot() {
		return true;
	}

}
