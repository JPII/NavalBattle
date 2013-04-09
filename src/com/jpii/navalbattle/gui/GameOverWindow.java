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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOverWindow extends BaseWindow {
	private static final long serialVersionUID = 1L;

	/**
	 * <code>GameOverWindow</code> constructor.
	 */
	public GameOverWindow() {
		super(500,250);
		getContentPane().setLayout(null);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGameOver.setBounds(169, 11, 121, 38);
		getContentPane().add(lblGameOver);
		
		JLabel scoreValue = new JLabel("200,000");
		scoreValue.setBounds(244, 60, 46, 14);
		getContentPane().add(scoreValue);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScore.setBounds(169, 60, 46, 14);
		getContentPane().add(lblScore);
		
		JLabel lblRounds = new JLabel("Rounds");
		lblRounds.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRounds.setBounds(169, 85, 46, 14);
		getContentPane().add(lblRounds);
		
		JLabel label = new JLabel("4");
		label.setBounds(244, 85, 46, 14);
		getContentPane().add(label);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(169, 133, 105, 28);
		getContentPane().add(btnMainMenu);
		
		JButton btnRoketGamer = new JButton("RoketGamer");
		btnRoketGamer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRoketGamer.setBounds(169, 172, 105, 28);
		getContentPane().add(btnRoketGamer);
		
		JLabel lblScoreSubmitted = new JLabel("Score Submitted");
		lblScoreSubmitted.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblScoreSubmitted.setBounds(121, 108, 94, 14);
		getContentPane().add(lblScoreSubmitted);
		
		JLabel lblYes = new JLabel("Yes");
		lblYes.setBounds(244, 108, 46, 14);
		getContentPane().add(lblYes);
	}
}