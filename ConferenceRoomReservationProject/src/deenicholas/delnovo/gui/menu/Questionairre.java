package deenicholas.delnovo.gui.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;
import deenicholas.delnovo.dto.*;
import deenicholas.delnovo.gui.mainFrame.MainFrame;
import java.awt.SystemColor;
import com.toedter.components.JSpinField;

public class Questionairre extends JPanel implements ActionListener
{
	private Date currentDate = new Date();
	private JTextField txtSurname;
	private JTextField txtDept;
	private JTextField txtTel;
	private JTextField txtEmail;
	private JTextField txtTitle;
	private JButton btnSearch;
	private JButton btnBack;
	private JPanel mainPanel = MainFrame.mf.mainPanel;
	private JTextField txtNumberOfAttendees;
	private JTextField txtName;
	public static JDateChooser startDate;
	public static JDateChooser endDate;
	private JComboBox cmbbxStartHour;
	private JComboBox cmbbxStartMinute;
	private JComboBox cmbbxEndHour;
	private JComboBox cmbbxEndMinute;
	private JComboBox cmbbxLength;
	
	public static JTextArea txtrReason;
	public static int startHour;
	public static int startMinute;
	public static int endHour;
	public static int endMinute;
	public static int capacity;
	public static int length;
	private JLabel lblLimit;

	/**
	 * Create the panel.
	 */
	public Questionairre()
	{
		setBackground(SystemColor.activeCaption);
		
		JLabel lblBookAConference = new JLabel("Book a Conference Room");
		lblBookAConference.setFont(new Font("Californian FB", Font.BOLD, 50));
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		JLabel lblDept = new JLabel("Dept:");
		lblDept.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		JLabel lblEmail = new JLabel("email:");
		lblEmail.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		JLabel lblDateRange = new JLabel("Date Range:");
		lblDateRange.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		JLabel lblTimeRange = new JLabel("Time Range:");
		lblTimeRange.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		txtSurname = new JTextField();
		txtSurname.setBackground(SystemColor.activeCaption);
		txtSurname.setEditable(false);
		txtSurname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtSurname.setColumns(10);
		
		txtDept = new JTextField();
		txtDept.setBackground(SystemColor.activeCaption);
		txtDept.setEditable(false);
		txtDept.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtDept.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setBackground(SystemColor.activeCaption);
		txtTel.setEditable(false);
		txtTel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtTel.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBackground(SystemColor.activeCaption);
		txtEmail.setEditable(false);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setBackground(SystemColor.activeCaption);
		txtTitle.setEditable(false);
		txtTitle.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtTitle.setColumns(10);
		
		cmbbxStartHour = new JComboBox();
		cmbbxStartHour.setFont(new Font("Californian FB", Font.BOLD, 20));
		cmbbxStartHour.setModel(new DefaultComboBoxModel(new String[] {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"}));
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		cmbbxStartMinute = new JComboBox();
		cmbbxStartMinute.setFont(new Font("Californian FB", Font.BOLD, 20));
		cmbbxStartMinute.setModel(new DefaultComboBoxModel(new String[] {"00", "30"}));
		
		JLabel label_1 = new JLabel("-");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		cmbbxEndHour = new JComboBox();
		cmbbxEndHour.setFont(new Font("Californian FB", Font.BOLD, 20));
		cmbbxEndHour.setModel(new DefaultComboBoxModel(new String[] {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"}));
		
		JLabel label_2 = new JLabel(":");
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		cmbbxEndMinute = new JComboBox();
		cmbbxEndMinute.setFont(new Font("Californian FB", Font.BOLD, 20));
		cmbbxEndMinute.setModel(new DefaultComboBoxModel(new String[] {"00", "30"}));
		
		JLabel lblLengthOfMeeting = new JLabel("Length of meeting:");
		lblLengthOfMeeting.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		cmbbxLength = new JComboBox();
		cmbbxLength.setModel(new DefaultComboBoxModel(new String[] {"0.5", "1.0", "1.5", "2.0", "2.5", "3.5", "4.0", "4.5", "5.0", "5.5", "6.0", "6.5", "7.0", "7.5", "8.0", "8.5", "9.0", "9.5", "10.0", "10.5"}));
		cmbbxLength.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		JLabel lblHours = new JLabel("hour(s)");
		lblHours.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		btnSearch = new JButton("Find Room");
		btnSearch.addActionListener(this);
		btnSearch.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnBack.addActionListener(this);
		
		JLabel lblNumberOfAttendees = new JLabel("Number of Attendees:");
		lblNumberOfAttendees.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		txtNumberOfAttendees = new JTextField();
		txtNumberOfAttendees.setFont(new Font("Californian FB", Font.PLAIN, 15));
		txtNumberOfAttendees.setColumns(10);
		
		JLabel lblReasonForMeeting = new JLabel("Reason For Meeting:");
		lblReasonForMeeting.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		txtrReason = new JTextArea();
		txtrReason.setFont(new Font("Californian FB", Font.PLAIN, 15));
		
		txtName = new JTextField();
		txtName.setBackground(SystemColor.activeCaption);
		txtName.setEditable(false);
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtName.setColumns(10);
		
		startDate = new JDateChooser();
		startDate.getCalendarButton().addActionListener(this);
		
		JLabel label_3 = new JLabel("-");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		endDate = new JDateChooser();
		endDate.getCalendarButton().addActionListener(this);
		
		lblLimit = new JLabel("(max 30)");
		lblLimit.setFont(new Font("Californian FB", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSearch)
							.addGap(194)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addGap(403))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBookAConference)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblName)
										.addComponent(lblLengthOfMeeting)
										.addComponent(lblTimeRange)
										.addComponent(lblNumberOfAttendees)
										.addComponent(lblReasonForMeeting)
										.addComponent(lblSurname)
										.addComponent(lblDateRange)
										.addComponent(lblDept)
										.addComponent(lblTel)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblTitle)
											.addComponent(lblEmail)))
									.addGap(23)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtTel, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(txtDept, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(startDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(label_3)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(endDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
															.addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
															.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtrReason, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
																.addGroup(groupLayout.createSequentialGroup()
																	.addComponent(cmbbxStartHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(label)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(cmbbxStartMinute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(label_1)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(cmbbxEndHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(label_2)
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(cmbbxEndMinute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addGap(36))
																.addGroup(groupLayout.createSequentialGroup()
																	.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(txtNumberOfAttendees, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
																		.addComponent(cmbbxLength, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblLimit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblHours))))))))))))
							.addGap(315))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addComponent(lblBookAConference)
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtSurname, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSurname, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDept, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDept, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDateRange, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_3)
								.addComponent(startDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(endDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTimeRange, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbbxStartHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(cmbbxStartMinute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(cmbbxEndHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addComponent(cmbbxEndMinute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLengthOfMeeting)
						.addComponent(cmbbxLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHours))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNumberOfAttendees)
							.addComponent(txtNumberOfAttendees, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblLimit, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtrReason, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReasonForMeeting))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		txtName.setText(MainFrame.user.getName());
		txtSurname.setText(MainFrame.user.getSurname());
		txtEmail.setText(MainFrame.user.getEmail());
		txtDept.setText(MainFrame.user.getDept());
		txtTel.setText(MainFrame.user.getTel());
		if(MainFrame.user.getTitle().equals("1"))
		{
			txtTitle.setText("Executive");
		}
		else
		{
			txtTitle.setText("Employee");
		}
		
//		txtName.setText("Chris");
//		txtSurname.setText("Spalding");
//		txtEmail.setText("dupeysam@gmail.com");
//		txtDept.setText("IT");
//		txtTel.setText("0732343370");
//		txtTitle.setText("Executive Committee Member");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source == btnSearch)
		{
			if(startDate.getDate() == null)
			{
				JOptionPane.showMessageDialog(this, "Please Enter A Start Date");
				startDate.requestFocus();
				return;
			}
			
			if(endDate.getDate() == null)
			{
				JOptionPane.showMessageDialog(this, "Please Enter An End Date");
				endDate.requestFocus();
				return;
			}
			
			if(startDate.getDate().after(endDate.getDate()))
			{
				JOptionPane.showMessageDialog(this, "Start Date Cannot Be After End Date");
				startDate.requestFocus();
				return;
			}
			
			if(cmbbxStartHour.getSelectedIndex() > cmbbxEndHour.getSelectedIndex())
			{
				JOptionPane.showMessageDialog(this, "Start Time Cannot Be After End Time");
				return;
			}
			
			if(cmbbxStartHour.getSelectedIndex() == cmbbxEndHour.getSelectedIndex())
			{
				if(cmbbxStartMinute.getSelectedIndex() > cmbbxEndMinute.getSelectedIndex())
				{
					JOptionPane.showMessageDialog(this, "Start Time Cannot Be After End Time");
					return;
				}
			}
			
			if(cmbbxStartHour.getSelectedIndex() == cmbbxEndHour.getSelectedIndex())
			{
				if(cmbbxStartMinute.getSelectedIndex() == cmbbxEndMinute.getSelectedIndex())
				{
					JOptionPane.showMessageDialog(this, "Start Time Cannot Be End Time");
					return;
				}
			}
			
			if(txtNumberOfAttendees.getText().equals(null) || txtNumberOfAttendees.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please enter number of attendees");
				return;
			}
			for(int i = 0; i < txtNumberOfAttendees.getText().length(); i++)
			{
				char ch = txtNumberOfAttendees.getText().charAt(i);
				if(Character.isSpaceChar(ch))
				{
					JOptionPane.showMessageDialog(this, "Number of attendees cannot contain spaces");
					return;
				}
			}
			
			for(int i = 0; i < txtNumberOfAttendees.getText().length(); i++)
			{
				char ch = txtNumberOfAttendees.getText().charAt(i);
				if(Character.isAlphabetic(ch))
				{
					JOptionPane.showMessageDialog(this, "Number of Attendees must be a number.");
					return;
				}
			}
			
			if(Integer.parseInt(txtNumberOfAttendees.getText()) > 30)
			{
				JOptionPane.showMessageDialog(this, "Maximum room size is 30");
				return;
			}
			
			if(txtrReason.getText().equals(null) || txtrReason.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please enter a reason");
				return;
			}
			
			int spaceCheckReason = 0;
			for(int i = 0; i < txtrReason.getText().length(); i++)
			{
				char ch = txtrReason.getText().charAt(i);
				if(Character.isSpaceChar(ch))
				{
					spaceCheckReason++;
				}
				if(spaceCheckReason == txtrReason.getText().length())
				{
					JOptionPane.showMessageDialog(this, "Please enter number of attendees");
					return;
				}
			}
			
			startHour = Integer.parseInt(cmbbxStartHour.getSelectedItem().toString());
			startMinute = Integer.parseInt(cmbbxStartMinute.getSelectedItem().toString());
			endHour = Integer.parseInt(cmbbxEndHour.getSelectedItem().toString());
			endMinute = Integer.parseInt(cmbbxEndMinute.getSelectedItem().toString());
			capacity = Integer.parseInt(txtNumberOfAttendees.getText());
			length = (int) (60 * Double.parseDouble(cmbbxLength.getSelectedItem().toString()));
			double lengthCheck = Double.parseDouble(cmbbxLength.getSelectedItem().toString()); 
			double startHourCheck = startHour;
			double endHourCheck = endHour;
			double startMinuteCheck = 0;
			double endMinuteCheck = 0;
			
			if(startMinute == 30)
			{
				startMinuteCheck = .5;
			}
			if(endMinute == 30)
			{
				endMinuteCheck = .5;
			}
			
			if(lengthCheck > ((endHourCheck + endMinuteCheck) - (startHourCheck + startMinuteCheck)))
			{
				JOptionPane.showMessageDialog(this, "Length cannot be greater than the time range");
				return;
			}
			
			if(startDate.getDate().compareTo(currentDate) < 0)
			{
				System.out.println("startDate: " + startDate);
				System.out.println("currentDate: " + currentDate);
				JOptionPane.showMessageDialog(this, "Cannot choose a date before current date");
				return;
			}
			if(startDate.getDate().getYear() == currentDate.getYear() && startDate.getDate().getMonth() == currentDate.getMonth() && startDate.getDate().getDate() == currentDate.getDate())
			{
				Time startTime = new Time(startHour, startMinute, 0);
				Time currentTime = new Time(currentDate.getHours(), currentDate.getMinutes(), 0);
				
//				int sCheckHours = startTime.getHours();
//				int sCheckMinutes = startTime.getMinutes();
//				int eCheckHours = currentTime.getHours();
//				int eCheckMinutes = currentTime.getMinutes();
				//sCheckHours <= eCheckHours && sCheckMinutes <= eCheckMinutes
				
				if(startTime.before(currentTime))
				{
					JOptionPane.showMessageDialog(this, "Cannot select a time before current time");
			        return;
				}
			}
			
			try
			{
				MainFrame.lists = MainFrame.cm.searchRoomClient(startDate.getDate(), endDate.getDate(), startHour, startMinute, endHour, endMinute, length , capacity);
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(this, "server error");
				e.printStackTrace();
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, "server error");
				e.printStackTrace();
			}
						
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			PickARoom par = new PickARoom();
			mainPanel.add(par);
			mainPanel.validate();
			mainPanel.repaint();
			startDate.getDate();
		}
		
		if(source == btnBack)
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
}
