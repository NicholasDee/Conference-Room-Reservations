package deenicholas.delnovo.gui.menu;

import javax.swing.JPanel;
import deenicholas.delnovo.dto.*;
import deenicholas.delnovo.gui.mainFrame.MainFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PickARoom extends JPanel implements ActionListener, ListSelectionListener
{
	private JPanel mainPanel = MainFrame.mf.mainPanel;
	private JButton btnBookRoom;
	private JButton btnViewBookedRooms;
	private JButton btnChangeRoom;
	private JButton btnCancel;
	private JList list;
	private JButton btnAddToWait;
	private String[] availableRooms;
	private boolean tf;
	
	/**
	 * Create the panel.
	 */
	public PickARoom()
	{
		setBorder(null);
		setBackground(SystemColor.activeCaption);
		
		JLabel lblPickARoom = new JLabel("Pick A Room");
		lblPickARoom.setFont(new Font("Californian FB", Font.BOLD, 50));
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnBookRoom = new JButton("Book Room");
		btnBookRoom.setToolTipText("Uses the selected room to make a booking.");
		btnBookRoom.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnBookRoom.addActionListener(this);
		
		btnViewBookedRooms = new JButton("View Booked Rooms");
		btnViewBookedRooms.setToolTipText("To view booked rooms.");
		btnViewBookedRooms.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnViewBookedRooms.setEnabled(false);
		btnViewBookedRooms.addActionListener(this);
		
		btnChangeRoom = new JButton("Change Room Specs");
		btnChangeRoom.setToolTipText("Go back and change the specifications of a room booking, e.g. - Room capacity");
		btnChangeRoom.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnChangeRoom.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Takes you back to the main menu.");
		btnCancel.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnCancel.addActionListener(this);
		
		btnAddToWait = new JButton("Add to Wait List");
		btnAddToWait.setEnabled(false);
		btnAddToWait.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnAddToWait.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAddToWait, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnChangeRoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnViewBookedRooms, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBookRoom, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
						.addComponent(lblPickARoom))
					.addGap(140))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPickARoom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBookRoom)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnViewBookedRooms)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnChangeRoom)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddToWait)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setToolTipText("Order(Room Name, Capacity, Time, Date, Equipment)");
		list.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		list.addListSelectionListener(this);
		availableRooms = new String[] {"A-CR2 15 11:00 15/07/2018 Digital Projector, WiFi", "B-CR2 15 11:00 15/07/2018 Digital Projector, WiFi", "C-CR2 15 11:00 15/07/2018 Smart Electronic White-Board, Digital Projector, Wi-fi", "D-CR2 15 11:00 15/07/2018 Digital Projector, WiFi"};
		list.setListData(MainFrame.lists);
		if(MainFrame.lists.isEmpty())
		{
			String [ ] noTextInList = new String [ ]{"There are no available rooms,", "please view booked rooms."};
			list.setListData(noTextInList);
			btnBookRoom.setEnabled(false);
			btnViewBookedRooms.setEnabled(true);
			list.setEnabled(false);
		}
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source == btnBookRoom)
		{
			 ListDTO selected = (ListDTO) list.getSelectedValue();
			if(list.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please select a room to book.");
				return;
			}
			ConferenceRoomDTO crdto = selected.getConferenceRoom();
			
			ArrayList<String> equipment = new ArrayList<String>();
			String reason = MainFrame.qs.txtrReason.getText();
			int length = MainFrame.qs.length;
			int startTime = selected.getStartTime().getHours();
			int startMinute = selected.getStartTime().getMinutes();
			int endHour= selected.getEndTime().getHours();
			int endMinute = selected.getEndTime().getMinutes();
			Date date = selected.getDate();
			
			BookingConfirm bc = new BookingConfirm(MainFrame.user, crdto, reason, length, startTime, startMinute, endHour, endMinute, date, MainFrame.qs.capacity , equipment);
		}
		
		if(source == btnViewBookedRooms)
		{
			Vector<ListDTO> bookedRooms;
			try
			{
				bookedRooms = MainFrame.cm.viewWaitingList(MainFrame.qs.startDate.getDate(), MainFrame.qs.endDate.getDate(), MainFrame.qs.startHour, MainFrame.qs.startMinute, MainFrame.qs.endHour, MainFrame.qs.endMinute, MainFrame.qs.length, MainFrame.qs.capacity);
				list.setEnabled(true);
				list.setListData(bookedRooms);
				btnBookRoom.setEnabled(false);
				btnAddToWait.setEnabled(true);
				btnViewBookedRooms.setEnabled(false);
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(this, "System err");
				mainPanel.removeAll();
				mainPanel.validate();
				mainPanel.repaint();
				MainMenu menu = new MainMenu();
				mainPanel.add(menu);
				mainPanel.validate();
				mainPanel.repaint();
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, "System err");
				mainPanel.removeAll();
				mainPanel.validate();
				mainPanel.repaint();
				MainMenu menu = new MainMenu();
				mainPanel.add(menu);
				mainPanel.validate();
				mainPanel.repaint();
			}
		}
		
		if(source == btnChangeRoom)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			Questionairre qs = new Questionairre();
			mainPanel.add(qs);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == btnAddToWait)
		{
			ListDTO selected = (ListDTO) list.getSelectedValue();
			if(list.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please select a room.");
			}
			ConferenceRoomDTO crdto = selected.getConferenceRoom();
				
			ArrayList<String> equipment = new ArrayList<String>();
			String reason = MainFrame.qs.txtrReason.getText();
			int length = MainFrame.qs.length;
			int startTime = selected.getStartTime().getHours();
			int startMinute = selected.getStartTime().getMinutes();
			int endHour= selected.getEndTime().getHours();
			int endMinute = selected.getEndTime().getMinutes();
			Date date = selected.getDate();
					
			BookingDTO bdto = new BookingDTO(MainFrame.user, crdto, reason, length, startTime, startMinute, endHour, endMinute, date, MainFrame.qs.capacity , equipment);
			WaitlistConfirm wc = new WaitlistConfirm(bdto, selected.getEmail());
		}
		
		if(source == btnCancel)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			MainMenu menu = new MainMenu();
			mainPanel.add(menu);
			mainPanel.validate();
			mainPanel.repaint();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
