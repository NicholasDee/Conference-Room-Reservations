package deenicholas.delnovo.gui.menu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import deenicholas.delnovo.gui.mainFrame.MainFrame;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class MainMenu extends JPanel implements ActionListener
{

	private JButton btnMakeABooking;
	private JButton btnCancelBooking;
	private JButton btnGenerateReports;
	private JButton btnViewBookings;
	private JPanel mainPanel = MainFrame.mainPanel;
	private JButton btnConfirmReservation;

	/**
	 * Create the panel.
	 */
	public MainMenu()
	{
		setBackground(SystemColor.activeCaption);
		setForeground(new Color(0, 0, 0));
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setFont(new Font("Californian FB", Font.BOLD, 50));
		
		btnMakeABooking = new JButton("Make a Booking");
		btnMakeABooking.setFont(new Font("Californian FB", Font.PLAIN, 20));
		btnMakeABooking.addActionListener(this);
		
		btnCancelBooking = new JButton("Cancel Booking");
		btnCancelBooking.setFont(new Font("Californian FB", Font.PLAIN, 20));
		btnCancelBooking.addActionListener(this);
		
		btnGenerateReports = new JButton("Generate Reports");
		btnGenerateReports.setFont(new Font("Californian FB", Font.PLAIN, 20));
		btnGenerateReports.addActionListener(this);
		
		btnViewBookings = new JButton("View Bookings");
		btnViewBookings.setFont(new Font("Californian FB", Font.PLAIN, 20));
		btnViewBookings.addActionListener(this);
		
		btnConfirmReservation = new JButton("Confirm Reservation");
		btnConfirmReservation.addActionListener(this);
		btnConfirmReservation.setFont(new Font("Californian FB", Font.PLAIN, 20));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(btnMakeABooking, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(9)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnCancelBooking, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
										.addComponent(btnGenerateReports, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
										.addComponent(btnConfirmReservation, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
										.addComponent(btnViewBookings, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
							.addGap(33))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMainMenu)
							.addContainerGap(13, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMainMenu)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnMakeABooking)
					.addGap(23)
					.addComponent(btnCancelBooking)
					.addGap(18)
					.addComponent(btnGenerateReports)
					.addGap(18)
					.addComponent(btnViewBookings)
					.addGap(18)
					.addComponent(btnConfirmReservation)
					.addContainerGap(324, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source == btnMakeABooking)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			Questionairre qs = new Questionairre();
			mainPanel.add(qs);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == btnCancelBooking)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			CancelBooking cb = new CancelBooking();
			mainPanel.add(cb);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == btnGenerateReports)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			GenerateReports gr = new GenerateReports();
			mainPanel.add(gr);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == btnViewBookings)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			ViewBookings vb = new ViewBookings();
			mainPanel.add(vb);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == btnConfirmReservation)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			ConfirmReservation cr = new ConfirmReservation();
			mainPanel.add(cr);
			mainPanel.validate();
			mainPanel.repaint();
		}
	}
}
