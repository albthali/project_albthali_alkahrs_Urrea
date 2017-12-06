package org.thinktanktutoringservice.people;

import java.awt.BorderLayout;
import org.thinktanktutoringservice.people.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.thinktanktutoringservice.software.DropinSlot;
import org.thinktanktutoringservice.software.MasterSchedule;
import org.thinktanktutoringservice.software.Slot;
import org.thinktanktutoringservice.hardware.*;
import java.util.*;

public class AdminGUI extends JFrame{
	   private JPanel panel;
	   private Admin admin;
	   private MasterSchedule MS;
	   private JPanel spJPanel;
	   private JPanel hJPanel;
	   private JTabbedPane tabbedPane;
	   private JPanel dummyPanel;
	   private JPanel dummyPanelpic;
	   private final int WINDOW_WIDTH = 500;
	   private final int WINDOW_HEIGHT = 400;
	   private MasterSchedule masterSchedule;
	   
	
	public AdminGUI(Admin admin) {
		super("Hi, "+ admin.getName());
		this.admin = admin;
		this.MS = admin.getMasterschedule();
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     tabbedPane = new JTabbedPane();
	     spJPanel = SchedulePanel();
	      tabbedPane.addTab("Home", HomePanel());
	      tabbedPane.addTab("Drop-in Schedule", spJPanel);
	      add(tabbedPane);
	      setVisible(true);
	      setResizable(false);
	      // Display the window.
	}
	
	 private JPanel HomePanel()
	   {
		 
		 JButton addSlotButton; 
		 JButton dropSlotButton;
		 JButton editButton;
		 JButton changePicButton;
	      // Buttons
		 addSlotButton = new JButton("Add Slot");
		 dropSlotButton = new JButton("Drop Slot");
		 editButton = new JButton("Edit Slot");
		 changePicButton = new JButton("Change Profile Image");
	      // Add an action listener to the button.
		 addSlotButton.addActionListener(new addSlotListener(this));
		 editButton.addActionListener(new editButtonListener(this));
		 dropSlotButton.addActionListener(new DropinSlotListner(this));
		 changePicButton.addActionListener(new changePicListner(this));
	      panel = new JPanel();
	      dummyPanel = new JPanel();
	      dummyPanel.add(addSlotButton);
	      dummyPanel.add(editButton);
	      dummyPanel.add(dropSlotButton);
	      dummyPanel.add(changePicButton);
	      dummyPanel.setVisible(true);
	      dummyPanelpic = new JPanel();
	      JTextArea ta = new JTextArea(9,11);
	      String string = new String();
	      string = admin.getName()+"\n"+admin.getEmail();
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
		 String datas[][] = new String[MS.getDropinSchedule().getSlots().size()][4];
		 int i = 0;
		 for (Slot slot : MS.getDropinSchedule().getSlots()) {
			 datas[i][0] = slot.getRoom().getBuilding();
			 datas[i][1] = Integer.toString(slot.getRoom().getNumber());
			 datas[i][2] = slot.getDate();
			 datas[i][3] = slot.getTimestart() + "-" + slot.getTimend();
			 i++;
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
	 
	 void refresh() {
		 tabbedPane.remove(spJPanel);
		 spJPanel = SchedulePanel();
		 
		 tabbedPane.addTab("Drop-in Schedule", spJPanel);
		 remove(tabbedPane);
		 add(tabbedPane);
		 revalidate();
		 
	 }
	 
	 private class addSlotListener implements ActionListener
	 {
		 private AdminGUI adminGUI;
		 public addSlotListener(AdminGUI admingui) {
			adminGUI = admingui;
		}
	      public void actionPerformed(ActionEvent e)
	      {
				AdminAddSlotGUI addSlotGUI = new AdminAddSlotGUI(MS, adminGUI);    

	      }
	  }
	 
	 private class editButtonListener implements ActionListener
	   {
		 private AdminGUI adminGUI;
		 public editButtonListener(AdminGUI admingui) {
			 adminGUI = admingui;
		}
		   public void actionPerformed(ActionEvent e) {
			   AdminEditSlot ars = new AdminEditSlot(MS, adminGUI);
		   }
		   
	   }
	 
	 private class DropinSlotListner implements ActionListener
	 {
		 private AdminGUI adminGUI;
		 public DropinSlotListner(AdminGUI admingui) {
			 adminGUI = admingui;
		 }
	      public void actionPerformed(ActionEvent e)
	      {
	    	  AdminDropSlot adminDropSlot = new AdminDropSlot(MS, adminGUI);
	      }
	  }
	 private class changePicListner implements ActionListener
	 {
		 private AdminGUI adminGUI;
		 public changePicListner(AdminGUI adminGUI) {
			 this.adminGUI = adminGUI;
		}
		 public void actionPerformed(ActionEvent e)
	      {
		 JFileChooser chooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                "JPG & GIF Images", "jpg", "gif");
	        chooser.setFileFilter(filter);
	        int returnVal = chooser.showOpenDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	String path = chooser.getSelectedFile().getPath();
		        File file = new File(path);
		        BufferedImage image = null;
				try {
					image = ImageIO.read(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        JLabel label = new JLabel(new ImageIcon(image));
		        tabbedPane.remove(panel);
			    tabbedPane.remove(spJPanel);
			    remove(tabbedPane);
		        panel = new JPanel();
		        dummyPanelpic = new JPanel();
			      dummyPanelpic.add(label);
			      panel.setLayout(new BorderLayout());
			      panel.add(dummyPanelpic,BorderLayout.CENTER);
			      panel.add(dummyPanel,BorderLayout.SOUTH);
			      tabbedPane.addTab("Home", panel);
			      tabbedPane.addTab("Drop-in Schedule", spJPanel);
			      add(tabbedPane);
	        }
	      }
	 }
	
}
