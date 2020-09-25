package deenicholas.delnovo.gui.menu;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import deenicholas.delnovo.dto.BookingDTO;
import deenicholas.delnovo.gui.mainFrame.MainFrame;

public class ViewBookings extends JPanel implements ActionListener
{
	private JLabel lblViewBookings;
	private JScrollPane scrollPane;
	private JList bookingsList;
	private JButton btnRefresh;
	private JButton btnBack;
	private JButton btnViewBooking;
	private JTextArea txtrBookinginfo;
	private JLabel lblListOfBookings;
	private JLabel lblBookingInformation;
	private JPanel mainPanel = MainFrame.mainPanel;
	private JScrollPane scrollPane_1;

	/**
	 * Create the panel.
	 */
	public ViewBookings()
	{
		setBackground(SystemColor.activeCaption);
		
		lblViewBookings = new JLabel("View Bookings");
		lblViewBookings.setFont(new Font("Californian FB", Font.BOLD, 50));
		
		scrollPane = new JScrollPane();
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnRefresh.addActionListener(this);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnBack.addActionListener(this);
		
		btnViewBooking = new JButton("Query Booking");
		btnViewBooking.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnViewBooking.addActionListener(this);
		
		lblListOfBookings = new JLabel("List Of Bookings:");
		lblListOfBookings.setFont(new Font("Californian FB", Font.BOLD, 18));
		
		lblBookingInformation = new JLabel("Booking Information:");
		lblBookingInformation.setFont(new Font("Californian FB", Font.BOLD, 18));
		
		scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnBack, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnRefresh, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnViewBooking, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
								.addComponent(lblBookingInformation, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblListOfBookings)
						.addComponent(lblViewBookings))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblViewBookings)
					.addGap(18)
					.addComponent(lblListOfBookings)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnViewBooking)
							.addGap(13)
							.addComponent(btnRefresh)
							.addGap(9)
							.addComponent(btnBack)
							.addGap(25)
							.addComponent(lblBookingInformation, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		
		txtrBookinginfo = new JTextArea();
		scrollPane_1.setViewportView(txtrBookinginfo);
		txtrBookinginfo.setBackground(SystemColor.control);
		txtrBookinginfo.setEditable(false);
		txtrBookinginfo.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		
		bookingsList = new JList();
		
		try
		{
			bookingsList.setListData((MainFrame.cm.queryARoom()));
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bookingsList.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		scrollPane.setViewportView(bookingsList);
		setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		
		if(source == btnViewBooking)
		{
			BookingDTO selected = (BookingDTO) bookingsList.getSelectedValue();
			
			if(bookingsList.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please select a room to query");
				return;
			}
			
			if(selected.getUser().getTitle().equals("1") && MainFrame.user.getTitle().equals("0"))
			{
				txtrBookinginfo.setText("");
				txtrBookinginfo.setText("Room Unavailable");
			}
			else
			{
				txtrBookinginfo.setText("");
				txtrBookinginfo.append("Name: " + selected.getUser().getName());
				txtrBookinginfo.append("\nSurname: " + selected.getUser().getSurname());
				txtrBookinginfo.append("\nemail: " + selected.getUser().getEmail());
				txtrBookinginfo.append("\nRoom name: " + selected.getConference().getRoomName());
				txtrBookinginfo.append("\nNo. of Attendees: " + selected.getNoOfAttendees());
			}
		}
		
		if(source == btnRefresh)
		{
			try
			{
				bookingsList.setListData((MainFrame.cm.queryARoom()));
				JOptionPane.showMessageDialog(this, "Refreshed");
			} catch (UnknownHostException e)
			{
				JOptionPane.showMessageDialog(this, "Refresh Failed");
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(this, "Refresh Failed");
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, "Refresh Failed");
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
