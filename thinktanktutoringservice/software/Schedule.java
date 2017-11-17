package org.thinktanktutoringservice.software;

import java.util.ArrayList;

public class Schedule {
	private ArrayList<Slot>slots;
	
	public Schedule() {
		slots = new ArrayList<Slot>();
	}
	
	public void addSlot(Slot newSlot) {
		if (!slots.contains(newSlot))
			slots.add(newSlot);
		else 
			System.out.println("Slot already exist.");
	}
	
	public ArrayList<Slot> getSlots() {
		return slots;
	}

	public void removeSlot(Slot newSlot) {
		if (!slots.contains(newSlot))
			System.out.println("Slot does not exist");
		else
			slots.remove(newSlot);
	}
}
