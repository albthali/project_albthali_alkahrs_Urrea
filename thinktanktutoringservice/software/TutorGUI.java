package org.thinktanktutoringservice.software;
import org.thinktanktutoringservice.people.*;
import org.thinktanktutoringservice.hardware.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class TutorGUI extends JFrame{
	Admin admin;
	Tutor tutor;
	JButton Home;
	JButton Schedule;
	JButton Availablitiy;
	JButton addappointment;
	JButton removeappointment;
	
	public TutorGUI(Admin a, Tutor t ) {
		super("Hello  " + t.getName());
		admin = a;
		tutor = t;
		setSize(1000, 1000);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		TutorGuiBuild();
		setVisible(true);
	}
	public void TutorGuiBuild() {
		Home = new JButton("Home");
		Schedule = new JButton("Schedule");
		Availablitiy = new JButton("Availablitiy");
		addappointment = new JButton("Add Appointment");
		removeappointment = new JButton("remove Appointment");
		JPanel legends = new JPanel(new GridLayout(3,0));
		Home.addActionListener(new buttonlisten());
		Schedule.addActionListener(new buttonlisten());
		Availablitiy.addActionListener(new buttonlisten());
		addappointment.addActionListener(new buttonlisten());
		removeappointment.addActionListener(new buttonlisten());
		legends.add(Home);
		legends.add(Schedule);
		legends.add(Availablitiy);
		add(legends, BorderLayout.WEST);
		//ScheduleGUI();
		
		
		
	}
	public void HomeGUI() {
		
	}
	public void ScheduleGUI() {
		JPanel panels = new JPanel(new GridLayout(2,0));
		int rows = tutor.getSchedule().Tutorslots();
		JPanel Table = new JPanel(new GridLayout(rows + 1,4));
//		Table.add(new JLabel("Course "));
		Table.add(new JLabel("Student"));
		Table.add(new JLabel("Date"));
		Table.add(new JLabel("Time"));
		Table.add(new JLabel("Location"));
		ArrayList<Slot> tsch = tutor.getSchedule().Tutorslotslist();
		for(int i = 0 ; i < rows ; i++) {
			if(tsch.get(i).getstudent() == null) Table.add(new JLabel("no student"));
			else Table.add(new JLabel(tsch.get(i).getstudent().getName()));
			Table.add(new JLabel(tsch.get(i).getDate()));
			Table.add(new JLabel(tsch.get(i).getTimestart() + "-" + tsch.get(i).getTimend()));
			Table.add(new JLabel(tsch.get(i).getRoom().getBuilding()));
			
		}
		panels.add(Table);
		JPanel addorremove = new JPanel(new GridLayout(0,2));
		addorremove.add(addappointment);
		addorremove.add(removeappointment);
		panels.add(addorremove);
		this.add(panels, BorderLayout.CENTER);
		this.revalidate();
	}
	private class buttonlisten implements ActionListener{
		public void actionPerformed(ActionEvent e) 
		{
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(Home))
			{
				Homepressed();	
			}
			else if(source.equals(Schedule))
			{
				Schedulepressed();
			}
			else if (source.equals(Availablitiy)) {
				Availablitiypressed();
			}
			else if((source.equals(addappointment))) {
				addppointmenthandle();
			}
			else if((source.equals(removeappointment))) removeappointmenthandle();
			
			
}
		public void Homepressed() {
			
		}
		public void 	Schedulepressed() {
			ScheduleGUI();
			
		}
		public void Availablitiypressed() {
			
		}
		public void addppointmenthandle() {
			addappointment temp = new addappointment();
		}
		public void removeappointmenthandle() {
			removeappointment temp = new removeappointment();
		}
}
	private  class addappointment extends JFrame{

		JButton oks;
		JButton Cancel;
		JTextField date;
		JTextField times;
		JTextField timee;
		JTextField building;
		JTextField roomnumber;
		
		addappointment(){
			
			super("Add Appointment");
		
		setSize(350, 150);
		setLayout(new BorderLayout());

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		date = new JTextField(10);
		times = new JTextField(10);
		timee = new JTextField(10);
		building = new JTextField(10);
		roomnumber = new JTextField(10);
		 oks = new JButton("OK");
		  Cancel = new JButton("Cancel");
		  addappointmentGUI();	
		setVisible(true);}
	public void addappointmentGUI() {
		 JPanel legends = new JPanel(new GridLayout(5,1));
		 JPanel studentnamerino =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 studentnamerino.add(new JLabel("date:         "));
		 legends.add(studentnamerino);
		 JPanel departs =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 departs.add(new JLabel("  starts at:     "));
		 legends.add(departs);
		
		 JPanel courses =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 courses.add(new JLabel("    ends at :          "));
		 legends.add(courses);
		 JPanel buildingf =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 buildingf.add(new JLabel("    Building :          "));
		 legends.add(buildingf);
		 JPanel roomnumbers =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 roomnumbers.add(new JLabel("    Room# :          "));
		 legends.add(roomnumbers);
		 JPanel texts = new JPanel(new GridLayout(5,1));
		 texts.add(date);
		 texts.add(times);
		 texts.add(timee);
		 texts.add(building);
		 texts.add(roomnumber);
		 JPanel buttons = new JPanel( new FlowLayout(FlowLayout.CENTER));
		 oks.addActionListener(new buttonlistener());
		 Cancel.addActionListener(new buttonlistener());
		 
		buttons.add(oks);
		buttons.add(Cancel);
		 add(legends, BorderLayout.WEST);
		 add(texts, BorderLayout.EAST);
		 add(buttons, BorderLayout.SOUTH);
		 
		 
		 
		 
		 
	 }
	 private class buttonlistener implements ActionListener{
			public void actionPerformed(ActionEvent e) 
			{
				JButton source = (JButton)(e.getSource());
				
				if(source.equals(oks))
				{
					okpressed();	
				}
				else if(source.equals(Cancel))
				{
					cancelpressed();
				}
				
				
	}
			public void okpressed() {
				
				if(date.getText().trim().length() == 0 || times.getText().trim().length() == 0 || timee.getText().trim().length() == 0
						|| building.getText().trim().length() == 0 || roomnumber.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "All fielda are required", "Fields missing", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					Room tempr = admin.getMasterschedule().getroom(building.getText().trim(), Integer.parseInt(roomnumber.getText().trim()));
					
					
					TutorSlot temps = new TutorSlot(date.getText().trim(),"", times.getText().trim(), timee.getText().trim() , tempr, tutor);
					boolean ssd = false;
					for(Slot b : tutor.getSchedule().getSlots()) {
						if(Slot.checkconflict(b, temps)) ssd = true;
					}
							if(tempr == null) JOptionPane.showMessageDialog(null, "Room doesn't exist", "Error adding appointment", JOptionPane.PLAIN_MESSAGE);
							else if (ssd == true)JOptionPane.showMessageDialog(null, "Time Conflict", "Error adding appointment", JOptionPane.PLAIN_MESSAGE);
							else {
								tutor.addSlot(temps);
								dispose();
								 ScheduleGUI();
							}
				}

			}
			public void cancelpressed() {
				dispose();
			}
	 
	 
	 
	 }}
	private  class removeappointment extends JFrame{

		JButton oks;
		JButton Cancel;
		JTextField date;
		JTextField times;
		JTextField timee;
		JTextField building;
		JTextField roomnumber;
		
		removeappointment(){
			
			super("remove Appointment");
		
		setSize(350, 150);
		setLayout(new BorderLayout());

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		date = new JTextField(10);
		times = new JTextField(10);
		timee = new JTextField(10);
		building = new JTextField(10);
		roomnumber = new JTextField(10);
		 oks = new JButton("OK");
		  Cancel = new JButton("Cancel");
		  addappointmentGUI();	
		setVisible(true);}
	public void addappointmentGUI() {
		 JPanel legends = new JPanel(new GridLayout(3,1));
		 JPanel studentnamerino =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 studentnamerino.add(new JLabel("date:         "));
		 legends.add(studentnamerino);
		 JPanel departs =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 departs.add(new JLabel("  starts at:     "));
		 legends.add(departs);
		
		 JPanel courses =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 courses.add(new JLabel("    ends at :          "));
		 legends.add(courses);
		
		 JPanel texts = new JPanel(new GridLayout(3,1));
		 texts.add(date);
		 texts.add(times);
		 texts.add(timee);
		
		 JPanel buttons = new JPanel( new FlowLayout(FlowLayout.CENTER));
		 oks.addActionListener(new buttonlisteners());
		 Cancel.addActionListener(new buttonlisteners());
		 
		buttons.add(oks);
		buttons.add(Cancel);
		 add(legends, BorderLayout.WEST);
		 add(texts, BorderLayout.EAST);
		 add(buttons, BorderLayout.SOUTH);
		 
		 
		 
		 
		 
	 }
	 private class buttonlisteners implements ActionListener{
			public void actionPerformed(ActionEvent e) 
			{
				JButton source = (JButton)(e.getSource());
				
				if(source.equals(oks))
				{
					okpressed();	
				}
				else if(source.equals(Cancel))
				{
					cancelpressed();
				}
				
				
	}
			public void okpressed() {
				
				if(date.getText().trim().length() == 0 || times.getText().trim().length() == 0 || timee.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "All fielda are required", "Fields missing", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					
					boolean ssd = false;
					Slot temps = tutor.getSchedule().findslot(date.getText().trim(), times.getText().trim(), timee.getText().trim());
					if(temps == null) ssd = true;
					
							
							 if (ssd == true)JOptionPane.showMessageDialog(null, "Appointment Selected doesnot exist", "Error removing appointment", JOptionPane.PLAIN_MESSAGE);
							else {
								tutor.getSchedule().getSlots().remove(temps);
								dispose();
								 ScheduleGUI();
							}
				}

			}
			public void cancelpressed() {
				dispose();
			}
	 
	 
	 
	 }}
	
}
