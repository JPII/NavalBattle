package com.jpii.navalbattle.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.jpii.navalbattle.NavalBattle;

public class SPOptions {
	JFrame f;
	
	public SPOptions() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		f = new JFrame();
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);
		
		JButton btnOpenSave = new JButton("Open Save");
		btnOpenSave.setBounds(112, 11, 89, 23);
		f.getContentPane().add(btnOpenSave);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 44, 455, 5);
		f.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 43, 455, 5);
		f.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 45, 455, 5);
		f.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 42, 455, 5);
		f.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 239, 455, 5);
		f.getContentPane().add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(150, 44, 21, 195);
		f.getContentPane().add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(324, 43, 21, 195);
		f.getContentPane().add(separator_6);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel lblOpenTheCurrently = new JLabel("Open the currently saved game.");
		lblOpenTheCurrently.setBounds(211, 15, 162, 14);
		f.getContentPane().add(lblOpenTheCurrently);
		
		JLabel lblNewGame = new JLabel("New Game");
		lblNewGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewGame.setBounds(189, 120, 106, 33);
		f.getContentPane().add(lblNewGame);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ButtonGroup water = new ButtonGroup();
		JRadioButton rdbtnWater = new JRadioButton("60% Water");
		rdbtnWater.setBounds(39, 114, 109, 23);
		water.add(rdbtnWater);
		f.getContentPane().add(rdbtnWater);
		
		JRadioButton rdbtnWater_1 = new JRadioButton("70% Water");
		rdbtnWater_1.setBounds(39, 155, 109, 23);
		water.add(rdbtnWater_1);
		f.getContentPane().add(rdbtnWater_1);
		
		JRadioButton rdbtnWater_2 = new JRadioButton("80% Water");
		rdbtnWater_2.setBounds(39, 193, 109, 23);
		water.add(rdbtnWater_2);
		f.getContentPane().add(rdbtnWater_2);
		rdbtnWater.setSelected(true);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ButtonGroup difficulty = new ButtonGroup();
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBounds(81, 255, 21, 23);
		difficulty.add(radioButton);
		f.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(138, 255, 21, 23);
		difficulty.add(radioButton_1);
		f.getContentPane().add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(194, 255, 21, 23);
		difficulty.add(radioButton_2);
		f.getContentPane().add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("");
		radioButton_3.setBounds(251, 255, 21, 23);
		difficulty.add(radioButton_3);
		f.getContentPane().add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("");
		radioButton_4.setBounds(307, 255, 21, 23);
		difficulty.add(radioButton_4);
		f.getContentPane().add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("");
		radioButton_5.setBounds(364, 255, 21, 23);
		difficulty.add(radioButton_5);
		f.getContentPane().add(radioButton_5);
		radioButton.setSelected(true);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel lblPioneer = new JLabel("Pioneer");
		lblPioneer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPioneer.setBounds(69, 276, 46, 14);
		f.getContentPane().add(lblPioneer);
		
		JLabel lblSeawolf = new JLabel("Seawolf");
		lblSeawolf.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeawolf.setBounds(125, 276, 46, 14);
		f.getContentPane().add(lblSeawolf);
		
		JLabel lblSentry = new JLabel("Sentry");
		lblSentry.setHorizontalAlignment(SwingConstants.CENTER);
		lblSentry.setBounds(182, 276, 46, 14);
		f.getContentPane().add(lblSentry);
		
		JLabel lblFirebolt = new JLabel("Firebolt");
		lblFirebolt.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirebolt.setBounds(238, 276, 46, 14);
		f.getContentPane().add(lblFirebolt);
		
		JLabel lblDefender = new JLabel("Defender");
		lblDefender.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefender.setBounds(294, 276, 46, 14);
		f.getContentPane().add(lblDefender);
		
		JLabel lblBattleship = new JLabel("Battleship");
		lblBattleship.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleship.setBounds(350, 276, 53, 14);
		f.getContentPane().add(lblBattleship);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setBounds(213, 239, 46, 14);
		f.getContentPane().add(lblDifficulty);
		
		JLabel lblEasiest = new JLabel("Easiest");
		lblEasiest.setBounds(10, 264, 46, 14);
		f.getContentPane().add(lblEasiest);
		
		JLabel lblHardest = new JLabel("Hardest");
		lblHardest.setBounds(407, 264, 46, 14);
		f.getContentPane().add(lblHardest);
		
		JLabel lblAmmountOf = new JLabel("Ammount of");
		lblAmmountOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmmountOf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmmountOf.setBounds(20, 59, 106, 14);
		f.getContentPane().add(lblAmmountOf);
		
		JLabel lblLandmass = new JLabel("Landmass");
		lblLandmass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLandmass.setHorizontalAlignment(SwingConstants.CENTER);
		lblLandmass.setBounds(39, 82, 76, 14);
		f.getContentPane().add(lblLandmass);
		
		JLabel lblNumberOf = new JLabel("Number of");
		lblNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumberOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOf.setBounds(364, 58, 66, 14);
		f.getContentPane().add(lblNumberOf);
		
		JLabel lblOpponets = new JLabel("Opponents");
		lblOpponets.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOpponets.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpponets.setBounds(355, 81, 75, 16);
		f.getContentPane().add(lblOpponets);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ButtonGroup opponents = new ButtonGroup();
		JRadioButton radioButton_6 = new JRadioButton("3");
		radioButton_6.setBounds(376, 116, 38, 23);
		opponents.add(radioButton_6);
		f.getContentPane().add(radioButton_6);
		
		JRadioButton radioButton_7 = new JRadioButton("4");
		radioButton_7.setBounds(376, 142, 38, 23);
		opponents.add(radioButton_7);
		f.getContentPane().add(radioButton_7);
		
		JRadioButton radioButton_8 = new JRadioButton("5");
		radioButton_8.setBounds(376, 167, 38, 23);
		opponents.add(radioButton_8);
		f.getContentPane().add(radioButton_8);
		
		JRadioButton radioButton_9 = new JRadioButton("6");
		radioButton_9.setBounds(376, 193, 38, 23);
		opponents.add(radioButton_9);
		f.getContentPane().add(radioButton_9);
		radioButton_6.setSelected(true);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setBounds(193, 155, 89, 23);
		f.getContentPane().add(btnNewButton);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
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
}
