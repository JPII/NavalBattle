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

@SuppressWarnings("serial")
public class CreditsWindow extends JFrame {
	private JLabel gameTitle;
	private JLabel licenseNotice;
	private JButton btnClose;
	private JLabel mattWaller;
	private JLabel zachMathewson;
	private JLabel anthonyRole;
	private JLabel thomasRole;

	public CreditsWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){}

		setTitle("NavalBattle");
		getContentPane().setLayout(null);

		gameTitle = new JLabel("NavalBattle");
		gameTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		gameTitle.setBounds(10, 11, 86, 14);
		getContentPane().add(gameTitle);

		licenseNotice = new JLabel("NavalBattle is open source under the GNU General Public License v3.");
		licenseNotice.setHorizontalAlignment(SwingConstants.CENTER);
		licenseNotice.setBounds(121, 232, 328, 14);
		getContentPane().add(licenseNotice);

		btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing CreditsWindow");
				dispose();
				NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
				new MainMenuWindow();
			}
		});
		btnClose.setBounds(10, 228, 89, 23);
		getContentPane().add(btnClose);
		
		JLabel anthonyBauer = new JLabel("Anthony \"abauer\" Bauer");
		anthonyBauer.setBounds(10, 36, 117, 14);
		getContentPane().add(anthonyBauer);
		
		JLabel thomasGaubert = new JLabel("Thomas \"TexasGamer\" Gaubert");
		thomasGaubert.setBounds(10, 61, 150, 14);
		getContentPane().add(thomasGaubert);
		
		JLabel maxKirkby = new JLabel("Max \"maximusvladimir\" Kirkby");
		maxKirkby.setBounds(10, 86, 140, 14);
		getContentPane().add(maxKirkby);
		
		JLabel jrVetus = new JLabel("JR \"DarkWarHero\" Vetus");
		jrVetus.setBounds(10, 111, 127, 14);
		getContentPane().add(jrVetus);
		
		mattWaller = new JLabel("Matt \"Matthis5point0\" Waller");
		mattWaller.setBounds(10, 136, 140, 14);
		getContentPane().add(mattWaller);
		
		zachMathewson = new JLabel("Zach \"smeagle42\" Mathewson");
		zachMathewson.setBounds(10, 161, 150, 14);
		getContentPane().add(zachMathewson);
		
		anthonyRole = new JLabel("Game design lead");
		anthonyRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		anthonyRole.setBounds(190, 36, 117, 14);
		getContentPane().add(anthonyRole);
		
		thomasRole = new JLabel("SCM manager, RoketGamer lead");
		thomasRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		thomasRole.setBounds(190, 61, 189, 14);
		getContentPane().add(thomasRole);
		
		JLabel maxRole = new JLabel("TBD");
		maxRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		maxRole.setBounds(190, 86, 46, 14);
		getContentPane().add(maxRole);
		
		JLabel jrRole = new JLabel("TBD");
		jrRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		jrRole.setBounds(190, 111, 46, 14);
		getContentPane().add(jrRole);
		
		JLabel mattRole = new JLabel("TBD");
		mattRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		mattRole.setBounds(190, 136, 46, 14);
		getContentPane().add(mattRole);
		
		JLabel zachRole = new JLabel("TBD");
		zachRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		zachRole.setBounds(190, 161, 46, 14);
		getContentPane().add(zachRole);
		
		JLabel gitHub = new JLabel("GitHub");
		gitHub.setBounds(10, 181, 46, 14);
		getContentPane().add(gitHub);
		
		JLabel roketGamer = new JLabel("RoketGamer");
		roketGamer.setBounds(10, 203, 59, 14);
		getContentPane().add(roketGamer);
		
		JLabel githubRole = new JLabel("Source code hosting, SCM");
		githubRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		githubRole.setBounds(190, 181, 156, 14);
		getContentPane().add(githubRole);
		
		JLabel roketgamerRole = new JLabel("Online social gaming");
		roketgamerRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		roketgamerRole.setBounds(190, 203, 117, 14);
		getContentPane().add(roketgamerRole);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});

		setSize(475,300);
		setVisible(true);
		setResizable(false);
		setLocation(1280/2-getWidth()/2,800/2-getHeight()/2);
	}

	public JFrame getFrame() {
		return this;
	}
}