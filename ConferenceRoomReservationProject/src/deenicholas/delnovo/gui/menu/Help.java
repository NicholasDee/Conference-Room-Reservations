package deenicholas.delnovo.gui.menu;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import deenicholas.delnovo.gui.mainFrame.MainFrame;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class Help extends JPanel implements ActionListener
{
	private JLabel lblHelpPage;
	private JScrollPane scrollPane;
	private JButton btnDone;
	private JTextArea txtrWelcomeToThe;
	private JPanel mainPanel = MainFrame.mainPanel;

	/**
	 * Create the panel.
	 */
	public Help()
	{
		setBackground(SystemColor.activeCaption);
		
		lblHelpPage = new JLabel("Help Page");
		lblHelpPage.setFont(new Font("Californian FB", Font.BOLD, 50));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		
		btnDone = new JButton("Back");
		btnDone.addActionListener(this);
		btnDone.setFont(new Font("Californian FB", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHelpPage)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
						.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHelpPage)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDone)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		
		txtrWelcomeToThe = new JTextArea();
		txtrWelcomeToThe.setEditable(false);
		txtrWelcomeToThe.setLineWrap(true);
		txtrWelcomeToThe.setWrapStyleWord(true);
		txtrWelcomeToThe.setText("Welcome to the help page!\r\n\r\nCRRS is a conference room booking program, that is meant\r\nto shorten the time spent organizing and making bookings.\r\n\r\nBeyond making bookings, users can add themselves to a \r\nwaitlist. When  a booking is cancelled, the next user on a \r\nwaitlist is given a 72 hour period to confirm a booking.\r\n\r\nThere are reports for: \r\n\t-The equipment used, which describes what equipment \r\n\t was used and how often, during a selected period of \r\n\t time.\r\n \t-The number of cancellations made during a selected \r\n\t period of time.\r\n\t-The waitlist details \r\n\t-The Rooms used, and ranked according to percentage \r\n\t of usage.\r\n\r\nUsers may view booked rooms and find out information of \r\nbookings.\r\n\r\nIf you require any extra assistance please contact us:\r\n\tdelnovovzap@gmail.com");
		txtrWelcomeToThe.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		scrollPane.setViewportView(txtrWelcomeToThe);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource() == btnDone)
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
