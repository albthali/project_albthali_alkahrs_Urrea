package org.thinktanktutoringservice.people;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.thinktanktutoringservice.software.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class Admin extends Profile implements Serializable {
	private int iD;
	private MasterSchedule masterschedule;
	public MasterSchedule getMasterschedule() {
		return masterschedule;
	}

	public void setMasterschedule(MasterSchedule masterschedule) {
		this.masterschedule = masterschedule;
	}

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
	public static void saveData(Admin e) {
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;
		try {
			fileOut = new FileOutputStream("admin.ser");
			objOut = new ObjectOutputStream(fileOut); 
			objOut.writeObject(e);

			
			objOut.close();
			fileOut.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	public static Admin loadData() {
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		Admin e = null;
		try {
			fileIn = new FileInputStream("admin.ser");
			objIn = new ObjectInputStream(fileIn);
			e = (Admin)objIn.readObject();
			//System.out.println("		Post Serialization:");
			objIn.close();
			fileIn.close();
			
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		catch (ClassNotFoundException  s) {
			s.printStackTrace();
		}
		return e;
	}
	
}
