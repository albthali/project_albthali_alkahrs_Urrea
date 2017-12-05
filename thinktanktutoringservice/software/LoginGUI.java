package org.thinktanktutoringservice.software;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;

import javax.swing.*;

import org.thinktanktutoringservice.people.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.thinktanktutoringservice.people.*;
public class LoginGUI extends JFrame
{
   private JPanel panel;             // A panel container
   private JButton loginButton;       // Performs calculation
   private final int WINDOW_WIDTH = 500;  // Window width
   private final int WINDOW_HEIGHT = 220; // Window height
   private JLabel NetIDMessage;
   private JTextField NetID;
   private JLabel passwordMessage;
   private JTextField password;
   private Student student;
   private Admin administrator;
   private MasterSchedule masterSchedule;
   
  
   public LoginGUI(Admin admin)
   {
	  super( "Welcome to Think Tank");
	  this.administrator = admin;
	  this.masterSchedule = admin.getMasterschedule();
	  this.student = new Student();
      // Set the size of the window.
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      // Specify what happens when the close button is clicked.
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
	  // NetID
      NetIDMessage = new JLabel("NetID: ");
      NetID = new JTextField(10);
      
      // Password
      passwordMessage = new JLabel("Password: ");
      password= new JTextField(10);
  
      // Buttons
      loginButton = new JButton("LOGIN");

      // Add an action listener to the button.
      loginButton.addActionListener(new loginButtonListener());

      // Create a panel to hold the components.
      panel = new JPanel();
      
      // Add the label, text field, and button to the panel.
      
      /////// Need to align these!!!!!!
      panel.add(NetIDMessage);
      panel.add(NetID);
      panel.add(passwordMessage);
      panel.add(password);
      panel.add(loginButton);


   }

   /**
    *  Private inner class that handles the event when
    *  the user clicks the calculate button.
    */
  /* private class cancelButtonListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e) {
		   dispose();
	   }
	   
   }
   */
   private class loginButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String id;   // To hold text entered
         String pass;

         id = NetID.getText();
         pass = password.getText();
  
         
         
         if(id.trim().equals("")| pass.trim().equals(""))
         {
        	 
        	 JOptionPane.showMessageDialog(null,
						"Please enter all fields",
						"Error ",
						JOptionPane.ERROR_MESSAGE);
        	 return;
         }
         // Get the number of kilometers entered in the
         // text field. The input is a string.
 
         if(id!= null)
			{
				if(id.trim().equals(""))
				{
					JOptionPane.showMessageDialog(null,
												"Please enter correct student name",
												"Error student doesn't exist",
												JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					if(!containsNetID(id,pass))
					{
						JOptionPane.showMessageDialog(null,
													"The NetID or Password entered are incorrect",
													"Error ",
													JOptionPane.PLAIN_MESSAGE);
						return;
					}
				
				}
			}
        		
      }
   }
//////////////////////////////////////////////////////////////////////
   
   /// Make it check if NetID and password are correct.
public boolean containsNetID(String id, String pass)
{
	// check if NetID exists
	if(administrator.getUsername().equals(id) && administrator.checkPassword(pass)) {
		//AdminGUI adgui = new AdminGUI(administrator);
		dispose();
		return true;
	}
	for(Student stu : masterSchedule.students){
		if(stu.getUsername().equals(id) && stu.checkPassword(pass)) 
		{
			dispose();
			StudentGUI stugui = new StudentGUI(stu,administrator );   
			return true;
			}
		
	}
	for(Tutor tut: masterSchedule.tutors) {
		if(tut.getUsername().equals(id) && tut.checkPassword(pass)) 
		{
			dispose();
			TutorGUI tutgui = new TutorGUI(administrator , tut);   
			return true;
			}
	}
	return false;
}

}