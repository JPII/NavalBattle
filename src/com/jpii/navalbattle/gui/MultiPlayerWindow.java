package com.jpii.navalbattle.gui;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JButton;

public class MultiPlayerWindow extends BaseWindow {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	
	public MultiPlayerWindow() {
		super();
		getContentPane().setLayout(null);
		
		JLabel lblWelcomeToSelect = new JLabel("Select a Multiplayer mode:");
		lblWelcomeToSelect.setBounds(10, 11, 203, 14);
		getContentPane().add(lblWelcomeToSelect);
		
		JRadioButton rdbtnLocalLan = new JRadioButton("Local LAN:");
		rdbtnLocalLan.setBounds(20, 32, 109, 23);
		getContentPane().add(rdbtnLocalLan);
		
		JRadioButton rdbtnRoketgamertm = new JRadioButton("RoketGamer (tm):");
		rdbtnRoketgamertm.setEnabled(false);
		rdbtnRoketgamertm.setBounds(20, 115, 123, 23);
		getContentPane().add(rdbtnRoketgamertm);
		
		JCheckBox chckbxClientsAreWatchers = new JCheckBox("Read-only mode");
		chckbxClientsAreWatchers.setBounds(46, 89, 184, 23);
		getContentPane().add(chckbxClientsAreWatchers);
		
		textField = new JTextField();
		textField.setBounds(107, 62, 123, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIpAddress = new JLabel("IP Address:");
		lblIpAddress.setBounds(46, 65, 74, 14);
		getContentPane().add(lblIpAddress);
		
		Choice choice = new Choice();
		choice.setEnabled(false);
		choice.setBounds(135, 144, 95, 20);
		choice.add("Dallas");
		choice.select(0);
		getContentPane().add(choice);
		
		JLabel lblSelectAServer = new JLabel("Select a server:");
		lblSelectAServer.setEnabled(false);
		lblSelectAServer.setBounds(46, 145, 83, 14);
		getContentPane().add(lblSelectAServer);
		
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.setBounds(50, 193, 89, 23);
		getContentPane().add(btnLaunch);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(189, 193, 89, 23);
		getContentPane().add(btnCancel);
	}
	
}
