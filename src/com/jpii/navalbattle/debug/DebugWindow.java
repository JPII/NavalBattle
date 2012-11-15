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

package com.jpii.navalbattle.debug;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.gui.listeners.KeyboardListener;
import com.jpii.navalbattle.renderer.Helper;

@SuppressWarnings("serial")
public class DebugWindow extends JFrame {
	private JLabel lblNavalBattle;
	private JLabel lblDebugMode;
	private JTextPane debugPrinter;
	private JTextField commandField;
	private boolean paused = false;

	/**
	 * Constructor for DebugWindow.
	 */
	public DebugWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		
		this.setIconImage(Helper.GUI_WINDOW_ICON);
		
		this.setResizable(false);
		this.setTitle("NavalBattle");
		this.getContentPane().setLayout(null);

		lblNavalBattle = new JLabel("NavalBattle");
		lblNavalBattle.setBounds(10, 11, 120, 14);
		lblNavalBattle.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.getContentPane().add(lblNavalBattle);

		lblDebugMode = new JLabel("Debug Mode");
		lblDebugMode.setBounds(95, 13, 66, 14);
		this.getContentPane().add(lblDebugMode);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 439, 255);
		this.getContentPane().add(scrollPane);

		debugPrinter = new JTextPane();
		debugPrinter.setEditable(false);
		debugPrinter.setFont(new Font("Consolas",0,12));
		scrollPane.setViewportView(debugPrinter);

		commandField = new JTextField();
		commandField.setBounds(10, 301, 337, 23);
		this.getContentPane().add(commandField);
		commandField.setColumns(10);

		final JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!commandField.getText().isEmpty()) {
					submitCommand(commandField.getText());
					commandField.setText("");
				}
			}
		});
		btnSubmit.setBounds(357, 301, 89, 23);
		this.getContentPane().add(btnSubmit);

		printInfo("Debug mode enabled");
		this.setSize(465,365);
		this.setVisible(true);
		this.setLocation(0,0);
		
		commandField.grabFocus();

		this.setFocusable(true);
		this.addKeyListener(new KeyboardListener(this));
		commandField.addKeyListener(new KeyboardListener(this));

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				setVisible(false);
			}
		});
	}

	/**
	 * Prints a message without a tag. Next message does not go to a new line.
	 * @param message
	 */
	public void print(String message) {
		debugPrinter.setText(debugPrinter.getText() + message);
	}

	/**
	 * Prints a message without a tag. Next message goes to a new line.
	 * @param message
	 */
	public void println(String message) {
		debugPrinter.setText(debugPrinter.getText() + message + "\n");
	}

	/**
	 * Prints a message with an [INFO] tag.
	 * @param message
	 */
	public void printInfo(String message) {
		if(!paused)
			debugPrinter.setText(debugPrinter.getText() + "[INFO] " + message + "\n");
	}

	/**
	 * Prints a message with a [WARN] tag.
	 * @param message
	 */
	public void printWarning(String message) {
		if(!paused)
			debugPrinter.setText(debugPrinter.getText() + "[WARN] " + message + "\n");
	}

	/**
	 * Prints a message with an [ERROR] tag.
	 * @param message
	 */
	public void printError(String message) {
		if(!paused)
			debugPrinter.setText(debugPrinter.getText() + "[ERROR] " + message + "\n");
	}

	/**
	 * Prints a message with a [OTHER] tag.
	 * @param message
	 */
	public void printOther(String message) {
		if(!paused)
			debugPrinter.setText(debugPrinter.getText() + "[OTHER] " + message + "\n");
	}

	/**
	 * Clears the DebugWindow and prints message.
	 * @param message
	 */
	public void printNew(String message) {
		debugPrinter.setText(message + "\n");
	}

	/**
	 * Get current JFrame.
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return this;
	}

	/**
	 * Parses command.
	 * @param command
	 */
	public void submitCommand(String command) {
		NavalBattle.getCommandHandler().parseCommand(command);
	}

	/**
	 * Remotely grabs entered in <code>commandField</code> and parses.
	 */
	public void submitCommandRemote() {
		if(!commandField.getText().isEmpty()) {
			submitCommand(commandField.getText());
			commandField.setText("");
		}
	}

	/**
	 * Pause logging of tagged messages.
	 */
	public void pause() {
		this.paused  = true;
	}
	
	/**
	 * Resume logging of tagged messages.
	 */
	public void resume() {
		this.paused = false;
	}
	
	/**
	 * Grab focus of the <code>commandField</code>.
	 */
	public void focusOnField() {
		commandField.grabFocus();
	}
}