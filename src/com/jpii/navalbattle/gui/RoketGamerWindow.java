package com.jpii.navalbattle.gui;

import javax.swing.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class RoketGamerWindow {
	JFrame f;
	private JTable overallLeaderboard;

	public RoketGamerWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}

		f = new JFrame();
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(RoketGamerWindow.class.getResource("/com/jpii/roketgamer/res/logo_300px.png")));
		f.setTitle("NavalBattle - RoketGamer");
		f.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 401, 372);
		f.getContentPane().add(tabbedPane);

		JPanel profileTab = new JPanel();
		tabbedPane.addTab(NavalBattle.getRoketGamer().getPlayer().getName(), null, profileTab, null);
		profileTab.setLayout(null);

		JLabel lblWelcomeUsername = new JLabel("Welcome, " + NavalBattle.getRoketGamer().getPlayer().getName());
		lblWelcomeUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcomeUsername.setBounds(10, 11, 376, 25);
		profileTab.add(lblWelcomeUsername);

		JButton btnOnlineProfile = new JButton("Online Profile");
		btnOnlineProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening profile page...");

				String url = Constants.SERVER_LOCATION + "/profile.php";
				String os = System.getProperty("os.name").toLowerCase();
				Runtime rt = Runtime.getRuntime();

				try {

					if (os.indexOf("win") >= 0) {

						// this doesn't support showing urls in the form of
						// "page.html#nameLink"
						rt.exec("rundll32 url.dll,FileProtocolHandler " + url);

					} else if (os.indexOf("mac") >= 0) {

						rt.exec("open " + url);

					} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {

						// Do a best guess on unix until we get a platform
						// independent way
						// Build a list of browsers to try, in this order.
						String[] browsers = { "epiphany", "firefox", "mozilla",
								"konqueror", "netscape", "opera", "links",
								"lynx" };

						// Build a command string which looks like
						// "browser1 "url" || browser2 "url" ||..."
						StringBuffer cmd = new StringBuffer();
						for (int i = 0; i < browsers.length; i++)
							cmd.append((i == 0 ? "" : " || ") + browsers[i]
									+ " \"" + url + "\" ");

						rt.exec(new String[] { "sh", "-c", cmd.toString() });

					} else {
						return;
					}
				} catch (Exception ex) {
					return;
				}
			}
		});
		btnOnlineProfile.setBounds(10, 310, 103, 23);
		profileTab.add(btnOnlineProfile);
		
		JLabel lblFrontPageIs = new JLabel("Front Page is coming soon and is currently under development.");
		lblFrontPageIs.setBounds(10, 47, 376, 14);
		profileTab.add(lblFrontPageIs);

		JPanel leaderboardTab = new JPanel();
		tabbedPane.addTab("Leaderboard", null, leaderboardTab, null);
		leaderboardTab.setLayout(null);

		overallLeaderboard = new JTable();
		overallLeaderboard.setBounds(0, 254, 396, -251);
		leaderboardTab.add(overallLeaderboard);
		
		JTextPane textPane = new JTextPane();
		try {
			textPane.setPage(Constants.SERVER_LOCATION + "/api/1.0/temp/leaderboard.php");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		textPane.setBounds(0, 0, 396, 347);
		leaderboardTab.add(textPane);

		JPanel achievementTab = new JPanel();
		tabbedPane.addTab("Achievements", null, achievementTab, null);
		achievementTab.setLayout(null);
		
		JTextPane textPane_1 = new JTextPane();
		try {
			textPane_1.setPage(Constants.SERVER_LOCATION + "/api/1.0/temp/achievement.php");
		} catch (IOException e1) {}
		textPane_1.setBounds(0, 0, 396, 344);
		achievementTab.add(textPane_1);

		JPanel friendsTab = new JPanel();
		tabbedPane.addTab("Friends", null, friendsTab, null);
		friendsTab.setLayout(null);
		
		JLabel lblComingSoon_1 = new JLabel("Coming Soon");
		lblComingSoon_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblComingSoon_1.setBounds(10, 11, 79, 14);
		friendsTab.add(lblComingSoon_1);
		
		JLabel lblChallengesAreComing_1 = new JLabel("Friends are coming soon and are currently under development.");
		lblChallengesAreComing_1.setBounds(10, 35, 376, 14);
		friendsTab.add(lblChallengesAreComing_1);

		JPanel roketgamerTab = new JPanel();
		tabbedPane.addTab("RoketGamer", null, roketgamerTab, null);
		roketgamerTab.setLayout(null);

		JLabel lblRoketgamer = new JLabel("RoketGamer");
		lblRoketgamer.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRoketgamer.setBounds(10, 11, 126, 25);
		roketgamerTab.add(lblRoketgamer);

		JLabel lblVersion = new JLabel("Version " + NavalBattle.getRoketGamer().getVersion());
		lblVersion.setBounds(10, 47, 99, 14);
		roketgamerTab.add(lblVersion);

		JLabel lblCopyrightTexasgamer = new JLabel(
				"Copyright 2012 TexasGamer <www.texasgamer.co.cc>");
		lblCopyrightTexasgamer.setBounds(10, 80, 270, 14);
		roketgamerTab.add(lblCopyrightTexasgamer);

		JLabel lblRoketgamerIsAvailable = new JLabel(
				"Available under is open source under the GNU General Public License v3. ");
		lblRoketgamerIsAvailable.setBounds(10, 105, 376, 14);
		roketgamerTab.add(lblRoketgamerIsAvailable);

		JButton btnRoketgamer = new JButton("RoketGamer");
		btnRoketgamer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Opening RoketGamer page...");

				String url = Constants.SERVER_LOCATION;
				String os = System.getProperty("os.name").toLowerCase();
				Runtime rt = Runtime.getRuntime();

				try {

					if (os.indexOf("win") >= 0) {

						// this doesn't support showing urls in the form of
						// "page.html#nameLink"
						rt.exec("rundll32 url.dll,FileProtocolHandler " + url);

					} else if (os.indexOf("mac") >= 0) {

						rt.exec("open " + url);

					} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {

						// Do a best guess on unix until we get a platform
						// independent way
						// Build a list of browsers to try, in this order.
						String[] browsers = { "epiphany", "firefox", "mozilla",
								"konqueror", "netscape", "opera", "links",
								"lynx" };

						// Build a command string which looks like
						// "browser1 "url" || browser2 "url" ||..."
						StringBuffer cmd = new StringBuffer();
						for (int i = 0; i < browsers.length; i++)
							cmd.append((i == 0 ? "" : " || ") + browsers[i]
									+ " \"" + url + "\" ");

						rt.exec(new String[] { "sh", "-c", cmd.toString() });

					} else {
						return;
					}
				} catch (Exception ex) {
					return;
				}
			}
		});
		btnRoketgamer.setBounds(287, 310, 99, 23);
		roketgamerTab.add(btnRoketgamer);

		JLabel lblPoweredBy = new JLabel("Powered by:");
		lblPoweredBy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPoweredBy.setBounds(10, 130, 99, 14);
		roketgamerTab.add(lblPoweredBy);

		JLabel lblSql = new JLabel("SQL");
		lblSql.setBounds(10, 145, 46, 14);
		roketgamerTab.add(lblSql);

		JLabel lblPhp = new JLabel("PHP");
		lblPhp.setBounds(10, 159, 46, 14);
		roketgamerTab.add(lblPhp);

		JLabel lblBootstrapByTwitter = new JLabel("Bootstrap, by Twitter");
		lblBootstrapByTwitter.setBounds(10, 170, 126, 14);
		roketgamerTab.add(lblBootstrapByTwitter);

		JLabel lblCloudflare = new JLabel("CloudFlare");
		lblCloudflare.setBounds(10, 183, 54, 14);
		roketgamerTab.add(lblCloudflare);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RoketGamerWindow.class.getResource("/com/jpii/roketgamer/res/logo_100px.png")));
		lblNewLabel.setBounds(10, 233, 100, 100);
		roketgamerTab.add(lblNewLabel);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				NavalBattle.getDebugWindow().printInfo("Disposing RoketGamerWindow");
				f.dispose();
				NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
				new MainMenuWindow();
			}
		});

		f.setSize(417, 410);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocation(1280/2-f.getWidth()/2,800/2-f.getHeight()/2);
	}

	public JFrame getFrame() {
		return f;
	}
}