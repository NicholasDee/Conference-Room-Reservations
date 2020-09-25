package deenicholas.delnovo.gui.menu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import deenicholas.delnovo.dto.*;
import deenicholas.delnovo.gui.mainFrame.MainFrame;

public class CancelConfirm extends JDialog
{
	private JButton jbYes, jbNo;
	private UserDTO user;
	private ConferenceRoomDTO conference;
	private String descrip;
	private int length, startH, startM, endH, endM, numOfAtten;
	private Date date;
	private ArrayList<String> equip;
	
	public CancelConfirm(UserDTO user, ConferenceRoomDTO conference, String descrip, int length, int startH, int startM, int endH, int endM, Date date, int numOfAtten, ArrayList<String> equip)
	{
		createForms();
		createButtons();
		registerListeners();
		config();
		this.user = user;
		this.conference = conference;
		this.descrip = descrip;
		this.length = length;
		this.startH = startH;
		this.startM = startM;
		this.endH = endH;
		this.endM = endM;
		this.date = date;
		this.numOfAtten = numOfAtten;
		this.equip = equip;
		this.setVisible(true);
		this.setModal(true);
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
		jpForm.setBorder(BorderFactory.createTitledBorder("Cancel Booking"));
		jpForm.add(fieldSet(new JLabel("Are you sure you wish to cancel this booking?")));
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
		try
		{
			MainFrame.cm.removeBooking(user, conference, descrip, length, startH, startM, endH, endM, date, numOfAtten, equip);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(this, "Booking Cancelled", "", JOptionPane.INFORMATION_MESSAGE);
		MainFrame.mainPanel .removeAll();
		MainFrame.mainPanel.validate();
		MainFrame.mainPanel.repaint();
		MainMenu mm = new MainMenu();
		MainFrame.mainPanel.add(mm);
		MainFrame.mainPanel.validate();
		MainFrame.mainPanel.repaint();
		dispose();
		this.setModal(false);
	}
	
	//**********************************************
	
	private void cmdCancel()
	{
		dispose();
		this.setModal(false);
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