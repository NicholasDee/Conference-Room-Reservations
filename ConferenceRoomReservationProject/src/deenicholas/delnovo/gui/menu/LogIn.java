package deenicholas.delnovo.gui.menu;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import deenicholas.delnovo.gui.mainFrame.MainFrame;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class LogIn extends JPanel implements ActionListener
{
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	private JButton btnLogIn;
	private JPanel mainPanel = MainFrame.mf.mainPanel;
	private JButton btnExit;

	/**
	 * Create the panel.
	 */
	public LogIn()
	{
		setBackground(SystemColor.activeCaption);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setBackground(new Color(240, 240, 240));
		lblLogIn.setFont(new Font("Californian FB", Font.BOLD, 50));
		
		JLabel lblEmail = new JLabel("email:");
		lblEmail.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 15));
		txtEmail.setColumns(10);
		
		btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnLogIn.addActionListener(this);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("Californian FB", Font.BOLD, 20));
		
		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 15));
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		btnExit.setFont(new Font("Californian FB", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblEmail, Alignment.LEADING)
								.addComponent(lblPassword, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, 354, 354, 354)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(177)
							.addComponent(lblLogIn))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(169)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLogIn, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogIn)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(btnLogIn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(287))
		);
		setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source == btnLogIn)
		{
			String email = txtEmail.getText();
			String pwd = new String(pwdPassword.getPassword());
			if(email.isEmpty() || email.equals(null))
			{
				JOptionPane.showMessageDialog(this, "Please enter a valid email");
				return;
			}
			
			int spaceCheckEmail = 0;
			for(int i = 0; i < email.length(); i++)
			{
				char ch = email.charAt(i);
				if(Character.isSpaceChar(ch))
				{
					spaceCheckEmail++;
				}
				if(spaceCheckEmail == email.length())
				{
					JOptionPane.showMessageDialog(this, "Please enter a valid email");
					return;
				}
			}
			
			if(pwd.isEmpty() || email.equals(null))
			{
				JOptionPane.showMessageDialog(this, "Password cannot be empty");
					return;
			}
			
			int spaceCheckPassword = 0;
			for(int i = 0; i < email.length(); i++)
			{
				char ch = email.charAt(i);
				if(Character.isSpaceChar(ch))
				{
					spaceCheckPassword++;
				}
				if(spaceCheckPassword == email.length())
				{
					JOptionPane.showMessageDialog(this, "Password cannot be empty");
					return;
				}
			}
			
			try
			{
				MainFrame.user = MainFrame.cm.login(email, pwd);
				if(MainFrame.user.getEmail().equals("err"))
				{
					JOptionPane.showMessageDialog(this, "Email or password incorrect");
					return;
				}
				MainFrame.user.setEmail(email);
				MainFrame.user.setPsw(pwd);
				MainFrame.mf.setTitle("Conference Room Reservation System (" + MainFrame.user.getName() + ")");
			} catch (UnknownHostException e)
			{
				JOptionPane.showMessageDialog(this, "Server unavailable, please try again later.");
				e.printStackTrace();
				return;
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(this, "Server unavailable, please try again later.");
				e.printStackTrace();
				return;
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this, "Server unavailable, please try again later.");
				e.printStackTrace();
				return;
			}
			
			
			
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			MainMenu menu = new MainMenu();
			mainPanel.add(menu);
			mainPanel.validate();
			mainPanel.repaint();
			MainFrame.mf.mnFile.setEnabled(true);
			MainFrame.mf.mnAccount.setEnabled(true);
			MainFrame.mf.mnReports.setEnabled(true);
			MainFrame.mf.mnHelp.setEnabled(true);
		}
		
		if(btnExit == source)
		{
			try
			{
				MainFrame.cm.closeConnections();
				System.exit(0);
			} catch (IOException e)
			{
				System.out.println("fuahdovuadbivbdshibvsadbfaudbifuhdkhfcb");
				System.exit(0);
			}
		}
	}
}
