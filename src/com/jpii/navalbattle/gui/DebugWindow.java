package com.jpii.navalbattle.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.jpii.navalbattle.data.Constants;

public class DebugWindow {
	JFrame f;
	private JLabel lblNavalBattle;
	private JLabel lblDebugMode;
	private JTextPane debugPrinter;

	public DebugWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		f = new JFrame();
		f.setResizable(false);
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);

		lblNavalBattle = new JLabel("NavalBattle");
		lblNavalBattle.setBounds(10, 11, 86, 14);
		lblNavalBattle.setFont(new Font("Tahoma", Font.BOLD, 14));
		f.getContentPane().add(lblNavalBattle);

		lblDebugMode = new JLabel("Debug Mode");
		lblDebugMode.setBounds(95, 13, 66, 14);
		f.getContentPane().add(lblDebugMode);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 439, 255);
		f.getContentPane().add(scrollPane);

		debugPrinter = new JTextPane();
		debugPrinter.setEditable(false);
		scrollPane.setViewportView(debugPrinter);

		if(Constants.DEBUG_MODE) {
			f.setSize(475,340);
			f.setVisible(true);
			f.setLocation(0,0);
		} else {
			f.setSize(475,340);
			f.setVisible(false);
			f.setLocation(0,0);
		}

		f.setFocusable(true);
		f.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent k) {	
				if(k.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) { 
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void print(String message) {
		debugPrinter.setText(debugPrinter.getText() + message);
	}

	public void println(String message) {
		debugPrinter.setText(debugPrinter.getText() + message + "\n");
	}

	public void printInfo(String message) {
		debugPrinter.setText(debugPrinter.getText() + "[INFO] " + message + "\n");
	}

	public void printWarning(String message) {
		debugPrinter.setText(debugPrinter.getText() + "[WARN] " + message + "\n");
	}

	public void printError(String message) {
		debugPrinter.setText(debugPrinter.getText() + "[ERROR] " + message + "\n");
	}

	public void printOther(String message) {
		debugPrinter.setText(debugPrinter.getText() + "[OTHER] " + message + "\n");
	}

	public JFrame getFrame() {
		return f;
	}
}
