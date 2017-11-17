package org.thinktanktutoringservice.people;
import org.thinktanktutoringservice.software.*;
public class Admin extends Profile {
	private int iD;
	private MasterSchedule masterschedule;
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}
	Admin(){
		this.iD = 0;
		masterschedule = new MasterSchedule();
	}
	public boolean addDropinHours(Slot newslot){
		boolean check = masterschedule.addDropinSlot(newslot);
		return check;
		
	}
	public boolean removeDropinHours(Slot newslot){
		
		boolean check = masterschedule.removeDropinSlot(newslot);
		return check;
		
	}
	
}
