package org.thinktanktutoringservice.people;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.thinktanktutoringservice.hardware.Course;
import org.thinktanktutoringservice.software.DropinSlot;
import org.thinktanktutoringservice.software.MasterSchedule;
import org.thinktanktutoringservice.software.Slot;

public class AdminRemoveCourseFromSlot extends JFrame{
	DropinSlot slot;
	JComboBox<String> coursesList;
	JLabel courseListLabel;
	JButton removeButton;
	JButton cancelButton;
	AdminGUI adminGUI;
	
	public AdminRemoveCourseFromSlot(DropinSlot s, AdminGUI admingui) {
		
		 super("Remove Course From Slot");
		 slot = s;
		 adminGUI = admingui;
	      // Set the size of the window.
	      setSize(400, 140);

	      // Specify what happens when the close
	      // button is clicked.
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setResizable(false);
	      setLayout(new BorderLayout());
	      
	      // Build the panel and add it to the frame.
	      buildPanel();

	      
	      // Display the window.
	      setVisible(true);
	      getRootPane().setDefaultButton(removeButton);
		
	}
	
	private void buildPanel()
	{
	   
	  add(slotListPanel(), BorderLayout.NORTH);
	   add(buttonPanel(), BorderLayout.SOUTH);
	   


	}
		
		public JPanel slotListPanel() {
			ArrayList<String> choices = new ArrayList<String>();
			for (Course course : slot.getCourses()) {
				
				
				choices.add(course.getName() + " - " + course.getDepartment().getName() + course.getNumber().toString());
			}
			
			coursesList = new JComboBox<String>(choices.toArray(new String[0]));  
			courseListLabel = new JLabel("Select a slot: ");
			JPanel slotListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			slotListPanel.add(courseListLabel);
			slotListPanel.add(coursesList);
			
			return slotListPanel;
		}

		
		public JPanel buttonPanel() {
			removeButton = new JButton("Remove");
			cancelButton = new JButton("Cancel");
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			removeButton.addActionListener(new selectButtonListener());
			cancelButton.addActionListener(new cancelButtonListener());
			buttonPanel.add(removeButton);
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
		 private class selectButtonListener implements ActionListener
		  {
		     public void actionPerformed(ActionEvent e)
		     {
		    	 slot.dropCourse(slot.getCourses().get(coursesList.getSelectedIndex()));
		    	 adminGUI.setEnabled(true);
		    	 dispose();
		     }
		  }
		
}
