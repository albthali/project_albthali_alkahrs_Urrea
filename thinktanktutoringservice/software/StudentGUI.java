package org.thinktanktutoringservice.software;

import java.awt.BorderLayout;
import org.thinktanktutoringservice.people.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.thinktanktutoringservice.people.Student;
import org.thinktanktutoringservice.hardware.*;
import java.util.*;
public class StudentGUI extends JFrame {
	
	   private JPanel panel;    // A panel container
	   private Admin admins;
	   private MasterSchedule MS;
	
	  
	   
	  // private JButton cancelButton;       // Performs calculation
	   private final int WINDOW_WIDTH = 400;  // Window width
	   private final int WINDOW_HEIGHT = 400; // Window height
	   private MasterSchedule masterSchedule; // should be public
	   private Student student;
	   //private String action; // true = add false = drop
	   
	
	public StudentGUI(Student student, Admin admin) {
		super("Hi, "+ student.getName());
		this.student = student;
		this.admins = admin;
		this.MS = admin.getMasterschedule();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

	      // Specify what happens when the close
	      // button is clicked.
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     JTabbedPane tabbedPane = new JTabbedPane();

	      // Build the panel and add it to the frame.
	      //buildPanel();

	      // Add the panel to the frame's content pane.
	      tabbedPane.addTab("Home", HomePanel());
	      tabbedPane.addTab("Drop-in Schedule", SchedulePanel());
	      tabbedPane.addTab("Apoinments", AppointmentPanel());
	      add(tabbedPane);
	      setVisible(true);
	      // Display the window.
	}
	
	 private JPanel HomePanel()
	   {
		 
		 JButton addCourseButton;       // Performs calculation
		 JButton dropCourseButton;
		   
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
	      JPanel dummyPanel = new JPanel();
	      dummyPanel.add(addCourseButton);
	      dummyPanel.add(dropCourseButton);
	      dummyPanel.setVisible(true);
	      
	      // for pic and student information
	      JPanel dummyPanelpic = new JPanel();
	      ImageIcon imageIcon = new ImageIcon("/Users/x/Desktop/SelfPortrait.jpg"); // load the image to a imageIcon
	      Image image = imageIcon.getImage(); // transform it 
	      Image newimg = image.getScaledInstance(140, 145,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	      imageIcon = new ImageIcon(newimg);  // transform it back
	      dummyPanelpic.add(new JLabel(imageIcon));
	      JTextArea ta = new JTextArea(9,11);
	      String string = new String();
	      string = student.getName()+"\n"+student.getEmail()+"\nMajor: "+student.getDepartment().getName();
	      
	      ta.setText(string);
	      dummyPanelpic.add(ta);
	      
	      
	      panel.setLayout(new BorderLayout());
	      panel.add(dummyPanelpic,BorderLayout.CENTER);
	      panel.add(dummyPanel,BorderLayout.SOUTH);
	      panel.setVisible(true);
	      return panel;

	   }
	 
	 private JPanel SchedulePanel()
	 {
		
		 panel = new JPanel();
		//String datas[][];
		ArrayList<Slot> ds = new ArrayList<Slot>();
		Slot d ;
		 for(int i = 0; i <MS.dropinSchedule.getSlots().size(); i++) {
			 d = MS.dropinSchedule.getSlots().get(i);
			 for(Course c : d.getcourse()) {
				 if(this.student.getCourses().contains(c)) {
					 ds.add(d);
					 break;
//					 datas.add(Integer.toString(t.getiD()));
//					 datas.add(t.getName());
//					 datas.add(String.valueOf(t.getPayrate()));
					 
				 }
			 }
		 }
		 String datas[][] = new String[ds.size()][4];
		 for(int i = 0; i <ds.size(); i ++ ) {
			 datas[i][0] = ds.get(i).getRoom().getBuilding();
			 datas[i][1] = Integer.toString(ds.get(i).getRoom().getNumber());
			 datas[i][2] = ds.get(i).getDate();
			 datas[i][3] = ds.get(i).getTimestart() + "-" + ds.get(i).getTimend();
		 }
		 String data[][]={ {"101","Amit","670000"},    
                 {"102","Jai","780000"},    
                 {"101","Sachin","700000"}};    
		 String column[]={"Building","Room#","date", "time"};         
		 JTable jt=new JTable(datas,column);    
		 jt.setBounds(50,50,400,400);          
		 JScrollPane sp=new JScrollPane(jt);
		 panel.add(jt);
		 panel.setVisible(true);
		
		return panel; 
	 }
	 
	private JPanel AppointmentPanel() {
		 panel = new JPanel();
			//String datas[][];
			ArrayList<Tutor> tlist = new ArrayList<Tutor>();
			Tutor t = new Tutor();
			 for(int i = 0; i <MS.tutors.size(); i++) {
				 t = MS.tutors.get(i);
				 for(Course c : student.getCourses()) {
					 if(t.getProficiency().contains(c)) {
						 tlist.add(t);
						 break;
//						 datas.add(Integer.toString(t.getiD()));
//						 datas.add(t.getName());
//						 datas.add(String.valueOf(t.getPayrate()));
						 
					 }
				 }
			 }
			 String datas[][] = new String[tlist.size()][3];
			 for(int i = 0; i <tlist.size(); i ++ ) {
				 datas[i][0] = Integer.toString(tlist.get(i).getiD());
				 datas[i][1] = tlist.get(i).getName();
				 datas[i][2] = String.valueOf(tlist.get(i).getPayrate());
			 }
		 String column[]={"ID","NAME","SALARY"};         
		 JTable jt=new JTable(datas,column);    
		 jt.setBounds(50,50,400,400);          
		 JScrollPane sp=new JScrollPane(jt);
		 panel.add(jt);
		 panel.setVisible(true);
		
		return panel; 
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


