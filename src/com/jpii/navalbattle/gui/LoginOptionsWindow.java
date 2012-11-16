package com.jpii.navalbattle.gui;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import com.jpii.navalbattle.NavalBattle;

@SuppressWarnings("serial")
public class LoginOptionsWindow extends Window {
	
	private JTextField txtHttproketgamercocc;
	
	public LoginOptionsWindow() {
		getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("NavalBattle");
		JLabel lblSubtitle = new JLabel("Launch Options");
		txtHttproketgamercocc = new JTextField();
		JCheckBox chckbxEnableSplashScreens = new JCheckBox("Enable splash screens");
		JCheckBox chckbxDebugMode = new JCheckBox("Debug mode");
		JLabel lblGeneral = new JLabel("General");
		JLabel lblRoketgamer = new JLabel("RoketGamer");
		JLabel lblServer = new JLabel("Server Address");
		JButton btnSave = new JButton("Save");
		JCheckBox chckbxSaveLastUsername = new JCheckBox("Remember username");
		JCheckBox chckbxAnalytics = new JCheckBox("Analytics");
		
		txtHttproketgamercocc.setText("http://roketgamer.co.cc");
		txtHttproketgamercocc.setColumns(10);
		
		lblTitle.setBounds(10, 11, 79, 17);
		lblSubtitle.setBounds(96, 14, 89, 14);
		txtHttproketgamercocc.setBounds(130, 137, 145, 27);
		chckbxEnableSplashScreens.setBounds(30, 60, 160, 23);
		chckbxDebugMode.setBounds(30, 86, 123, 23);
		lblGeneral.setBounds(20, 39, 46, 14);
		lblRoketgamer.setBounds(20, 119, 79, 14);
		lblServer.setBounds(30, 144, 102, 14);
		chckbxAnalytics.setBounds(30, 165, 102, 23);
		chckbxSaveLastUsername.setBounds(30, 191, 155, 23);
		btnSave.setBounds(386, 277, 89, 23);
		
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGeneral.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRoketgamer.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		getContentPane().add(lblTitle);
		getContentPane().add(lblSubtitle);		
		getContentPane().add(txtHttproketgamercocc);
		getContentPane().add(chckbxEnableSplashScreens);
		getContentPane().add(chckbxDebugMode);
		getContentPane().add(lblGeneral);
		getContentPane().add(lblRoketgamer);
		getContentPane().add(lblServer);
		getContentPane().add(chckbxAnalytics);
		getContentPane().add(btnSave);
		getContentPane().add(chckbxSaveLastUsername);
		
		btnSave.setFocusable(false);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				NavalBattle.getDebugWindow().printInfo("Disposing LoginOptionsWindow");
				dispose();
				new LoginWindow();
			}
		});
	}
}
