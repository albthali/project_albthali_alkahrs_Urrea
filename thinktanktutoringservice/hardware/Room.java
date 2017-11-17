package org.thinktanktutoringservice.hardware;

import org.thinktanktutoringservice.software.Schedule;
import org.thinktanktutoringservice.software.Slot;

public class Room {

	private String building;
	private Integer number;
	private Schedule schedule;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room() {
		this.building = "";
		this.number = 0;
		this.schedule = null;
		this.name = "";
	}
	
	public Room(String building, Integer number) {
		this.setBuilding(building);
		this.setNumber(number);
		addSlot(null);
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void addSlot(Slot newSlot) {
		if (!schedule.getSlots().contains(newSlot))
			schedule.addSlot(newSlot);
		else
			System.out.println("Slot already exist.");
	}
}
