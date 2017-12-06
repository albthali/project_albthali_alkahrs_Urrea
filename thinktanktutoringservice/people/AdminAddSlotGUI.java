package org.thinktanktutoringservice.people;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.thinktanktutoringservice.hardware.Room;
import org.thinktanktutoringservice.software.DropinSlot;
import org.thinktanktutoringservice.software.MasterSchedule;
import org.thinktanktutoringservice.software.Slot;



public class AdminAddSlotGUI extends JFrame{

	               // A panel container
	   private JButton okButton;       // Performs calculation
	   private JButton cancelButton;       // Performs calculation
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
	   private MasterSchedule masterSchedule;
	   private String action;
	   private AdminGUI adminGUI;
	   
	   /**
	    *  Constructor
	    */

	   public AdminAddSlotGUI(MasterSchedule masterSchedule, AdminGUI admingui)
	   {
	      // Call the JFrame constructor.
		  super("Add Slot");
	      this.masterSchedule = masterSchedule;
	      adminGUI = admingui;
	      // Set the size of the window.
	      setSize(300, 220);

	      // Specify what happens when the close
	      // button is clicked.
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setResizable(false);
	      setLayout(new BorderLayout());
	      
	      // Build the panel and add it to the frame.
	      buildPanel();

	      
	      // Display the window.
	      setVisible(true);
	      getRootPane().setDefaultButton(okButton);
	      adminGUI.setEnabled(false);
	   }

	   /**
	    *  The buildPanel method adds a label, text field, and
	    *  a button to a panel.
	    */

	   private void buildPanel()
	   {
	      
	     
	      add(labelPanel(), BorderLayout.WEST);
	      add(textFieldPanel(), BorderLayout.EAST);
	      add(buttonPanel(), BorderLayout.SOUTH);


	   }
	   
	   public JPanel labelPanel() {
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
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			okButton.addActionListener(new okButtonListener());
			cancelButton.addActionListener(new cancelButtonListener());
			buttonPanel.add(okButton);
			buttonPanel.add(cancelButton);
			return buttonPanel;
		}

	   /**
	    *  Private inner class that handles the event when
	    *  the user clicks the calculate button.
	    */
	   private class cancelButtonListener implements ActionListener
	   {
		   public void actionPerformed(ActionEvent e) {
			   adminGUI.setEnabled(true);
			   dispose();
		   }
		   
	   }
	   
	   
	   private class okButtonListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  
	         String building = buildingName.getText();
	         String roomNum = roomNumber.getText();
	         String date = dateTime.getText();
	         String day = dayTime.getText();
	         String start = startTime.getText();
	         String end = endTime.getText();

	         
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
	         DropinSlot slot = new DropinSlot(date, day, start, end, room);
	         boolean ssd = false;
				for(Slot b : masterSchedule.getDropinSchedule().getSlots()) {
					if(Slot.checkconflict(b, slot)) ssd = true;
				}
						if (ssd == true)JOptionPane.showMessageDialog(null, "Time Conflict", "Error editing slot", JOptionPane.PLAIN_MESSAGE);
						else {
							masterSchedule.addDropinSlot(slot);
							adminGUI.setEnabled(true);
							adminGUI.refresh();
							dispose();
						}
		   }	   }
}
