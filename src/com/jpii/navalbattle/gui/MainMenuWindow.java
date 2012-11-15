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

import java.awt.*;
import java.awt.event.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;

@SuppressWarnings("serial")
public class MainMenuWindow extends Window {
	Timer ticker;
	MenuBackground backgrnd;
	int ticks;
	
	public MainMenuWindow() {
		super();
		
		getContentPane().setLayout(null);
		
	//	backgrnd = new MenuBackground(491,339,2);
	//	backgrnd.setLocation(0, 0);
	//	backgrnd.setSize(491,339);
	//	setContentPane(backgrnd);

		JLabel lblVersion = new JLabel(Constants.NAVALBATTLE_VERSION_TITLE);
		lblVersion.setBounds(10, 276, 238, 14);
		this.getContentPane().add(lblVersion);

		JLabel lblNavalBattle = new JLabel("NavalBattle");
		lblNavalBattle.setForeground(Color.blue);
		lblNavalBattle.setFont(new Font("RingBearer", Font.BOLD, 35));
		lblNavalBattle.setBounds(158, 13, 193, 51);
		this.getContentPane().add(lblNavalBattle);
		
		JButton btnSingleplayer = new JButton("Singleplayer");
		btnSingleplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing MainMenuWindow");
				dispose();
				NavalBattle.getDebugWindow().printInfo("Opening SPOptions");
				new SPOptions();
			}
		});
		btnSingleplayer.setBackground(new Color(255,255,255,255));
		btnSingleplayer.setBounds(194, 73, 100, 25);
		this.getContentPane().add(btnSingleplayer);

		JButton btnHelp = new JButton("Help");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing MainMenuWindow");
				dispose();
				NavalBattle.getDebugWindow().printInfo("Opening HelpWindow");
				new HelpWindow();
			}
		});
		
		btnHelp.setBounds(194, 141, 100, 25);
		this.getContentPane().add(btnHelp);

		JButton btnRoketGamer = new JButton("RoketGamer");;
		btnRoketGamer.setBounds(194, 175, 100, 25);
		if(NavalBattle.getGameState().isOffline()) {
			btnRoketGamer.setEnabled(false);
		} else {
			btnRoketGamer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					NavalBattle.getDebugWindow().printInfo("Disposing MainMenuWindow");
					dispose();
					NavalBattle.getDebugWindow().printInfo("Opening RoketGamerWindow");
					new RoketGamerWindow();
				}
			});
		}
		
		this.getContentPane().add(btnRoketGamer);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.close();
			}
		});
		btnQuit.setBounds(194, 209, 100, 25);
		this.getContentPane().add(btnQuit);

		JButton btnCredits = new JButton("Credits");
		btnCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing MainMenuWindow");
				dispose();
				NavalBattle.getDebugWindow().printInfo("Opening CreditsWindow");
				new CreditsWindow();
			}
		});
		btnCredits.setBounds(389, 267, 76, 23);
		this.getContentPane().add(btnCredits);
		
		JButton btnMultiplayer = new JButton("Multiplayer");
		btnMultiplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printWarning("Multiplayer has not been implemented");
			}
		});
		btnMultiplayer.setBounds(194, 107, 100, 25);
		this.getContentPane().add(btnMultiplayer);
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				backgrnd.tick();
				
				ticks += 100;
				
				backgrnd.invalidate();
				
				repaint();
			}
		};
		ticker = new Timer(100,listener);
		ticker.start();
	}
}