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

import java.awt.*;
import java.awt.event.*;

public class HelpWindow {
	JFrame f;
	private JLabel lblTitle;

	public HelpWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){}

		f = new JFrame();
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);

		lblTitle = new JLabel("NavalBattle Help");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 11, 121, 23);
		f.getContentPane().add(lblTitle);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing HelpWindow");
				f.dispose();
				NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
				new MainMenuWindow();
			}
		});
		btnClose.setBounds(10, 137, 59, 23);
		f.getContentPane().add(btnClose);
		
		JLabel lblYoutubeTutorials = new JLabel("YouTube Tutorials");
		lblYoutubeTutorials.setBounds(20, 36, 92, 14);
		f.getContentPane().add(lblYoutubeTutorials);
		
		JButton btnVideoOverview = new JButton("Overview");
		btnVideoOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVideoOverview.setBounds(30, 61, 82, 23);
		f.getContentPane().add(btnVideoOverview);
		
		JButton btnVideoNavigation = new JButton("Navigation");
		btnVideoNavigation.setBounds(122, 61, 89, 23);
		f.getContentPane().add(btnVideoNavigation);
		
		JButton btnVideoCombat = new JButton("Combat");
		btnVideoCombat.setBounds(221, 61, 89, 23);
		f.getContentPane().add(btnVideoCombat);
		
		JButton btnVideoUpgradeShop = new JButton("Upgrade Shop");
		btnVideoUpgradeShop.setBounds(320, 61, 107, 23);
		f.getContentPane().add(btnVideoUpgradeShop);
		
		JButton btnVideoMultiplayer = new JButton("Multiplayer");
		btnVideoMultiplayer.setBounds(111, 95, 100, 23);
		f.getContentPane().add(btnVideoMultiplayer);
		
		JButton btnVideoRoketgamer = new JButton("RoketGamer");
		btnVideoRoketgamer.setBounds(221, 95, 100, 23);
		f.getContentPane().add(btnVideoRoketgamer);
		
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});

		f.setSize(475,199);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocation(1280/2-f.getWidth()/2,800/2-f.getHeight()/2);
	}

	public JFrame getFrame() {
		return f;
	}
}