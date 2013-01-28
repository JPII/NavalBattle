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

@SuppressWarnings("serial")
public class CreditsWindow extends Window {
	private JLabel gameTitle;
	private JLabel licenseNotice;
	private JButton btnClose;
	private JLabel mattWaller;
	private JLabel zachMathewson;
	private JLabel anthonyRole;
	private JLabel thomasRole;

	public CreditsWindow() {

		getContentPane().setLayout(null);
		gameTitle = new JLabel("NavalBattle");
		licenseNotice = new JLabel("NavalBattle is open source under the GNU General Public License v3.");
		btnClose = new JButton("Close");
		JLabel anthonyBauer = new JLabel("Anthony \"abauer\" Bauer");
		JLabel thomasGaubert = new JLabel("Thomas \"TexasGamer\" Gaubert");
		JLabel maxKirkby = new JLabel("Max \"maximusvladimir\" Kirkby");
		JLabel jrVetus = new JLabel("JR \"DarkWarHero\" Vetus");
		mattWaller = new JLabel("Matt \"Matthis5point0\" Waller");
		zachMathewson = new JLabel("Zach \"smeagle42\" Mathewson");
		anthonyRole = new JLabel("Game design lead");
		thomasRole = new JLabel("SCM manager, RoketGamer lead");
		JLabel maxRole = new JLabel("Rendering lead");
		JLabel jrRole = new JLabel("AI lead");
		JLabel mattRole = new JLabel("QA lead, game-play lead");
		JLabel zachRole = new JLabel("art lead, music lead");
		JLabel gitHub = new JLabel("GitHub");
		JLabel roketGamer = new JLabel("RoketGamer");
		JLabel githubRole = new JLabel("Source code hosting, SCM");
		JLabel roketgamerRole = new JLabel("Online social gaming");

		gameTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		anthonyRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		thomasRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		maxRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		jrRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		mattRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		zachRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		githubRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		roketgamerRole.setFont(new Font("Tahoma", Font.BOLD, 11));

		gameTitle.setBounds(10, 11, 86, 14);
		licenseNotice.setBounds(0, 292, 492, 15);
		btnClose.setBounds(10, 251, 90, 30);
		anthonyBauer.setBounds(10, 36, 209, 14);
		thomasGaubert.setBounds(10, 61, 209, 14);
		maxKirkby.setBounds(10, 86, 209, 14);
		jrVetus.setBounds(10, 111, 220, 14);
		mattWaller.setBounds(10, 135, 220, 14);
		zachMathewson.setBounds(10, 161, 209, 14);
		anthonyRole.setBounds(260, 37, 117, 14);
		thomasRole.setBounds(260, 62, 189, 14);
		maxRole.setBounds(260, 87, 189, 14);
		jrRole.setBounds(260, 112, 189, 14);
		mattRole.setBounds(260, 137, 189, 14);
		zachRole.setBounds(260, 162, 189, 14);
		gitHub.setBounds(10, 186, 46, 14);
		roketGamer.setBounds(10, 211, 117, 14);
		githubRole.setBounds(260, 187, 156, 14);
		roketgamerRole.setBounds(260, 211, 117, 14);

		licenseNotice.setHorizontalAlignment(SwingConstants.CENTER);

		getContentPane().add(gameTitle);
		getContentPane().add(licenseNotice);
		getContentPane().add(btnClose);	
		getContentPane().add(anthonyBauer);
		getContentPane().add(thomasGaubert);
		getContentPane().add(maxKirkby);
		getContentPane().add(jrVetus);
		getContentPane().add(mattWaller);
		getContentPane().add(zachMathewson);		
		getContentPane().add(anthonyRole);
		getContentPane().add(thomasRole);
		getContentPane().add(maxRole);
		getContentPane().add(jrRole);
		getContentPane().add(mattRole);		
		getContentPane().add(zachRole);
		getContentPane().add(gitHub);
		getContentPane().add(roketGamer);
		getContentPane().add(githubRole);
		getContentPane().add(roketgamerRole);

		btnClose.setFocusable(false);

		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nextWindow("MainMenuWindow");
			}
		});
	}
}