/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.gui;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import com.jpii.navalbattle.data.Constants;

public class LoginOptionsWindow extends Window {
	private static final long serialVersionUID = 1L;
	private JTextField txtHttproketgamercocc;
	
	/**
	 * <code>LoginOptionsWindow</code> constructor.
	 */
	public LoginOptionsWindow() {
		getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("NavalBattle");
		JLabel lblSubtitle = new JLabel("Launch Options");
		txtHttproketgamercocc = new JTextField();
		JCheckBox chckbxEnableSplashScreens = new JCheckBox("Enable splash screens");
		chckbxEnableSplashScreens.setSelected(true);
		JCheckBox chckbxDebugMode = new JCheckBox("Debug mode");
		if(Constants.DEBUG_MODE)
			chckbxDebugMode.setSelected(true);
		JLabel lblGeneral = new JLabel("General");
		JLabel lblRoketgamer = new JLabel("RoketGamer");
		JLabel lblServer = new JLabel("Server Address");
		JButton btnSave = new JButton("Save");
		JCheckBox chckbxSaveLastUsername = new JCheckBox("Remember username");
		chckbxSaveLastUsername.setSelected(true);
		
		txtHttproketgamercocc.setText("http://roketgamer.com");
		txtHttproketgamercocc.setColumns(10);
		
		lblTitle.setBounds(10, 11, 79, 17);
		lblSubtitle.setBounds(96, 14, 89, 14);
		txtHttproketgamercocc.setBounds(130, 135, 145, 30);
		chckbxEnableSplashScreens.setBounds(30, 60, 160, 23);
		chckbxDebugMode.setBounds(30, 86, 123, 23);
		lblGeneral.setBounds(20, 39, 46, 14);
		lblRoketgamer.setBounds(20, 119, 79, 14);
		lblServer.setBounds(30, 144, 102, 14);
		chckbxSaveLastUsername.setBounds(30, 172, 155, 23);
		btnSave.setBounds(386, 277, 90, 30);
		
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
		getContentPane().add(btnSave);
		getContentPane().add(chckbxSaveLastUsername);
		
		btnSave.setFocusable(false);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				nextWindow("LoginWindow");
			}
		});
	}
}
