package com.jpii.navalbattle.gui;

import javax.swing.*;
import java.awt.event.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;

public class LoginWindow {
	JButton loginButton;
	JFrame f;
	JLabel usernameLabel, passwordLabel;
	JTextField usernameField;
	JPasswordField passwordField;

	public LoginWindow() {
		
		NavalBattle.getDebugWindow().printInfo("LoginWindow opened");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}

		f = new JFrame();
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);
		usernameLabel = new JLabel();
		usernameLabel.setText("Username");
		usernameLabel.setBounds(311,11,100,20);
		usernameField = new JTextField(25);
		usernameField.setBounds(365,11,100,20);
		passwordLabel = new JLabel();
		passwordLabel.setText("Password");
		passwordLabel.setBounds(311,42,100,20);
		passwordField = new JPasswordField(25);
		passwordField.setBounds(365,42,100,20);
		loginButton=new JButton("Login");
		loginButton.setBounds(389,73,78,22);

		try {
		} catch (Exception e) { NavalBattle.getDebugWindow().printError("Exception while loading news feed!"); }
		f.getContentPane().add(usernameLabel);
		f.getContentPane().add(usernameField);
		f.getContentPane().add(passwordLabel);
		f.getContentPane().add(passwordField);
		f.getContentPane().add(loginButton);
		
		passwordField.addKeyListener(new KeyboardListener(this));
		usernameField.addKeyListener(new KeyboardListener(this));		
		
		JLabel lblVersion = new JLabel(Constants.NAVALBATTLE_VERSION_TITLE);
		lblVersion.setBounds(10, 81, 193, 14);
		f.getContentPane().add(lblVersion);

		JButton registerButton = new JButton("Register");
		registerButton.setBounds(301, 73, 78, 22);
		f.getContentPane().add(registerButton);
		
		JButton btnOffline = new JButton("Offline");
		btnOffline.setBounds(213, 73, 78, 22);
		f.getContentPane().add(btnOffline);

		f.setSize(491,143);
		f.setVisible(true);
		f.setLocation(500,300);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				login();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {			
				NavalBattle.getDebugWindow().printInfo("Opening register page...");
				// open register page
			}
		});
		
		f.setFocusable(true);
		f.addKeyListener(new KeyboardListener(this));

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				NavalBattle.close();
			}
		});
	}
	
	/**
	 *  Method for handling login.
	 */
	public void login() {
		if(Constants.FORCE_LOGIN) {
			NavalBattle.getDebugWindow().printWarning("User is forced authenticated! Game will be insecure.");
			NavalBattle.getDebugWindow().printInfo("Disposing LoginWindow");
			f.dispose();
			NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
			new MainMenuWindow();
		}
		// Authenticate Login
		else if (false) {
			NavalBattle.getDebugWindow().printWarning("User authenticated!");
			NavalBattle.getDebugWindow().printInfo("Disposing LoginWindow");
			f.dispose();
			NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
			new MainMenuWindow();
		} 
		else {
			NavalBattle.getDebugWindow().printWarning("Authentication failed!");	
			JOptionPane.showMessageDialog(f, "Unable to login. Check username and password.");
		}
	}
	
	/**
	 * Get method for LoginWindow
	 * 
	 * @return LoginWindow
	 */
	public JFrame getFrame() {
		return f;
	}
}