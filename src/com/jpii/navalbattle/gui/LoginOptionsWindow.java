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
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 11, 79, 17);
		getContentPane().add(lblTitle);
		
		JLabel lblSubtitle = new JLabel("Launch Options");
		lblSubtitle.setBounds(96, 14, 79, 14);
		getContentPane().add(lblSubtitle);
		
		txtHttproketgamercocc = new JTextField();
		txtHttproketgamercocc.setText("http://roketgamer.co.cc");
		txtHttproketgamercocc.setBounds(122, 141, 131, 20);
		getContentPane().add(txtHttproketgamercocc);
		txtHttproketgamercocc.setColumns(10);
		
		JCheckBox chckbxEnableSplashScreens = new JCheckBox("Enable splash screens");
		chckbxEnableSplashScreens.setBounds(30, 60, 131, 23);
		getContentPane().add(chckbxEnableSplashScreens);
		
		JCheckBox chckbxDebugMode = new JCheckBox("Debug mode");
		chckbxDebugMode.setBounds(30, 86, 85, 23);
		getContentPane().add(chckbxDebugMode);
		
		JLabel lblGeneral = new JLabel("General");
		lblGeneral.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGeneral.setBounds(20, 39, 46, 14);
		getContentPane().add(lblGeneral);
		
		JLabel lblRoketgamer = new JLabel("RoketGamer");
		lblRoketgamer.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRoketgamer.setBounds(20, 119, 79, 14);
		getContentPane().add(lblRoketgamer);
		
		JLabel lblServer = new JLabel("Server Address");
		lblServer.setBounds(33, 144, 79, 14);
		getContentPane().add(lblServer);
		
		JCheckBox chckbxAnalytics = new JCheckBox("Analytics");
		chckbxAnalytics.setBounds(30, 165, 69, 23);
		getContentPane().add(chckbxAnalytics);
		
		JCheckBox chckbxSaveLastUsername = new JCheckBox("Remember username");
		chckbxSaveLastUsername.setBounds(30, 191, 145, 23);
		getContentPane().add(chckbxSaveLastUsername);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(386, 277, 89, 23);
		getContentPane().add(btnSave);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				NavalBattle.getDebugWindow().printInfo("Disposing LoginOptionsWindow");
				dispose();
				NavalBattle.getDebugWindow().printInfo("Opening LoginWindow");
				new LoginWindow();
			}
		});
	}
}
