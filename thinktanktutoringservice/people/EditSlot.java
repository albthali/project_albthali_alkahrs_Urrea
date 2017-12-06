package org.thinktanktutoringservice.people;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.thinktanktutoringservice.hardware.Course;
import org.thinktanktutoringservice.hardware.Room;
import org.thinktanktutoringservice.software.DropinSlot;
import org.thinktanktutoringservice.software.MasterSchedule;
import org.thinktanktutoringservice.software.Slot;
import org.thinktanktutoringservice.software.TutorSlot;

public class EditSlot extends JFrame{
	
	
	private MasterSchedule masterSchedule;
	private DropinSlot slot;
	private JButton okButton;       // Performs calculation
	private JButton cancelButton;       // Performs calculation
	private JButton addCourseButton;
	private JButton removeCourseButton;
	private JLabel buildingLabel;
	private JTextField buildingName;
	private JLabel roomNumberLabel;
	private JTextField roomNumber;
	private JLabel dateLabel;
	private JTextField dateTime;
	private JLabel startTimeLabel;
	private JTextField startTime;
	private JLabel dayLabel;
	private JTextField dayTime;
	private JLabel endTimeLabel;
	private JTextField endTime;
	private JTextArea slotInfo;
	private AdminGUI adminGUI;
	
	public EditSlot(DropinSlot s, MasterSchedule mSchedule, AdminGUI admingui) {
		  super("Edit Slot");
		  slot = s;
		  masterSchedule = mSchedule;
		  adminGUI = admingui;
	      setSize(400, 420);
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setResizable(false);
	      setLayout(new BorderLayout());
	      buildPanel();
	      setVisible(true);
	      getRootPane().setDefaultButton(okButton);
	   }

	   /**
	    *  The buildPanel method adds a label, text field, and
	    *  a button to a panel.
	    */

	   private void buildPanel()
	   {
	      
	     add(textAreaPanel(), BorderLayout.NORTH);
	      add(labelPanel(), BorderLayout.WEST);
	      add(textFieldPanel(), BorderLayout.EAST);
	      add(buttonPanel(), BorderLayout.SOUTH);


	   }
	   
	   public JPanel labelPanel() 
	   {
			buildingLabel = new JLabel("Bulding Name:");
			roomNumberLabel = new JLabel("Room #:");
			dateLabel = new JLabel("Date:");
			dayLabel = new JLabel("Day:");
			startTimeLabel = new JLabel("Starting Time:");
			endTimeLabel = new JLabel("End Time:");
			JPanel labelPanel = new JPanel(new GridLayout(6, 1));
			labelPanel.add(buildingLabel);
			labelPanel.add(roomNumberLabel);
			labelPanel.add(dateLabel);
			labelPanel.add(dayLabel);
			labelPanel.add(startTimeLabel);
			labelPanel.add(endTimeLabel);
			
			return labelPanel;
		}
	   
	   public JPanel textAreaPanel() {
		   slotInfo = new JTextArea(slotInfo(), 10, 30);
		   slotInfo.setEditable(false);
		   JPanel textAreaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		   JScrollPane scrollPane = new JScrollPane(slotInfo);
		   textAreaPanel.add(scrollPane);
		   
		   return textAreaPanel;
	   }
	public JPanel textFieldPanel() {
		buildingName = new JTextField(10);
	    roomNumber = new JTextField(10);
	    dateTime = new JTextField(10);
	    dayTime = new JTextField(10);
	    startTime = new JTextField(10);
	    endTime = new JTextField(10);
		
		JPanel textFieldPanel = new JPanel(new GridLayout(6, 1));
		
		textFieldPanel.add(buildingName);
		textFieldPanel.add(roomNumber);
		textFieldPanel.add(dateTime);
		textFieldPanel.add(dayTime);
		textFieldPanel.add(startTime);
		textFieldPanel.add(endTime);
		return textFieldPanel;
		
	}
	
	public JPanel buttonPanel() {
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		addCourseButton = new JButton("Add Course");
		removeCourseButton = new JButton("Remove Course");
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		okButton.addActionListener(new okButtonListener());
		addCourseButton.addActionListener(new addCourseButtonListener());
		removeCourseButton.addActionListener(new removeCourseButtonListener());
		cancelButton.addActionListener(new cancelButtonListener());
		buttonPanel.add(okButton);
		buttonPanel.add(addCourseButton);
		buttonPanel.add(removeCourseButton);
		buttonPanel.add(cancelButton);
		return buttonPanel;
	}
	
	private class cancelButtonListener implements ActionListener
	{
	   public void actionPerformed(ActionEvent e) {
		   adminGUI.setEnabled(true);
		   dispose();
	   }
	   
	}
	private class addCourseButtonListener implements ActionListener
	{
	   public void actionPerformed(ActionEvent e) {
		   AdminAddCourseToSlot addCourseToSlot = new AdminAddCourseToSlot(slot, masterSchedule, adminGUI);
		   dispose();
	   }
	   
	}
	private class removeCourseButtonListener implements ActionListener
	{
	   public void actionPerformed(ActionEvent e) {
		   AdminRemoveCourseFromSlot adminRemoveCourseFromSlot = new AdminRemoveCourseFromSlot(slot, adminGUI);
		   dispose();
	   }
	   
	}
	private class okButtonListener implements ActionListener
	{
	   public void actionPerformed(ActionEvent e)
	   {
	 	  
	      String building = buildingName.getText().trim();
	      String roomNum = roomNumber.getText().trim();
	      String date = dateTime.getText().trim();
	      String day = dayTime.getText().trim();
	      String start = startTime.getText().trim();
	      String end = endTime.getText().trim();

	      
	      if(building.trim().equals("") || roomNum.trim().equals("") || date.trim().equals("")  || 
	     		 start.trim().equals("") || end.trim().equals(""))
	      {
	     	 
	     	 JOptionPane.showMessageDialog(null,
						"Please enter all fields",
						"Error ",
						JOptionPane.ERROR_MESSAGE);
	     	 return;
	      }
	      Room room = new Room(building, Integer.valueOf(roomNum));
	      Slot slot2 = new DropinSlot(date, day, start, end, room);
		 boolean ssd = false;
			for(Slot b : masterSchedule.getDropinSchedule().getSlots()) {
				if(Slot.checkconflict(b, slot2)) ssd = true;
			}
					if (ssd == true)JOptionPane.showMessageDialog(null, "Time Conflict", "Error editing slot", JOptionPane.PLAIN_MESSAGE);
					else {
						masterSchedule.getDropinSchedule().removeSlot(slot);
						masterSchedule.getDropinSchedule().addSlot(slot2);
						adminGUI.setEnabled(true);
						adminGUI.refresh();
						dispose();
					}
	   }
	}
	
	public String slotInfo() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		PrintStream old = System.out;
		System.setOut(printStream);
		System.out.println("Courses in this slot:");
		for (Course course : slot.getCourses()) {
			System.out.println(course.getDepartment().getName() + course.getNumber() + " Taught by :");
			for (Tutor tutor : course.getTutors()) {
				System.out.println("\t"+ tutor.getName());
			}
		}
		String info = outputStream.toString();
		System.out.flush();
		System.setOut(old);
		
		return info;
	}
}
