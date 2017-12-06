package org.thinktanktutoringservice.software;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.thinktanktutoringservice.people.Student;

public class StudentHomeGUI extends JFrame {
	
	   private JPanel panel;             // A panel container
	   private JButton addCourseButton;       // Performs calculation
	   private JButton dropCourseButton;
	   
	   
	  // private JButton cancelButton;       // Performs calculation
	   private final int WINDOW_WIDTH = 500;  // Window width
	   private final int WINDOW_HEIGHT = 500; // Window height
	   private MasterSchedule masterSchedule; // should be public
	   private Student student;
	   //private String action; // true = add false = drop
	   
	
	public StudentHomeGUI(Student student) {
		super("Hi, "+ student.getName());
		this.student = student;
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

	      // Specify what happens when the close
	      // button is clicked.
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      // Build the panel and add it to the frame.
	      buildPanel();

	      // Add the panel to the frame's content pane.
	      add(panel);

	      // Display the window.
	      setVisible(true);
	}
	
	 private void buildPanel()
	   {
	      // Buttons
		 addCourseButton = new JButton("Add Course");
		 dropCourseButton = new JButton("Drop Course");

	      // Add an action listener to the button.
		 addCourseButton.addActionListener(new addCourseListener());
		 dropCourseButton.addActionListener(new dropCourseListener());

	      // Create a panel to hold the components.
	      panel = new JPanel();
	      
	      // Add the label, text field, and button to the panel.
	      
	      /////// Need to align these!!!!!!
	    
	      panel.add(addCourseButton);
	      panel.add(dropCourseButton);

	   }
	 
	 private class addCourseListener implements ActionListener
	 {//
	      public void actionPerformed(ActionEvent e)
	      {
				StudentAddGUI stugui = new StudentAddGUI(student,masterSchedule,"Add");    

	      }
	  }
	 
	 private class dropCourseListener implements ActionListener
	 {//
	      public void actionPerformed(ActionEvent e)
	      {
				StudentAddGUI stugui = new StudentAddGUI(student,masterSchedule,"Drop");    

	      }
	  }
	
	
	
	
	
	
}
