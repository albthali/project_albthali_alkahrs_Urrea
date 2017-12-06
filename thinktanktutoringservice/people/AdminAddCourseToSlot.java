package org.thinktanktutoringservice.people;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.thinktanktutoringservice.hardware.Course;
import org.thinktanktutoringservice.hardware.Department;
import org.thinktanktutoringservice.hardware.Room;
import org.thinktanktutoringservice.software.DropinSlot;
import org.thinktanktutoringservice.software.MasterSchedule;
import org.thinktanktutoringservice.software.Slot;

public class AdminAddCourseToSlot extends JFrame{

	private MasterSchedule masterSchedule;
	private DropinSlot slot;
	private JLabel courseDepartmentLabel;
	private JLabel courseNumberLabel;
	private JTextField courseDepartment;
	private JTextField courseNumber;
	private JButton okButton;
	private JButton cancelButton;
	private AdminGUI adminGUI;
	public AdminAddCourseToSlot(DropinSlot s, MasterSchedule mSchedule, AdminGUI admingui) {
		super("Add Course To Slot");
		masterSchedule = mSchedule;
		adminGUI = admingui;
		  slot = s;
	      setSize(350, 150);
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setResizable(false);
	      setLayout(new BorderLayout());
	      buildPanel();
	      setVisible(true);
	      getRootPane().setDefaultButton(okButton);
	}

	public void buildPanel() {
	      add(labelPanel(), BorderLayout.WEST);
	      add(textFieldPanel(), BorderLayout.EAST);
	      add(buttonPanel(), BorderLayout.SOUTH);
	}
	
	public JPanel labelPanel() {
		courseDepartmentLabel = new JLabel("Departement Name: ");
		courseNumberLabel = new JLabel("Course #: ");
		
		JPanel labelPanel = new JPanel(new GridLayout(2, 1));
		labelPanel.add(courseDepartmentLabel);
		labelPanel.add(courseNumberLabel);
		
		return labelPanel;
	}
	
	public JPanel textFieldPanel() {
		courseDepartment = new JTextField(10);
		courseNumber = new JTextField(10);
		
		JPanel textFeildPanel = new JPanel(new GridLayout(2, 1));
		textFeildPanel.add(courseDepartment);
		textFeildPanel.add(courseNumber);
		return textFeildPanel;
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
		String department = courseDepartment.getText().trim();
	 	String number = courseNumber.getText().trim();
	 	
	 	boolean isFound = false;
	 	
	 	for (Department dep : masterSchedule.getDepartments()) {
	 		if(dep.getName().equals(department)) {
				for (Course course : dep.getCourses()) {
					if(course.getNumber().toString().equals(number)){
						slot.addCourse(course);
						isFound = true;
						adminGUI.setEnabled(true);
						dispose();
						}
					}
				}
			}
	 	if(isFound == false) {
	 		JOptionPane.showMessageDialog(null, "Course Not Found", "Error", JOptionPane.PLAIN_MESSAGE);
	 	}
	 	}
	}
	
}

