package deenicholas.delnovo.gui.menu;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import deenicholas.delnovo.dto.BookingDTO;
import deenicholas.delnovo.dto.ConferenceRoomDTO;
import deenicholas.delnovo.dto.ListDTO;
import deenicholas.delnovo.gui.mainFrame.MainFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class ConfirmReservation extends JPanel implements ActionListener
{
	private JLabel lblConfirmReservation;
	private JScrollPane scrollPane;
	private JList list;
	private JButton btnConfirmReservation;
	private JButton btnCancelReservtaion;
	private JButton btnBack;
	private JPanel mainPanel = MainFrame.mainPanel;

	/**
	 * Create the panel.
	 */
	public ConfirmReservation()
	{
		setBackground(SystemColor.activeCaption);
		
		lblConfirmReservation = new JLabel("Confirm Reservation");
		lblConfirmReservation.setFont(new Font("Californian FB", Font.BOLD, 50));
		
		scrollPane = new JScrollPane();
		
		btnConfirmReservation = new JButton("Confirm Reservation");
		btnConfirmReservation.addActionListener(this);
		btnConfirmReservation.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		btnCancelReservtaion = new JButton("Cancel Reservtaion");
		btnCancelReservtaion.addActionListener(this);
		btnCancelReservtaion.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnCancelReservtaion, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
								.addComponent(btnConfirmReservation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(48))
						.addComponent(lblConfirmReservation)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConfirmReservation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnConfirmReservation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelReservtaion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBack))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		Vector<BookingDTO> listInfo = null;
		try
		{
			listInfo = MainFrame.cm.viewReservation(MainFrame.user.getEmail());
			list.setListData(listInfo);
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
		
		if( listInfo.isEmpty())
		{
			String [ ] noTextInList = new String [ ]{"You currently have no reservations."};
			list.setListData(noTextInList);
			list.setEnabled(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source == btnConfirmReservation)
		{
			BookingDTO selected = (BookingDTO) list.getSelectedValue();
			if(list.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please select a room to reserve.");
				return;
			}
			
			try
			{
				MainFrame.cm.confirmReservation(selected);
				JOptionPane.showMessageDialog(this, "Reservation successfully confirmed.");
				mainPanel.removeAll();
				mainPanel.validate();
				mainPanel.repaint();
				MainMenu mm = new MainMenu();
				mainPanel.add(mm);
				mainPanel.validate();
				mainPanel.repaint();
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, "Confirm unsuccessful, please try again");
				return;
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(this, "Comfirm unsuccessful, please try again");
				return;
			}
		}
		
		if(source == btnCancelReservtaion)
		{
			BookingDTO selected = (BookingDTO) list.getSelectedValue();
			if(list.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please select a room to cancel.");
				return;
			}
			try
			{
				MainFrame.cm.cancelReservation(selected);
				JOptionPane.showMessageDialog(this, "Reservation Cancelled");
				mainPanel .removeAll();
				mainPanel.validate();
				mainPanel.repaint();
				MainMenu mm = new MainMenu();
				mainPanel.add(mm);
				mainPanel.validate();
				mainPanel.repaint();
			} catch (UnknownHostException e)
			{
				JOptionPane.showMessageDialog(this, "Unable to cancel");
				return;
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(this, "Unable to cancel");
				return;
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, "Unable to cancel");
				return;
			}
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
