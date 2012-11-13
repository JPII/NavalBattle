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

import javax.swing.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.util.URLUtils;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class HelpWindow extends JFrame {
	private JLabel lblTitle;

	public HelpWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){}
		
		setTitle("NavalBattle");
		getContentPane().setLayout(null);

		this.setIconImage(Helper.GUI_WINDOW_ICON);
		
		lblTitle = new JLabel("NavalBattle Help");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 11, 121, 23);
		getContentPane().add(lblTitle);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing HelpWindow");
				dispose();
				NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
				new MainMenuWindow();
			}
		});
		btnClose.setBounds(10, 137, 59, 23);
		getContentPane().add(btnClose);
		
		JLabel lblYoutubeTutorials = new JLabel("YouTube Tutorials");
		lblYoutubeTutorials.setBounds(20, 36, 92, 14);
		getContentPane().add(lblYoutubeTutorials);
		
		JButton btnVideoOverview = new JButton("Overview");
		btnVideoOverview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening overview tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVideoOverview.setBounds(30, 61, 82, 23);
		getContentPane().add(btnVideoOverview);
		
		JButton btnVideoNavigation = new JButton("Navigation");
		btnVideoNavigation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening navigation tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoNavigation.setBounds(122, 61, 89, 23);
		getContentPane().add(btnVideoNavigation);
		
		JButton btnVideoCombat = new JButton("Combat");
		btnVideoCombat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening combat tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoCombat.setBounds(221, 61, 89, 23);
		getContentPane().add(btnVideoCombat);
		
		JButton btnVideoUpgradeShop = new JButton("Upgrade Shop");
		btnVideoUpgradeShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening upgrade shop tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoUpgradeShop.setBounds(320, 61, 107, 23);
		getContentPane().add(btnVideoUpgradeShop);
		
		JButton btnVideoMultiplayer = new JButton("Multiplayer");
		btnVideoMultiplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening Multiplayer tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoMultiplayer.setBounds(111, 95, 100, 23);
		getContentPane().add(btnVideoMultiplayer);
		
		JButton btnVideoRoketgamer = new JButton("RoketGamer");
		btnVideoRoketgamer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening RoketGamer tutorial");
				URLUtils.openURL("http://www.google.com");
			}
		});
		btnVideoRoketgamer.setBounds(221, 95, 100, 23);
		getContentPane().add(btnVideoRoketgamer);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});

		setSize(475,199);
		setVisible(true);
		setResizable(false);
		setLocation(1280/2-getWidth()/2,800/2-getHeight()/2);
	}

	public JFrame getFrame() {
		return this;
	}
}