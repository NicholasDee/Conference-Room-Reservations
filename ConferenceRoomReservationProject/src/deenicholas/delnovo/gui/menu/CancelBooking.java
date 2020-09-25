package deenicholas.delnovo.gui.menu;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import deenicholas.delnovo.dto.*;
import deenicholas.delnovo.gui.mainFrame.MainFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import com.sun.mail.imap.protocol.ListInfo;
import javax.swing.ListSelectionModel;

public class CancelBooking extends JPanel implements ActionListener
{

	private JButton btnDeleteBooking;
	private JList list;
	private JButton btnBack;
	private JPanel mainPanel = MainFrame.mainPanel;

	/**
	 * Create the panel.
	 */
	public CancelBooking()
	{
		
		setBackground(SystemColor.activeCaption);
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnDeleteBooking = new JButton("Delete Booking");
		btnDeleteBooking.addActionListener(this);
		btnDeleteBooking.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnBack.addActionListener(this);
		
		JLabel lblCancelBooking = new JLabel("Cancel Booking");
		lblCancelBooking.setFont(new Font("Californian FB", Font.BOLD, 50));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
								.addComponent(btnDeleteBooking, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCancelBooking)))
					.addGap(121))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCancelBooking)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDeleteBooking)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBack))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(new Color(255, 255, 255));
		list.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
		Object [ ] listInfo = null;
		try
		{
			listInfo = MainFrame.cm.cancelARoom(MainFrame.user.getEmail()).toArray();
			list.setListData(listInfo);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( listInfo == null || listInfo.length == 0)
		{
			String [ ] noTextInList = new String [ ]{"You currently have no bookings."};
			list.setListData(noTextInList);
			list.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source == btnDeleteBooking)
		{
			BookingDTO selected = (BookingDTO) list.getSelectedValue();
			if(list.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please select a room to cancel");
				return;
			}
			ConferenceRoomDTO crdto = selected.getConference();
			
			CancelConfirm cc = new CancelConfirm(MainFrame.user, crdto, selected.getDescription(), selected.getLength(), selected.getStartH(), selected.getStartM(), selected.getEndH(), selected.getEndM(), selected.getDate(), selected.getNoOfAttendees(), selected.getEquipment());
			
//			try
//			{
//				list.setListData(MainFrame.cm.cancelARoom(MainFrame.user.getEmail()).toArray());
//				System.out.println("update cancels");
//			} catch (ClassNotFoundException e)
//			{
//				e.printStackTrace();
//			} catch (IOException e)
//			{
//				e.printStackTrace();
//			}
		}
		
		if(source == btnBack)
		{
			mainPanel .removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			MainMenu mm = new MainMenu();
			mainPanel.add(mm);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
	}
}
