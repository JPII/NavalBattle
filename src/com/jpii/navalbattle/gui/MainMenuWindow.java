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
import com.jpii.navalbattle.game.SPOptions;

public class MainMenuWindow {
	JFrame f;

	public MainMenuWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		f = new JFrame();
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);

		JLabel lblVersion = new JLabel(Constants.NAVALBATTLE_VERSION_TITLE);
		lblVersion.setBounds(10, 276, 238, 14);
		f.getContentPane().add(lblVersion);

		JLabel lblNavalBattle = new JLabel("NAVALBATTLE");
		lblNavalBattle.setFont(new Font("", Font.BOLD, 33));
		lblNavalBattle.setBounds(101, 11, 248, 51);
		f.getContentPane().add(lblNavalBattle);

		JButton btnSingleplayer = new JButton("Singleplayer");
		btnSingleplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing MainMenuWindow");
				f.dispose();
				NavalBattle.getDebugWindow().printInfo("Opening SPOptions");
				new SPOptions();
			}
		});
		btnSingleplayer.setBounds(177, 73, 99, 23);
		f.getContentPane().add(btnSingleplayer);

		JButton btnHelp = new JButton("Help");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing MainMenuWindow");
				f.dispose();
				NavalBattle.getDebugWindow().printInfo("Opening HelpWindow");
				new HelpWindow();
			}
		});
		btnHelp.setBounds(177, 141, 99, 23);
		f.getContentPane().add(btnHelp);

		JButton btnRoketGamer = new JButton("RoketGamer");;
		btnRoketGamer.setBounds(177, 175, 99, 23);
		if(NavalBattle.getGameState().isOffline()) {
			btnRoketGamer.setEnabled(false);
		} else {
			btnRoketGamer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					NavalBattle.getDebugWindow().printInfo("Disposing MainMenuWindow");
					f.dispose();
					NavalBattle.getDebugWindow().printInfo("Opening RoketGamerWindow");
					new RoketGamerWindow();
				}
			});
		}
		
		f.getContentPane().add(btnRoketGamer);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.close();
			}
		});
		btnQuit.setBounds(177, 209, 99, 23);
		f.getContentPane().add(btnQuit);

		JButton btnCredits = new JButton("Credits");
		btnCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing MainMenuWindow");
				f.dispose();
				NavalBattle.getDebugWindow().printInfo("Opening CreditsWindow");
				new CreditsWindow();
			}
		});
		btnCredits.setBounds(398, 267, 67, 23);
		f.getContentPane().add(btnCredits);
		
		JButton btnMultiplayer = new JButton("Multiplayer");
		btnMultiplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printWarning("Multiplayer has not been implemented");
			}
		});
		btnMultiplayer.setBounds(177, 107, 99, 23);
		f.getContentPane().add(btnMultiplayer);

		f.setSize(491,339);
		f.setVisible(true);
		f.setLocation(500,300);

		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});
		
		f.setFocusable(true);
		f.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent k) {	
				if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
					NavalBattle.close();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) { 
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
	}

	public JFrame getFrame() {
		return f;
	}
}