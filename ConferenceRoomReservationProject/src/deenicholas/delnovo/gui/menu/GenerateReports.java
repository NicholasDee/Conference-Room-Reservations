package deenicholas.delnovo.gui.menu;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import deenicholas.delnovo.dto.BookingDTO;
import deenicholas.delnovo.gui.mainFrame.MainFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

public class GenerateReports extends JPanel implements ActionListener, Printable
{
	private JLabel lblReports;
	private JRadioButton rdbtnCancelReport;
	private JRadioButton rdbtnEquipmentUsed;
	private JRadioButton rdbtnRoomUsage;
	private JRadioButton rdbtnWaitingList;
	private ButtonGroup btnGroup;
	private JButton btnPreviewReport;
	private JButton btnPrintReport;
	private JTextArea txtrReports;
	private JScrollPane scrollPane;
	private JButton btnBack;
	private JPanel mainPanel = MainFrame.mainPanel;
	private JDateChooser date1;
	private JLabel lblNewLabel;
	private JDateChooser date2;
	private PrinterJob printJob;
	private String d1;
	private String d2;
	private int cancels;

	/**
	 * Create the panel.
	 */
	public GenerateReports()
	{
		setBackground(SystemColor.activeCaption);

		printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);

		lblReports = new JLabel("Reports");
		lblReports.setFont(new Font("Times New Roman", Font.BOLD, 50));

		rdbtnCancelReport = new JRadioButton("Cancellations");
		rdbtnCancelReport.setBackground(SystemColor.activeCaption);
		rdbtnCancelReport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnCancelReport.addActionListener(this);

		rdbtnEquipmentUsed = new JRadioButton("Equipment Used");
		rdbtnEquipmentUsed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnEquipmentUsed.setBackground(SystemColor.activeCaption);
		rdbtnEquipmentUsed.addActionListener(this);

		rdbtnRoomUsage = new JRadioButton("Room Usage (by percentage)");
		rdbtnRoomUsage.setBackground(SystemColor.activeCaption);
		rdbtnRoomUsage.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnRoomUsage.addActionListener(this);

		rdbtnWaitingList = new JRadioButton("Waiting List ");
		rdbtnWaitingList.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnWaitingList.setBackground(SystemColor.activeCaption);
		rdbtnWaitingList.addActionListener(this);

		btnGroup = new ButtonGroup();

		btnGroup.add(rdbtnCancelReport);
		btnGroup.add(rdbtnEquipmentUsed);
		btnGroup.add(rdbtnRoomUsage);
		btnGroup.add(rdbtnWaitingList);

		btnPreviewReport = new JButton("Preview Report");
		btnPreviewReport.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnPreviewReport.addActionListener(this);

		btnPrintReport = new JButton("Print Report");
		btnPrintReport.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnPrintReport.addActionListener(this);

		scrollPane = new JScrollPane();

		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBack.addActionListener(this);

		date1 = new JDateChooser();

		lblNewLabel = new JLabel("-");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));

		date2 = new JDateChooser();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(rdbtnCancelReport).addGap(201)
								.addComponent(date1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(date2,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(lblReports)
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnEquipmentUsed).addComponent(rdbtnRoomUsage)
										.addComponent(rdbtnWaitingList))
								.addGap(116)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(btnPrintReport, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnPreviewReport, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblReports).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnCancelReport).addComponent(lblNewLabel)
								.addComponent(date1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(date2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnEquipmentUsed)
								.addComponent(btnPreviewReport))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnRoomUsage)
								.addComponent(btnPrintReport))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnWaitingList)
								.addComponent(btnBack))
						.addGap(18).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
						.addContainerGap()));

		txtrReports = new JTextArea();
		txtrReports.setWrapStyleWord(true);
		txtrReports.setLineWrap(true);
		txtrReports.setEditable(false);
		scrollPane.setViewportView(txtrReports);
		txtrReports.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		if (source == btnPreviewReport)
		{
			if (rdbtnCancelReport.isSelected())
			{

				if (date1.getDate() == null)
				{
					JOptionPane.showMessageDialog(this, "Please Enter A Start Date");
					date1.requestFocus();
					return;
				}

				if (date2.getDate() == null)
				{
					JOptionPane.showMessageDialog(this, "Please Enter An End Date");
					date2.requestFocus();
					return;
				}

				if (date1.getDate().after(date2.getDate()))
				{
					JOptionPane.showMessageDialog(this, "The start date cannot be after the end date");
					date1.requestFocus();
					return;
				}

				try
				{
					cancels = MainFrame.cm.displayAllCancellations(date1.getDate(), date2.getDate());

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					d1 = sdf.format(date1.getDate());
					d2 = sdf.format(date2.getDate());

					txtrReports.setText("========================================================");
					txtrReports.append("\n Number of Cancellations between " + d1 + " - " + d2);
					txtrReports.append("\n = " + cancels);
					txtrReports.append("\n========================================================");
				} catch (IOException e)
				{
					JOptionPane.showMessageDialog(this, "Server Timed Out");
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				return;
			}

			if (rdbtnRoomUsage.isSelected())
			{

				if (date1.getDate() == null)
				{
					JOptionPane.showMessageDialog(this, "Please Enter A Start Date");
					date1.requestFocus();
					return;
				}

				if (date2.getDate() == null)
				{
					JOptionPane.showMessageDialog(this, "Please Enter An End Date");
					date2.requestFocus();
					return;
				}

				if (date1.getDate().after(date2.getDate()))
				{
					JOptionPane.showMessageDialog(this, "The start date cannot be after the end date");
					date1.requestFocus();
					return;
				}

				try
				{
					ArrayList<String> ocupationPercent = MainFrame.cm.occupationPercentageOfRooms(date1.getDate(),
							date2.getDate());

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String d1 = sdf.format(date1.getDate());
					String d2 = sdf.format(date2.getDate());

					txtrReports.setText("======================================================");
					txtrReports.append("\n Occupation Percentage between: " + d1 + " - " + d2);
					for (int i = 0; i < ocupationPercent.size(); i++)
					{
						txtrReports.append("\n  " + ocupationPercent.get(i));
					}
					txtrReports.append("\n======================================================");
				} catch (IOException e)
				{
					e.printStackTrace();
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				return;
			}

			if (rdbtnEquipmentUsed.isSelected())
			{

				if (date1.getDate() == null)
				{
					JOptionPane.showMessageDialog(this, "Please Enter A Start Date");
					date1.requestFocus();
					return;
				}

				if (date2.getDate() == null)
				{
					JOptionPane.showMessageDialog(this, "Please Enter An End Date");
					date2.requestFocus();
					return;
				}

				if (date1.getDate().after(date2.getDate()))
				{
					JOptionPane.showMessageDialog(this, "The start date cannot be after the end date");
					date1.requestFocus();
					return;
				}

				try
				{
					Vector<String> equipmentUsed = MainFrame.cm.equipmentRequest(date1.getDate(), date2.getDate());

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String d1 = sdf.format(date1.getDate());
					String d2 = sdf.format(date2.getDate());

					txtrReports.setText("======================================================");
					txtrReports.append("\n Equipment used between: " + d1 + " - " + d2);
					for (int i = 0; i < equipmentUsed.size(); i++)
					{
						txtrReports.append("\n  " + equipmentUsed.get(i));
					}
					txtrReports.append("\n======================================================");
				} catch (IOException e)
				{
					e.printStackTrace();
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				return;
			}

			if (rdbtnWaitingList.isSelected())
			{
				try
				{
					Vector<BookingDTO> waitingList = MainFrame.cm.requestForWaitingList();

					txtrReports.setText("========================================================");
					txtrReports.append("\n WAITING LIST");
					txtrReports.append("\n");
					if (waitingList.size() == 0)
					{
						txtrReports.append(" There is no one on the waitling list");
					}
					for (int i = 0; i < waitingList.size(); i++)
					{
						txtrReports.append("\n  " + waitingList.get(i).getUser().getName() + "   "
								+ waitingList.get(i).getUser().getSurname() + " , waiting for:  "
								+ waitingList.get(i).getConference().getRoomName() + ", "
								+ waitingList.get(i).getStartH() + ":" + waitingList.get(i).getStartM());
						txtrReports.append("\n   equipment: ");
						for (int i2 = 0; i2 < waitingList.get(i).getEquipment().size(); i2++)
						{
							if (waitingList.get(i).getEquipment().get(i2) != null)
							{
								txtrReports.append(waitingList.get(i).getEquipment().get(i2).toString() + ", ");
							}
						}
					}
					txtrReports.append("\n========================================================");
				} catch (IOException e)
				{
					e.printStackTrace();
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				return;
			}else 
			{
				JOptionPane.showMessageDialog(this, "Please Make A Report Selection");
				return;
			}
		}

		if (source == btnPrintReport)
		{
			if (txtrReports.getText() == null || txtrReports.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please preview a report before printing");
				return;
			}
			boolean doPrint = printJob.printDialog();
			if (doPrint)
			{
				try
				{
					printJob.print();
				} catch (PrinterException e)
				{
					JOptionPane.showMessageDialog(this, "Print Failed");
				}
			}

		}

		if (source == btnBack)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			MainMenu mm = new MainMenu();
			mainPanel.add(mm);
			mainPanel.validate();
			mainPanel.repaint();
		}
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
	{

		if (page > 0)
		{
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		if (rdbtnCancelReport.isSelected())
		{
			g.drawString("========================================================", 100, 100);
			g.drawString("\n  Number of Cancellations between " + d1 + " - " + d2, 100, 120);
			g.drawString("\n    = " + cancels, 100, 140);
			g.drawString("========================================================", 100, 800);
			return PAGE_EXISTS;
		}

		if (rdbtnEquipmentUsed.isSelected())
		{
			try
			{
				Vector<String> equipmentUsed = MainFrame.cm.equipmentRequest(date1.getDate(), date2.getDate());
				int counter = 120;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String d1 = sdf.format(date1.getDate());
				String d2 = sdf.format(date2.getDate());

				g.drawString("======================================================", 100, 100);
				g.drawString("\n  Equipment used between: " + d1 + " - " + d2, 100, 120);
				for (int i = 0; i < equipmentUsed.size(); i++)
				{
					counter = counter + 20;
					g.drawString("\n   " + equipmentUsed.get(i), 100, counter);
				}
				g.drawString("\n======================================================", 100, 800);
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			return PAGE_EXISTS;
		}

		if (rdbtnRoomUsage.isSelected())
		{
			try
			{
				ArrayList<String> ocupationPercent = MainFrame.cm.occupationPercentageOfRooms(date1.getDate(),
						date2.getDate());
				int counter = 120;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String d1 = sdf.format(date1.getDate());
				String d2 = sdf.format(date2.getDate());

				g.drawString("======================================================", 100, 100);
				g.drawString("\n   Occupation Percentage between: " + d1 + " - " + d2, 100, 120);
				for (int i = 0; i < ocupationPercent.size(); i++)
				{
					counter = counter + 20;
					g.drawString("\n  " + ocupationPercent.get(i), 100, counter);
				}
				g.drawString("\n======================================================", 100, 800);
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			return PAGE_EXISTS;
		}

		if (rdbtnWaitingList.isSelected())
		{
			try
			{
				Vector<BookingDTO> waitingList = MainFrame.cm.requestForWaitingList();
				int counter = 140;

				g.drawString("========================================================", 100, 100);
				g.drawString("\n  WAITING LIST", 100, 120);
				g.drawString("\n", 100, 140);
				if (waitingList.size() == 0)
				{
					g.drawString("   There is no one on the waitling list", 100, 160);
				}
				for (int i = 0; i < waitingList.size(); i++)
				{
					counter = counter + 20;
					Time t = new Time(waitingList.get(i).getStartH(), waitingList.get(i).getStartM(), 0);
					g.drawString("\n   " + waitingList.get(i).getUser().getName() + "   "
							+ waitingList.get(i).getUser().getSurname() + " , waiting for:  "
							+ waitingList.get(i).getConference().getRoomName() + ", " + t.toString(), 100, i);
					g.drawString("\n      equipment: ", 100, counter);
					for (int i2 = 0; i2 < waitingList.get(i).getEquipment().size(); i2++)
					{
						if (waitingList.get(i).getEquipment().get(i2) != null)
						{
							counter = counter + 20;
							g.drawString(waitingList.get(i).getEquipment().get(i2).toString() + ", ", 100, counter);
						}
					}
				}
				g.drawString("\n========================================================", 100, 800);
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			return PAGE_EXISTS;
		} else
		{
			JOptionPane.showMessageDialog(this, "Please Make A Report Selection");
		}
		return PAGE_EXISTS;
	}
}
