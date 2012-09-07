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
	private JLabel gameTitle;

	public HelpWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e){}

		f = new JFrame();
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);

		gameTitle = new JLabel("NavalBattle");
		gameTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		gameTitle.setBounds(10, 11, 86, 14);
		f.getContentPane().add(gameTitle);
		
		JLabel lblToBeImplemented = new JLabel("To be implemented...");
		lblToBeImplemented.setBounds(10, 36, 102, 14);
		f.getContentPane().add(lblToBeImplemented);
		
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
		btnClose.setBounds(7, 228, 59, 23);
		f.getContentPane().add(btnClose);
		
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});

		f.setSize(475,300);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocation(1280/2-f.getWidth()/2,800/2-f.getHeight()/2);
	}

	public JFrame getFrame() {
		return f;
	}
}