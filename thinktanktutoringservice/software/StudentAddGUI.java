package org.thinktanktutoringservice.software;

import javax.swing.*;

import org.thinktanktutoringservice.hardware.Course;
import org.thinktanktutoringservice.hardware.Department;
import org.thinktanktutoringservice.people.Student;

import java.awt.TextArea;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;


public class StudentAddGUI extends JFrame
{
   private JPanel panel;             // A panel container
   private JButton okButton;       // Performs calculation
   private JButton cancelButton;       // Performs calculation
   private final int WINDOW_WIDTH = 250;  // Window width
   private final int WINDOW_HEIGHT = 220; // Window height
   private JLabel departmentMessage;
   private JTextField departmentName;
   private JLabel courseMessage;
   private JTextField courseNumber;   
   
   private MasterSchedule masterSchedule;
   private Student student;

   private String action;
   
   /**
    *  Constructor
    */

   public StudentAddGUI(Student student,MasterSchedule masterSchedule, String action)
   {
      // Call the JFrame constructor.
	  super( action + " Course");
      this.masterSchedule = masterSchedule;
      this.action = new String(action);
      this.student = new Student();
      // Set the size of the window.
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

   /**
    *  The buildPanel method adds a label, text field, and
    *  a button to a panel.
    */

   private void buildPanel()
   {
	 
      departmentMessage = new JLabel("Department: ");
      departmentName = new JTextField(10);
      // Course
      courseMessage = new JLabel("Course #:");
      courseNumber = new JTextField(10);
      
      // Buttons
      okButton = new JButton("OK");
      cancelButton = new JButton("Cancel");

      // Add an action listener to the button.
      okButton.addActionListener(new okButtonListener());
      cancelButton.addActionListener(new cancelButtonListener());

      // Create a panel to hold the components.
      panel = new JPanel();
      
      // Add the label, text field, and button to the panel.
      
      /////// Need to align these!!!!!!
     
      panel.add(departmentMessage);
      panel.add(departmentName);
      panel.add(courseMessage);
      panel.add(courseNumber);
      panel.add(okButton);
      panel.add(cancelButton);


   }

   /**
    *  Private inner class that handles the event when
    *  the user clicks the calculate button.
    */
   private class cancelButtonListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e) {
		   dispose();
	   }
	   
   }
   
   private class okButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //String student;   // To hold text entered
         String department;
         String courseNum; // To hold miles

         //student = studentName.getText();
         department = departmentName.getText();
         courseNum = courseNumber.getText();
  
         if(department.trim().equals("")| courseNum.trim().equals(""))
         {
        	 
        	 JOptionPane.showMessageDialog(null,
						"Please enter all fields",
						"Error ",
						JOptionPane.ERROR_MESSAGE);
        	 return;
         }
         // Get the number of kilometers entered in the
         // text field. The input is a string.
 
         if(courseNum!= null && department!=null)
			{
				if(courseNum.trim().equals(""))
				{
					JOptionPane.showMessageDialog(null,
												"Please enter correct student name",
												"Error student doesn't exist",
												JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					int x =contains(student,department,courseNum,action);
					if(x==-1)
					{
						JOptionPane.showMessageDialog(null,
													"Department  \""+department+"\" doesn't exist.",
													"Error ",
													JOptionPane.PLAIN_MESSAGE);
					}
					else if(x==-3) 
					{
						JOptionPane.showMessageDialog(null,
								"Course  \""+courseNumber+"\" doesn't exist.",
								"Error ",
								JOptionPane.PLAIN_MESSAGE);
						
					}
					else {
						return;
					}
				}
			}
        		 
        		 
        		 
        		 
        
      }
   }
////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////
   //////////////////////////// not working/////////////////////////////////////////////////
public int contains(Student student, String depart, String courseNumber, String add)
{
	int d = 0;
	int c= 0;
	Course course = new Course();
	Department department = new Department();
	
	// check if department exists
	for(Department dep : masterSchedule.getDepartments()){
		if(dep.getName().equals(depart)) {
			d = 1;
			department =dep;
			break;
		}
	}
	if(d==0) return -1;	
	
	// check if course exists
	for(Department dep : masterSchedule.getDepartments()){
		for(Course cor: dep.getCourses())
		{
			if(cor.getNumber()==Integer.parseInt(courseNumber)) {
				c=1;
				course = cor;
				break;
			}
			
		}
	}
	if (c==0) return -3;
	
	if(d*c==1){
		if(add.equals("Add")){
			handleAddCourse(student,course,department);
			return 0;
		}
		else{
			handleDropCourse(student,course,department);
			return 0;
		}
		
	}
	return 0;
}

private void handleDropCourse(Student student, Course course, Department department) {
	// Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print some output: goes to your special stream
   // System.out.println("Foofoofoo!");
    // Put things back
    
    // Show what happened
    //System.out.println("Here: " + baos.toString());
      
	student.dropCourse(course);
	System.out.flush();
    System.setOut(old);
	
    String output = new String(baos.toByteArray());
    if(!output.trim().equals(""))
	JOptionPane.showMessageDialog(null,output,
			"Error dropping student from course ",
			JOptionPane.PLAIN_MESSAGE, null);
    else 
    	JOptionPane.showMessageDialog(null,"Succesfully dropped "+student.getName() +" from "+department.getName()+""+course.getNumber(),"Success"
    			,
    			JOptionPane.PLAIN_MESSAGE, null);
	return;

	
}

public void handleAddCourse(Student student, Course course, Department department) {
	
	
	// Create a stream to hold the output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // IMPORTANT: Save the old System.out!
    PrintStream old = System.out;
    // Tell Java to use your special stream
    System.setOut(ps);
    // Print some output: goes to your special stream
   // System.out.println("Foofoofoo!");
    // Put things back
    
    // Show what happened
    //System.out.println("Here: " + baos.toString());
      
	student.addCourse(course);
	System.out.flush();
    System.setOut(old);
	
    String output = new String(baos.toByteArray());
    if(!output.trim().equals(""))
	JOptionPane.showMessageDialog(null,output,
			"Error adding student to course. ",
			JOptionPane.PLAIN_MESSAGE, null);
    else 
    	JOptionPane.showMessageDialog(null,"Succesfully added "+student.getName() +" to "+department.getName()+""+course.getNumber(),"Success",   			JOptionPane.PLAIN_MESSAGE, null);
	return;
	
}
}
