package deenicholas.delnovo.gui.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import deenicholas.delnovo.dto.BookingDTO;
import deenicholas.delnovo.dto.ConferenceRoomDTO;
import deenicholas.delnovo.dto.UserDTO;
import deenicholas.delnovo.gui.mainFrame.MainFrame;

public class WaitingListEquipment extends JPanel implements ActionListener
{

	private JButton jbYes, jbNo;
	private UserDTO user;
	private ConferenceRoomDTO conference;
	private String descrip;
	private int length, startH, startM, endH, endM, numOfAtten;
	private Date date;
	private ArrayList<String> equip;
	private JPanel mainPanel = MainFrame.mainPanel;
	
	private String overhead, tv, laptop, mic, amp, taperec, whiteboard, dvd;
	
	private String [ ] equipment;
	private int numberOfEquipment = 0;
	private JLabel lblAdditionalEquipment;
	private JCheckBox chckbxOverheadProjector;
	private JCheckBox chckbxTv;
	private JCheckBox chckbxLaptop;
	private JCheckBox chckbxMicrophone;
	private JCheckBox chckbxAmplifier;
	private JCheckBox chckbxTapeRecorder;
	private JCheckBox chckbxWhiteBoard;
	private JCheckBox chckbxDvdvcrMachine;
	private JLabel lblPleaseSelectAny;
	private JButton btnBookRoom;
	private JButton btnCancel;
	
	private String email = null;
	private BookingDTO bdto = null;
	
	/**
	 * Create the panel.
	 */
	public WaitingListEquipment(BookingDTO bdto, String email)
	{
		this.email = email;
		this.bdto = bdto;
		
		setBackground(SystemColor.activeCaption);
		
		lblAdditionalEquipment = new JLabel("Additional Equipment");
		lblAdditionalEquipment.setFont(new Font("Californian FB", Font.BOLD, 50));
		lblAdditionalEquipment.setBackground(new Color(240, 240, 240));
		
		chckbxOverheadProjector = new JCheckBox("Overhead Projector");
		chckbxOverheadProjector.setBackground(SystemColor.activeCaption);
		chckbxOverheadProjector.setFont(new Font("Californian FB", Font.PLAIN, 18));
		
		chckbxTv = new JCheckBox("TV");
		chckbxTv.setFont(new Font("Californian FB", Font.PLAIN, 18));
		chckbxTv.setBackground(SystemColor.activeCaption);
		
		chckbxLaptop = new JCheckBox("Laptop");
		chckbxLaptop.setFont(new Font("Californian FB", Font.PLAIN, 18));
		chckbxLaptop.setBackground(SystemColor.activeCaption);
		
		chckbxMicrophone = new JCheckBox("Microphone");
		chckbxMicrophone.setFont(new Font("Californian FB", Font.PLAIN, 18));
		chckbxMicrophone.setBackground(SystemColor.activeCaption);
		
		chckbxAmplifier = new JCheckBox("Amplifier");
		chckbxAmplifier.setFont(new Font("Californian FB", Font.PLAIN, 18));
		chckbxAmplifier.setBackground(SystemColor.activeCaption);
		
		chckbxTapeRecorder = new JCheckBox("Tape Recorder");
		chckbxTapeRecorder.setFont(new Font("Californian FB", Font.PLAIN, 18));
		chckbxTapeRecorder.setBackground(SystemColor.activeCaption);
		
		chckbxWhiteBoard = new JCheckBox("White Board");
		chckbxWhiteBoard.setFont(new Font("Californian FB", Font.PLAIN, 18));
		chckbxWhiteBoard.setBackground(SystemColor.activeCaption);
		
		chckbxDvdvcrMachine = new JCheckBox("DVD/VCR Machine");
		chckbxDvdvcrMachine.setFont(new Font("Californian FB", Font.PLAIN, 18));
		chckbxDvdvcrMachine.setBackground(SystemColor.activeCaption);
		
		lblPleaseSelectAny = new JLabel("Please select any equipment from below:");
		lblPleaseSelectAny.setFont(new Font("Californian FB", Font.PLAIN, 18));
		
		btnBookRoom = new JButton("Book Room");
		btnBookRoom.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnBookRoom.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Californian FB", Font.BOLD, 20));
		btnCancel.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxOverheadProjector)
						.addComponent(chckbxTv, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxLaptop, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxAmplifier, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxTapeRecorder, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxWhiteBoard, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdditionalEquipment)
						.addComponent(chckbxMicrophone, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPleaseSelectAny)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnCancel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBookRoom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(chckbxDvdvcrMachine, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdditionalEquipment)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPleaseSelectAny)
					.addGap(7)
					.addComponent(chckbxOverheadProjector)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxTv, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxLaptop, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxMicrophone, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxAmplifier, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxTapeRecorder, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxWhiteBoard, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxDvdvcrMachine, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBookRoom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		this.equip = new ArrayList<String>();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object source = arg0.getSource();
		
		if(source == btnBookRoom)
		{
			if(chckbxOverheadProjector.isSelected())
			{
				overhead = "Overhead Projector";
				equip.add(overhead);
			}
			
			if(chckbxTv.isSelected())
			{
				tv = "TV";
				equip.add(tv);
			}
			
			if(chckbxLaptop.isSelected())
			{
				laptop = "Laptop";
				equip.add(laptop);
			}
			
			if(chckbxMicrophone.isSelected())
			{
				mic = "Mic";
				equip.add(mic);
			}
			
			if(chckbxAmplifier.isSelected())
			{
				amp = "Amplifier";
				equip.add(amp);
			}
			
			if(chckbxTapeRecorder.isSelected())
			{
				taperec = "Tape Recorder";
				equip.add(taperec);
			}
			
			if(chckbxWhiteBoard.isSelected())
			{
				whiteboard = "White Board";
				equip.add(whiteboard);
			}
			
			if(chckbxDvdvcrMachine.isSelected())
			{
				dvd = "DVD/VCR Machine";
				equip.add(dvd);
			}
			
			try
			{				
				bdto.setEquipment(equip);
				boolean tf = MainFrame.cm.putOnWaitingList(bdto, email);
				if(tf)
				{
					JOptionPane.showMessageDialog(this, "Successfully Added to Waiting List");
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Add to waiting list unsuccessful");
					return;
				}
			} catch (HeadlessException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			MainMenu menu = new MainMenu();
			mainPanel.add(menu);
			mainPanel.validate();
			mainPanel.repaint();
		}
		
		if(source == btnCancel)
		{
			mainPanel.removeAll();
			mainPanel.validate();
			mainPanel.repaint();
			PickARoom par = new PickARoom();
			mainPanel.add(par);
			mainPanel.validate();
			mainPanel.repaint();
		}
	}

}
