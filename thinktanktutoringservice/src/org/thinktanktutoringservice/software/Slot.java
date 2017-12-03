package org.thinktanktutoringservice.software;
import org.thinktanktutoringservice.hardware.*;
import java.io.Serializable;
import org.thinktanktutoringservice.people.*;
public abstract class Slot implements Serializable {
	
	protected String time;
	protected Room room;
	
	public Slot() {
		time = null;
		room = null;
	}
	
	public Slot(String time, Room room) {
		this.time = time;
		this.room = room;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
}
