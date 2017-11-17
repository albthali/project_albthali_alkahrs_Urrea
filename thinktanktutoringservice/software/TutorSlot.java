package org.thinktanktutoringservice.software;

import org.thinktanktutoringservice.people.Student;
import org.thinktanktutoringservice.people.Tutor;

public class TutorSlot extends Slot {

	private Tutor tutor;
	private Student student;
	
	public TutorSlot() {
		tutor = null;
		student = null;
	}
	
	public TutorSlot(Tutor tutor) {
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
	
	public Student getStudent() {
		return student;
	}
}
