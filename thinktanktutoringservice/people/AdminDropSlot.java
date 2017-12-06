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

import org.thinktanktutoringservice.software.DropinSlot;
import org.thinktanktutoringservice.software.MasterSchedule;
import org.thinktanktutoringservice.software.Slot;

public class AdminDropSlot extends JFrame{
	private JButton removeButton;  
	private JButton cancelButton;
	private JComboBox<String> slotCB;
	private JLabel slotListLabel;
	private MasterSchedule masterSchedule;
	private AdminGUI adminGUI;
	
	public AdminDropSlot(MasterSchedule mSchedule, AdminGUI admingui) {
		  super("Remove Slot");
		  adminGUI = admingui;
	      this.masterSchedule = mSchedule;
	      setSize(400, 120);
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setResizable(false);
	      setLayout(new BorderLayout());
	      buildPanel();
	      setVisible(true);
	      getRootPane().setDefaultButton(removeButton);
	      adminGUI.setEnabled(false);
	   }
	
private void buildPanel()
{
   
  add(slotListPanel(), BorderLayout.NORTH);
   add(buttonPanel(), BorderLayout.SOUTH);


}
	
	public JPanel slotListPanel() {
		ArrayList<String> choices = new ArrayList<String>();
		for (Slot slot : masterSchedule.getDropinSchedule().getSlots()) {
			choices.add( slot.getRoom().getBuilding() + " " + slot.getRoom().getNumber().toString() + " - " + slot.getDate() 
			+ " - " + slot.getTimestart() + " - " + slot.getTimend());
		}
		
		slotCB = new JComboBox<String>(choices.toArray(new String[0]));  
		slotListLabel = new JLabel("Select a slot: ");
		JPanel slotListPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		slotListPanel.add(slotListLabel);
		slotListPanel.add(slotCB);
		
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
	    	 Slot slot = masterSchedule.getDropinSchedule().getSlots().get(slotCB.getSelectedIndex());
	    	 masterSchedule.getDropinSchedule().removeSlot(slot);
	    	 adminGUI.setEnabled(true);
	    	 adminGUI.refresh();
	    	 dispose();
	     }
	  }
	

}
