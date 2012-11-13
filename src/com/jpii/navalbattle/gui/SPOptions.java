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
import com.jpii.navalbattle.game.*;
import com.jpii.navalbattle.renderer.Helper;


@SuppressWarnings("serial")
public class SPOptions extends JFrame{
	JRadioButton radioButton;
	JRadioButton radioButton_1;
	JRadioButton radioButton_2;
	JRadioButton radioButton_3;
	JRadioButton radioButton_4;
	JRadioButton radioButton_5;
	JRadioButton radioButton_6;
	JRadioButton radioButton_7;
	JRadioButton radioButton_8;
	JRadioButton radioButton_9;
	JRadioButton rdbtnWater;
	JRadioButton rdbtnWater_1;
	JRadioButton rdbtnWater_2;
	
	public SPOptions() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		setTitle("NavalBattle");
		getContentPane().setLayout(null);
		this.setIconImage(Helper.GUI_WINDOW_ICON);
		JButton btnOpenSave = new JButton("Open Save");
		btnOpenSave.setBounds(193, 11, 89, 23);
		getContentPane().add(btnOpenSave);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 44, 455, 5);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 43, 455, 5);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 45, 455, 5);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 42, 455, 5);
		getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 239, 455, 5);
		getContentPane().add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(150, 44, 21, 195);
		getContentPane().add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(324, 43, 21, 195);
		getContentPane().add(separator_6);
		
		JLabel lblNewGame = new JLabel("New Game");
		lblNewGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewGame.setBounds(189, 120, 106, 33);
		getContentPane().add(lblNewGame);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ButtonGroup water = new ButtonGroup();
		rdbtnWater = new JRadioButton("60% Water");
		rdbtnWater.setBounds(39, 114, 109, 23);
		water.add(rdbtnWater);
		rdbtnWater.addKeyListener(new KeyboardListener(this));
		getContentPane().add(rdbtnWater);
		
		rdbtnWater_1 = new JRadioButton("70% Water");
		rdbtnWater_1.setBounds(39, 155, 109, 23);
		water.add(rdbtnWater_1);
		rdbtnWater_1.addKeyListener(new KeyboardListener(this));
		getContentPane().add(rdbtnWater_1);
		
		rdbtnWater_2 = new JRadioButton("80% Water");
		rdbtnWater_2.setBounds(39, 193, 109, 23);
		water.add(rdbtnWater_2);
		getContentPane().add(rdbtnWater_2);
		rdbtnWater_2.addKeyListener(new KeyboardListener(this));
		rdbtnWater.setSelected(true);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ButtonGroup difficulty = new ButtonGroup();
		radioButton = new JRadioButton("");
		radioButton.setBounds(81, 255, 21, 23);
		difficulty.add(radioButton);
		radioButton.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(138, 255, 21, 23);
		difficulty.add(radioButton_1);
		radioButton_1.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_1);
		
		radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(194, 255, 21, 23);
		difficulty.add(radioButton_2);
		radioButton_2.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_2);
		
		radioButton_3 = new JRadioButton("");
		radioButton_3.setBounds(251, 255, 21, 23);
		difficulty.add(radioButton_3);
		radioButton_3.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_3);
		
		radioButton_4 = new JRadioButton("");
		radioButton_4.setBounds(307, 255, 21, 23);
		difficulty.add(radioButton_4);
		radioButton_4.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_4);
		
		radioButton_5 = new JRadioButton("");
		radioButton_5.setBounds(364, 255, 21, 23);
		difficulty.add(radioButton_5);
		radioButton_5.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_5);
		radioButton.setSelected(true);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel lblPioneer = new JLabel("Pioneer");
		lblPioneer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPioneer.setBounds(69, 276, 46, 14);
		getContentPane().add(lblPioneer);
		
		JLabel lblSeawolf = new JLabel("Seawolf");
		lblSeawolf.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeawolf.setBounds(125, 276, 46, 14);
		getContentPane().add(lblSeawolf);
		
		JLabel lblSentry = new JLabel("Sentry");
		lblSentry.setHorizontalAlignment(SwingConstants.CENTER);
		lblSentry.setBounds(182, 276, 46, 14);
		getContentPane().add(lblSentry);
		
		JLabel lblFirebolt = new JLabel("Firebolt");
		lblFirebolt.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirebolt.setBounds(238, 276, 46, 14);
		getContentPane().add(lblFirebolt);
		
		JLabel lblDefender = new JLabel("Defender");
		lblDefender.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefender.setBounds(294, 276, 46, 14);
		getContentPane().add(lblDefender);
		
		JLabel lblBattleship = new JLabel("Battleship");
		lblBattleship.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleship.setBounds(350, 276, 53, 14);
		getContentPane().add(lblBattleship);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setBounds(213, 239, 46, 14);
		getContentPane().add(lblDifficulty);
		
		JLabel lblEasiest = new JLabel("Easiest");
		lblEasiest.setBounds(10, 264, 46, 14);
		getContentPane().add(lblEasiest);
		
		JLabel lblHardest = new JLabel("Hardest");
		lblHardest.setBounds(407, 264, 46, 14);
		getContentPane().add(lblHardest);
		
		JLabel lblAmmountOf = new JLabel("Ammount of");
		lblAmmountOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmmountOf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmmountOf.setBounds(20, 59, 106, 14);
		getContentPane().add(lblAmmountOf);
		
		JLabel lblLandmass = new JLabel("Landmass");
		lblLandmass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLandmass.setHorizontalAlignment(SwingConstants.CENTER);
		lblLandmass.setBounds(39, 82, 76, 14);
		getContentPane().add(lblLandmass);
		
		JLabel lblNumberOf = new JLabel("Number of");
		lblNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOf.setBounds(364, 58, 66, 14);
		getContentPane().add(lblNumberOf);
		
		JLabel lblOpponets = new JLabel("Opponents");
		lblOpponets.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOpponets.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpponets.setBounds(355, 81, 75, 16);
		getContentPane().add(lblOpponets);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ButtonGroup opponents = new ButtonGroup();
		radioButton_6 = new JRadioButton("3");
		radioButton_6.setBounds(376, 116, 38, 23);
		opponents.add(radioButton_6);
		radioButton_6.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_6);
		
		radioButton_7 = new JRadioButton("4");
		radioButton_7.setBounds(376, 142, 38, 23);
		opponents.add(radioButton_7);
		radioButton_7.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_7);
		
		radioButton_8 = new JRadioButton("5");
		radioButton_8.setBounds(376, 167, 38, 23);
		opponents.add(radioButton_8);
		radioButton_8.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_8);
		
		radioButton_9 = new JRadioButton("6");
		radioButton_9.setBounds(376, 193, 38, 23);
		opponents.add(radioButton_9);
		radioButton_9.addKeyListener(new KeyboardListener(this));
		getContentPane().add(radioButton_9);
		radioButton_6.setSelected(true);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setBounds(193, 155, 89, 23);		
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				saveOptions();
				NavalBattle.getDebugWindow().printInfo("Saving Data...");
				NavalBattle.getDebugWindow().printInfo("NumShips: " + NavalBattle.getGameState().getNumShips());
				NavalBattle.getDebugWindow().printInfo("WaterLevel: " + NavalBattle.getGameState().getWaterLevel());
				NavalBattle.getDebugWindow().printInfo("Difficulty: " + NavalBattle.getGameState().getDifficulty());
				
				NavalBattle.getDebugWindow().printInfo("Disposing SPOptions");
				dispose();
				NavalBattle.getDebugWindow().printInfo("Opening SinglePlayerGame");
				new SinglePlayerGame();
			}
		});
		getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(39, 11, 89, 23);
		getContentPane().add(btnBack);
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
				new MainMenuWindow();
				NavalBattle.getDebugWindow().printInfo("Disposing SPOptions");
				dispose();
			}
		});
		
		JButton btnAdvancedOptions = new JButton("Advanced Options");
		btnAdvancedOptions.setBounds(324, 11, 141, 23);
		getContentPane().add(btnAdvancedOptions);
		btnAdvancedOptions.addKeyListener(new KeyboardListener(this));
		btnAdvancedOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening Advanced Options");
			}
		});
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		setSize(491,339);
		setVisible(true);
		
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});
		
		setFocusable(true);
		setResizable(false);
		setLocation(1280/2-getWidth()/2,800/2-getHeight()/2);
		addKeyListener(new KeyboardListener(this));
	}
	
	private void saveOptions() {

		if(rdbtnWater.isSelected()) {
			NavalBattle.getGameState().setWaterLevel(60);
		}
		else if(rdbtnWater_1.isSelected()) {
			NavalBattle.getGameState().setWaterLevel(70);
		}
		else if(rdbtnWater_2.isSelected()) {
			NavalBattle.getGameState().setWaterLevel(80);
		}
		else {
			NavalBattle.getDebugWindow().printError("No water level selected");
		}
		
		if(radioButton_6.isSelected()) {
			NavalBattle.getGameState().setNumShips(Integer.parseInt(radioButton_6.getText()));
		}
		else if(radioButton_7.isSelected()) {
			NavalBattle.getGameState().setNumShips(Integer.parseInt(radioButton_7.getText()));
		}
		else if(radioButton_8.isSelected()) {
			NavalBattle.getGameState().setNumShips(Integer.parseInt(radioButton_8.getText()));
		}
		else if(radioButton_9.isSelected()) {
			NavalBattle.getGameState().setNumShips(Integer.parseInt(radioButton_9.getText()));
		}
		else {
			NavalBattle.getDebugWindow().printError("No number of Ships selected");
		}
		
		if(radioButton.isSelected()) {
			NavalBattle.getGameState().setDifficulty(1);
		}
		else if(radioButton_1.isSelected()) {
			NavalBattle.getGameState().setDifficulty(2);
		}
		else if(radioButton_2.isSelected()) {
			NavalBattle.getGameState().setDifficulty(3);
		}
		else if(radioButton_3.isSelected()) {
			NavalBattle.getGameState().setDifficulty(4);
		}
		else if(radioButton_4.isSelected()) {
			NavalBattle.getGameState().setDifficulty(5);
		}
		else if(radioButton_5.isSelected()) {
			NavalBattle.getGameState().setDifficulty(6);
		}
		
	}
}
