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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.game.SinglePlayerGame;
import com.jpii.navalbattle.gui.listeners.*;
import com.jpii.navalbattle.io.NavalBattleIO;
import com.jpii.navalbattle.pavo.PavoOpenState;
import com.jpii.navalbattle.renderer.Helper;

@SuppressWarnings("serial")
public class MainMenuWindow extends Window {
	JButton btnRoketGamer;
	SinglePlayerGame spg;
	//PavoOpenState pos = PavoOpenState.NORMAL;
	//String args = null;
	
	/**
	 * <code>MainMenuWindow</code> constructor.
	 */
	public MainMenuWindow() {
		super();
		
		spg = new SinglePlayerGame();
		
		getContentPane().setLayout(null);
		
		JLabel lblVersion = new JLabel(Constants.NAVALBATTLE_VERSION_TITLE);
		lblVersion.setForeground(Color.WHITE);
		JLabel lblNavalBattle = new JLabel("NavalBattle");
		JButton btnSingleplayer = new JButton("Singleplayer");
		JButton btnHelp = new JButton("Help");
		btnRoketGamer = new JButton("RoketGamer");
		JButton btnQuit = new JButton("Quit");
		JButton btnCredits = new JButton("Credits");
		JButton btnMultiplayer = new JButton("Multiplayer");
		
		lblNavalBattle.setBounds(10, 13, 466, 51);
		lblVersion.setBounds(10, 287, 238, 14);
		btnSingleplayer.setBounds(194, 74, 100, 30);
		btnHelp.setBounds(194, 141, 100, 30);
		btnRoketGamer.setBounds(194, 175, 100, 30);
		btnQuit.setBounds(194, 209, 100, 30);
		btnCredits.setBounds(375, 267, 100, 30);
		btnMultiplayer.setBounds(194, 107, 100, 30);
		
		lblNavalBattle.setForeground(Color.WHITE);
		lblNavalBattle.setFont(Helper.GUI_MENU_TITLE_FONT);
		//btnMultiplayer.setEnabled(false);
		lblNavalBattle.setHorizontalAlignment(SwingConstants.CENTER);
		
		getContentPane().add(lblVersion);
		getContentPane().add(lblNavalBattle);
		getContentPane().add(btnSingleplayer);
		getContentPane().add(btnHelp);
		getContentPane().add(btnRoketGamer);
		getContentPane().add(btnQuit);
		getContentPane().add(btnCredits);
		getContentPane().add(btnMultiplayer);
		
		btnSingleplayer.setFocusable(false);
		btnMultiplayer.setFocusable(false);
		btnHelp.setFocusable(false);
		btnRoketGamer.setFocusable(false);
		btnQuit.setFocusable(false);
		btnCredits.setFocusable(false);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainMenuWindow.class.getResource("/com/jpii/navalbattle/res/drawable-gui/menu_background.png")));
		label.setBounds(-83, -62, 569, 374);
		getContentPane().add(label);
		
		btnSingleplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				spg.setGameVars(PavoOpenState.NORMAL,null);
				spg.setVisible(true);
				NavalBattle.getWindowHandler().killAll();
			}
		});		
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextWindow("HelpWindow");
			}
		});
			btnRoketGamer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(!NavalBattle.getGameState().isOffline()) {
						dispose();
						new RoketGamerWindow();
					}
				}
			});
		btnQuit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				WindowCloser.close();
			}
		});
		btnCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextWindow("CreditsWindow");
			}
		});
		btnMultiplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//NavalBattle.getDebugWindow().printWarning("Multiplayer has not been implemented");
				boolean valid = false;
				String ip = NavalBattleIO.getAttribute("lastGoodIP");
				while (!valid) {
					ip = JOptionPane.showInputDialog(null,(Object)"Enter IP address to connect to:",(Object)ip);
					if (ip == null)
						return;
					if (ip.equals(""))
						valid = false;
					//else if (ip.equals("127.0.0.1") || ip.equals("localhost")) {
					//	JOptionPane.showMessageDialog(null,"Not permitted to connect to self.","JR says NO!",JOptionPane.WARNING_MESSAGE);
					//	valid = false;
					//
					else
						valid = validate(ip);
				}
				NavalBattleIO.saveAttribute("lastGoodIP", ip);
				spg.setGameVars(PavoOpenState.OPEN_SERVER,ip);
				spg.setVisible(true);
				NavalBattle.getWindowHandler().killAll();
			}
		});
	}
	
	private static final String PATTERN = 
	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	public static boolean validate(final String ip){          

	      Pattern pattern = Pattern.compile(PATTERN);
	      Matcher matcher = pattern.matcher(ip);
	      return matcher.matches();             
	}
	
	/**
	 * Set visible.
	 */
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(isVisible()){
			if(NavalBattle.getGameState().isOffline()) {
				btnRoketGamer.setEnabled(false);
			}
		}
	}
}