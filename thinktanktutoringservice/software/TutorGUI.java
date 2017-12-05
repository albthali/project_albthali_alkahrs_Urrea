package org.thinktanktutoringservice.software;
import org.thinktanktutoringservice.people.*;
//import org.thinktanktutoringservice.software.StudentGUI.addCourseListener;
//import org.thinktanktutoringservice.software.StudentGUI.dropCourseListener;
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
	private JPanel panel;    // A panel container
	   private Admin admins;
	   private MasterSchedule MS;
	
	  
	   
	  // private JButton cancelButton;       // Performs calculation
	   private final int WINDOW_WIDTH = 400;  // Window width
	   private final int WINDOW_HEIGHT = 400; // Window height
	   private MasterSchedule masterSchedule; // should be public
	public TutorGUI(Admin a, Tutor t ) {
		super("Hi, "+ t.getName());
		this.tutor = t;
		this.admins = a;
		this.admin = a;
		this.MS = a.getMasterschedule();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

	      // Specify what happens when the close
	      // button is clicked.
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     JTabbedPane tabbedPane = new JTabbedPane();

	      // Build the panel and add it to the frame.
	      

	      // Add the panel to the frame's content pane.
	      tabbedPane.addTab("Home", HomePanel());
	      tabbedPane.addTab("Schedule", SchedulePanel());
	     
	      add(tabbedPane);
	      setVisible(true);
	}
	 private JPanel HomePanel()
	   {
		 
		 JButton addCourseButton;       // Performs calculation
		 JButton dropCourseButton;
		   
	      // Buttons
		

	      // Create a panel to hold the components.
	      panel = new JPanel();	      
	 
	      JPanel dummyPanelpic = new JPanel();
	      ImageIcon imageIcon = new ImageIcon("/Users/x/Desktop/SelfPortrait.jpg"); // load the image to a imageIcon
	      Image image = imageIcon.getImage(); // transform it 
	      Image newimg = image.getScaledInstance(140, 145,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	      imageIcon = new ImageIcon(newimg);  // transform it back
	      dummyPanelpic.add(new JLabel(imageIcon));
	      JTextArea ta = new JTextArea(9,11);
	      String string = new String();
	      string = tutor.getName()+"\n"+tutor.getEmail();
	      
	      ta.setText(string);
	      dummyPanelpic.add(ta);
	      
	      
	      panel.setLayout(new BorderLayout());
	      panel.add(dummyPanelpic,BorderLayout.CENTER);

	      panel.setVisible(true);
	      return panel;

	   }
	 public void reset() {
		 dispose();
		 TutorGUI tts = new TutorGUI(Admin.loadData(), this.tutor );
	 }
	 private JPanel SchedulePanel()
	 {
		
		 panel = new JPanel();
		 addappointment = new JButton("Add appointment");
		 removeappointment = new JButton("Remove appointment");
		 addappointment.addActionListener(new buttonlisten());
		 removeappointment.addActionListener(new buttonlisten());
		 JPanel dummyPanel = new JPanel();
	      dummyPanel.add(addappointment);
	      dummyPanel.add(removeappointment);
	      dummyPanel.setVisible(true);
		 
	
			 String datas[][] = new String[tutor.getSchedule().getSlots().size()][5];
			 for(int i = 0; i <tutor.getSchedule().getSlots().size(); i ++ ) {
				 datas[i][0] = tutor.getSchedule().getSlots().get(i).getRoom().getBuilding();
				 datas[i][1] =String.valueOf(tutor.getSchedule().getSlots().get(i).getRoom().getNumber());
				 datas[i][2] = tutor.getSchedule().getSlots().get(i).getDate();
				 datas[i][3] = tutor.getSchedule().getSlots().get(i).getTimestart() +"-"+ tutor.getSchedule().getSlots().get(i).getTimend();
				 datas[i][3] = tutor.getSchedule().getSlots().get(i).getTimestart() +"-"+ tutor.getSchedule().getSlots().get(i).getTimend();
				 if(tutor.getSchedule().getSlots().get(i).isTutorslot()) {
					 if(tutor.getSchedule().getSlots().get(i).getstudent() == null) datas[i][4] = "Vacant";
					 else  datas[i][4] = tutor.getSchedule().getSlots().get(i).getstudent().getName();
				 }
				 else datas[i][4] = "Drop in Hour";
			 }
	      
			 String column[]={"Building","Room#","date", "time", "Student"};         
			 JTable jt=new JTable(datas,column);    
			 jt.setBounds(50,50,400,400);          
			 JScrollPane sp=new JScrollPane(jt);
			 panel.setLayout(new BorderLayout());
			 panel.add(jt, BorderLayout.CENTER);
			 panel.add(dummyPanel, BorderLayout.SOUTH);
			 panel.setVisible(true);
			
			return panel; 
	 }

	private class buttonlisten implements ActionListener{
		public void actionPerformed(ActionEvent e) 
		{
			JButton source = (JButton)(e.getSource());
			
			
			
			 if((source.equals(addappointment))) {
				addppointmenthandle();
			}
			else if((source.equals(removeappointment))) removeappointmenthandle();
			
			
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
		
		setSize(290, 230);
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
		 studentnamerino.add(new JLabel("  date:         "));
		 legends.add(studentnamerino);
		 JPanel departs =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 departs.add(new JLabel("  starts at:         "));
		 legends.add(departs);
		
		 JPanel courses =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 courses.add(new JLabel("  ends at :         "));
		 legends.add(courses);
		 JPanel buildingf =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 buildingf.add(new JLabel("  Building :         "));
		 legends.add(buildingf);
		 JPanel roomnumbers =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 roomnumbers.add(new JLabel("  Room# :         "));
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
								Admin.saveData(admin);
								
								dispose();
								reset();
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
			
			super("Remove Appointment");
		
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
		 departs.add(new JLabel("starts at:         "));
		 legends.add(departs);
		
		 JPanel courses =  new JPanel(new FlowLayout(FlowLayout.RIGHT));
		 courses.add(new JLabel("ends at:         "));
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
								Admin.saveData(admin);
								reset();
								// ScheduleGUI();
							}
				}

			}
			public void cancelpressed() {
				dispose();
			}
	 
	 
	 
	 }}
	
}
