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
import java.awt.event.*;
import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;
import com.jpii.roketgamer.Player;
import com.jpii.roketgamer.auth.APIKey;
import com.jpii.roketgamer.auth.AuthStatus;
import com.jpii.roketgamer.auth.Password;

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
		usernameLabel.setBounds(311,11,58,20);
		usernameField = new JTextField(25);
		usernameField.setBounds(365,11,100,20);
		passwordLabel = new JLabel();
		passwordLabel.setToolTipText("Use RoketGamer application password");
		passwordLabel.setText("Password");
		passwordLabel.setBounds(311,42,58,20);
		passwordField = new JPasswordField(25);
		passwordField.setToolTipText("Use RoketGamer application password");
		passwordField.setBounds(365,42,100,20);
		loginButton = new JButton("Login");
		loginButton.setBounds(387,86,78,22);

		f.getContentPane().add(usernameLabel);
		f.getContentPane().add(usernameField);
		f.getContentPane().add(passwordLabel);
		f.getContentPane().add(passwordField);
		f.getContentPane().add(loginButton);
		
		passwordField.addKeyListener(new KeyboardListener(this));
		usernameField.addKeyListener(new KeyboardListener(this));		
		
		JLabel lblVersion = new JLabel(Constants.NAVALBATTLE_VERSION_TITLE);
		lblVersion.setBounds(10, 90, 193, 14);
		f.getContentPane().add(lblVersion);

		JButton registerButton = new JButton("Register");
		registerButton.setBounds(301, 86, 78, 22);
		f.getContentPane().add(registerButton);
		registerButton.addKeyListener(new KeyboardListener(this));
		
		JButton offlineButton = new JButton("Offline");
		offlineButton.setBounds(213, 86, 78, 22);
		f.getContentPane().add(offlineButton);

		f.setSize(491,143);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocation(1280/2-f.getWidth()/2,800/2-f.getHeight()/2);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				login();
			}
		});
		
		offlineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				NavalBattle.getDebugWindow().printInfo("Opening in offline mode");
				NavalBattle.getDebugWindow().printWarning("RoketGamer disabled");
				NavalBattle.getGameState().setOffline(true);
				
				NavalBattle.getDebugWindow().printInfo("Disposing LoginWindow");
				f.dispose();
				NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
				new MainMenuWindow();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {			
				NavalBattle.getDebugWindow().printInfo("Opening register page");
				
				String url = Constants.SERVER_LOCATION + "/register.php?game=1&name=NavalBattle";
				String os = System.getProperty("os.name").toLowerCase();
				Runtime rt = Runtime.getRuntime();

				try {
					if (os.indexOf("win") >= 0) {
						rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
					} else if (os.indexOf("mac") >= 0) {
						rt.exec("open " + url);

					} else if (os.indexOf("nix") >=0 || os.indexOf( "nux") >=0) {
						String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
								"netscape","opera","links","lynx", "chrome"};

						StringBuffer cmd = new StringBuffer();
						for (int i = 0; i < browsers.length; i++) {
							cmd.append((i==0  ? "" : " || " ) + browsers[i] + " \"" + url + "\" ");
						}

						rt.exec(new String[] { "sh", "-c", cmd.toString() });
					} else {
						return;
					}
					
				} catch (Exception e) {
					NavalBattle.getDebugWindow().printError(e.getMessage());
					return;
				}
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
		@SuppressWarnings("deprecation")
		AuthStatus status = NavalBattle.getRoketGamer().init(new APIKey(Constants.API_KEY), 
				new Player(usernameField.getText(), 
				new Password(passwordField.getText())));
		
		if (status == AuthStatus.GOOD) {
			NavalBattle.getDebugWindow().printInfo("User authenticated");
			NavalBattle.getDebugWindow().printInfo("Logged in as: " + NavalBattle.getRoketGamer().getPlayer().getName());
			NavalBattle.getDebugWindow().printInfo("Disposing LoginWindow");
			f.dispose();
			NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
			new MainMenuWindow();
		} else {
			if(status == AuthStatus.BAD) {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus.BAD");	
				JOptionPane.showMessageDialog(f, "Incorrect username or password. \nUse your application password to login.");
			} else if (status == AuthStatus.OFFLINE) {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus.OFFLINE");	
				JOptionPane.showMessageDialog(f, "Unable to login. RoketGamer is offline.");
			} else if (status == AuthStatus.INVALID_API_KEY) {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus.INVALID_API_KEY");	
				JOptionPane.showMessageDialog(f, "Unable to login. API key is invalid.");
			} else if (status == AuthStatus.UNKNOWN) {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus.UNKNOWN");	
				JOptionPane.showMessageDialog(f, "Unable to login. Retry later or check the RoketGamer website.");
			} else {
				NavalBattle.getDebugWindow().printWarning("Authentication failed: AuthStatus is not recognized.");	
				JOptionPane.showMessageDialog(f, "Unable to login. An unknown error occured.");
			}
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