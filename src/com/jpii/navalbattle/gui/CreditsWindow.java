package com.jpii.navalbattle.gui;

import javax.swing.*;

import com.jpii.navalbattle.NavalBattle;

import java.awt.*;
import java.awt.event.*;

public class CreditsWindow {
	JFrame f;
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

		f = new JFrame();
		f.setTitle("NavalBattle");
		f.getContentPane().setLayout(null);

		gameTitle = new JLabel("NavalBattle");
		gameTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		gameTitle.setBounds(10, 11, 86, 14);
		f.getContentPane().add(gameTitle);

		licenseNotice = new JLabel("NavalBattle is open source under the GNU General Public License v3.");
		licenseNotice.setHorizontalAlignment(SwingConstants.CENTER);
		licenseNotice.setBounds(121, 232, 328, 14);
		f.getContentPane().add(licenseNotice);

		btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Disposing CreditsWindow");
				f.dispose();
				NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
				new MainMenuWindow();
			}
		});
		btnClose.setBounds(10, 228, 89, 23);
		f.getContentPane().add(btnClose);
		
		JLabel anthonyBauer = new JLabel("Anthony \"abauer\" Bauer");
		anthonyBauer.setBounds(10, 36, 117, 14);
		f.getContentPane().add(anthonyBauer);
		
		JLabel thomasGaubert = new JLabel("Thomas \"TexasGamer\" Gaubert");
		thomasGaubert.setBounds(10, 61, 150, 14);
		f.getContentPane().add(thomasGaubert);
		
		JLabel maxKirkby = new JLabel("Max \"maximusvladimir\" Kirkby");
		maxKirkby.setBounds(10, 86, 140, 14);
		f.getContentPane().add(maxKirkby);
		
		JLabel jrVetus = new JLabel("JR \"DarkWarHero\" Vetus");
		jrVetus.setBounds(10, 111, 127, 14);
		f.getContentPane().add(jrVetus);
		
		mattWaller = new JLabel("Matt \"Matthis5point0\" Waller");
		mattWaller.setBounds(10, 136, 140, 14);
		f.getContentPane().add(mattWaller);
		
		zachMathewson = new JLabel("Zach \"smeagle42\" Mathewson");
		zachMathewson.setBounds(10, 161, 150, 14);
		f.getContentPane().add(zachMathewson);
		
		anthonyRole = new JLabel("Game design lead");
		anthonyRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		anthonyRole.setBounds(190, 36, 117, 14);
		f.getContentPane().add(anthonyRole);
		
		thomasRole = new JLabel("SCM manager, RoketGamer lead");
		thomasRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		thomasRole.setBounds(190, 61, 189, 14);
		f.getContentPane().add(thomasRole);
		
		JLabel maxRole = new JLabel("TBD");
		maxRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		maxRole.setBounds(190, 86, 46, 14);
		f.getContentPane().add(maxRole);
		
		JLabel jrRole = new JLabel("TBD");
		jrRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		jrRole.setBounds(190, 111, 46, 14);
		f.getContentPane().add(jrRole);
		
		JLabel mattRole = new JLabel("TBD");
		mattRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		mattRole.setBounds(190, 136, 46, 14);
		f.getContentPane().add(mattRole);
		
		JLabel zachRole = new JLabel("TBD");
		zachRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		zachRole.setBounds(190, 161, 46, 14);
		f.getContentPane().add(zachRole);
		
		JLabel gitHub = new JLabel("GitHub");
		gitHub.setBounds(10, 181, 46, 14);
		f.getContentPane().add(gitHub);
		
		JLabel roketGamer = new JLabel("RoketGamer");
		roketGamer.setBounds(10, 203, 59, 14);
		f.getContentPane().add(roketGamer);
		
		JLabel githubRole = new JLabel("Source code hosting, SCM");
		githubRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		githubRole.setBounds(190, 181, 156, 14);
		f.getContentPane().add(githubRole);
		
		JLabel roketgamerRole = new JLabel("Online social gaming");
		roketgamerRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		roketgamerRole.setBounds(190, 203, 117, 14);
		f.getContentPane().add(roketgamerRole);
		
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				NavalBattle.close();
			}
		});

		f.setSize(475,300);
		f.setVisible(true);
		f.setLocation(500,300);
	}

	public JFrame getFrame() {
		return f;
	}
}