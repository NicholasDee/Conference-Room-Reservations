package deenicholas.delnovo.gui.mainFrame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import vzap.delnovo.client.ClientMain;
import vzap.delnovo.dto.*;
import vzap.delnovo.gui.menu.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import java.awt.Color;

public class MainFrame extends JFrame implements ActionListener
{
	public static  MainFrame mf = null;
	private JPanel contentPane;
	public static JMenuBar menuBar;
	public static JMenu mnFile;
	public static JMenu mnReports;
	public static JMenu mnAccount;
	public static JMenu mnHelp;
	private JMenuItem mntmMakeABooking;
	private JMenuItem mntmGenerateReports;
	private JMenuItem mntmCancelBooking;
	private JMenuItem mntmViewBookings;
	public static JPanel mainPanel;
	public static ClientMain cm;
	public static UserDTO user;
	public static Vector lists;
	public static Questionairre qs;
	
	private JMenuItem mntmLogOut;
	private JMenuItem mntmExit;
	private JMenuItem mntmAboutCrrs;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					cm = new ClientMain();
					mf = new MainFrame();
					mf.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setForeground(SystemColor.desktop);
		setBackground(SystemColor.activeCaption);
		setTitle("Conference Room Reservation System");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					MainFrame.cm.closeConnections();
					System.exit(0);
				} catch (IOException e1) {
					System.err.println("Connections not closed on exit");
					System.exit(0);
				}
			}
		});
		setBounds(100, 100, 1000, 750);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		mnFile.setEnabled(false);
		
		mntmMakeABooking = new JMenuItem("Make A Booking");
		mnFile.add(mntmMakeABooking);
		mntmMakeABooking.addActionListener(this);
		
		mntmCancelBooking = new JMenuItem("Cancel Booking");
		mnFile.add(mntmCancelBooking);
		mntmCancelBooking.addActionListener(this);
		
		mntmViewBookings = new JMenuItem("View Bookings");
		mnFile.add(mntmViewBookings);
		mntmViewBookings.addActionListener(this);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(this);
		
		mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		mnReports.setEnabled(false);
		
		mntmGenerateReports = new JMenuItem("Generate Reports");
		mnReports.add(mntmGenerateReports);
		mntmGenerateReports.addActionListener(this);
		
		mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		mnAccount.setEnabled(false);
		
		mntmLogOut = new JMenuItem("Log Out");
		mnAccount.add(mntmLogOut);
		mntmLogOut.addActionListener(this);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		mnHelp.setEnabled(false);
		
		mntmAboutCrrs = new JMenuItem("About CRRS");
		mnHelp.add(mntmAboutCrrs);
		mntmAboutCrrs.addActionListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
		);
		
		
		
		mainPanel = new JPanel();
		mainPanel.setForeground(Color.BLACK);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setBackground(SystemColor.activeCaption);
		LogIn login = new LogIn();
		login.setBackground(SystemColor.activeCaption);
		mainPanel.add(login);
		scrollPane.setViewportView(mainPanel);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if(source == mntmMakeABooking)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			qs = new Questionairre();
			mainPanel.add(qs);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == mntmCancelBooking)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			CancelBooking cb = new CancelBooking();
			mainPanel.add(cb);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == mntmViewBookings)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			ViewBookings vb = new ViewBookings();
			mainPanel.add(vb);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == mntmGenerateReports)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			GenerateReports gr = new GenerateReports();
			mainPanel.add(gr);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == mntmAboutCrrs)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			Help help = new Help();
			mainPanel.add(help);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == mntmLogOut)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			LogIn li = new LogIn();
			mainPanel.add(li);
			mainPanel.validate();
			mainPanel.repaint();
			
			mnFile.setEnabled(false);
			mnReports.setEnabled(false);
			mnAccount.setEnabled(false);
			mnHelp.setEnabled(false);
		}
		
		if(source == mntmExit)
		{
			try
			{
				MainFrame.cm.closeConnections();
				System.exit(0);
			} catch (IOException e)
			{
				System.err.println("Connections not closed on exit");
				System.exit(0);
			}
		}
	}
}
