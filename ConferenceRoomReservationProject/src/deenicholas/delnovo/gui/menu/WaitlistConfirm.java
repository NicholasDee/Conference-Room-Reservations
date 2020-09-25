package deenicholas.delnovo.gui.menu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import deenicholas.delnovo.dto.BookingDTO;
import deenicholas.delnovo.dto.ConferenceRoomDTO;
import deenicholas.delnovo.dto.UserDTO;
import deenicholas.delnovo.gui.mainFrame.MainFrame;

public class WaitlistConfirm extends JDialog
{

	private JButton jbYes, jbNo;
	private UserDTO user;
	private ConferenceRoomDTO conference;
	private String descrip;
	private int length, startH, startM, endH, endM, numOfAtten;
	private Date date;
	private ArrayList<String> equip;
	private JPanel mainPanel = MainFrame.mainPanel;
	
	private String email = null;
	private BookingDTO bdto = null;
	
	public WaitlistConfirm(BookingDTO bdto, String email)
	{
		createForms();
		createButtons();
		registerListeners();
		config();
		this.email = email;
		this.bdto = bdto;
		this.setVisible(true);
	}
	
	//**********************************************
	
	private void config()
	{
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(this.getRootPane());
	}
	
	//**********************************************
	
	private void createForms()
	{
		JPanel jpForm = new JPanel(new GridLayout(2,1,0,5));
		jpForm.setBorder(BorderFactory.createTitledBorder("Confirm Waitlist"));
		jpForm.add(fieldSet(new JLabel("Would you like to add extra equipment?")));
		this.add(jpForm, BorderLayout.CENTER);
	}
	
	//******************************************************************
	
	private JPanel fieldSet(JComponent...components)
	{
		JPanel fieldSet = new JPanel();	
		for(JComponent component: components)
		{
			fieldSet.add(component);
		}
		return fieldSet;
	}
	
	//*****************************************************************
	
	private void createButtons()
	{
		JPanel jpButtons = new JPanel();
		jpButtons.add(jbYes = new JButton("Yes"));
		jpButtons.add(jbNo = new JButton("No"));
		jbYes.setFont(new Font("Californian FB", Font.BOLD, 20));
		jbNo.setFont(new Font("Californian FB", Font.BOLD, 20));
		this.add(jpButtons, BorderLayout.SOUTH);
	}
	
	//**********************************************S
	
	private void registerListeners()
	{
		jbYes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				cmdYes();
			}
		});
		
		jbNo.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				cmdCancel();
			}
		});
	}
	
	//**********************************************
	
	private void cmdYes()
	{
		dispose();
		this.setModal(false);
		mainPanel.removeAll();
		mainPanel.validate();
		mainPanel.repaint();
		WaitingListEquipment wle = new WaitingListEquipment(bdto, this.email);
		mainPanel.add(wle);
		mainPanel.validate();
		mainPanel.repaint();
	}
	
	//**********************************************
	
	private void cmdCancel()
	{	
		try
		{
			boolean tf = MainFrame.cm.reserveARoomForUser(user, conference, descrip, length, startH, startM, endH, endM, date, MainFrame.qs.capacity , equip);
			if(tf)
			{
				JOptionPane.showMessageDialog(this, "Booking successful");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Booking unsuccessful, please try again");
				return;
			}
			
			dispose();
			this.setModal(false);
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			MainMenu menu = new MainMenu();
			mainPanel.add(menu);
			mainPanel.validate();
			mainPanel.repaint();
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(this, "Booking unsuccessful, please try again");
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void clearTheForm(JTextComponent...jtcomponents)
	{
		for(JTextComponent component: jtcomponents)
		{
			component.setText("");
		}
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		this.setModal(false);
	}


}
