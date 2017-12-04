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
		JPanel legends = new JPanel(new GridLayout(3,0));
		Home.addActionListener(new buttonlisten());
		Schedule.addActionListener(new buttonlisten());
		Availablitiy.addActionListener(new buttonlisten());
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
			
			
}
		public void Homepressed() {
			
		}
		public void 	Schedulepressed() {
			ScheduleGUI();
			
		}
		public void Availablitiypressed() {
			
		}
}
}
