package com.jpii.navalbattle.gui;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.util.URLUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateWindow extends BaseWindow {
	
	private static final long serialVersionUID = 1L;
	JLabel updateTitle;
	JTextPane updateText;
	
	public UpdateWindow() {
		getContentPane().setLayout(null);
		
		JLabel lblAnnouncement = new JLabel("Update:");
		lblAnnouncement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAnnouncement.setBounds(10, 11, 74, 30);
		getContentPane().add(lblAnnouncement);
		
		updateTitle = new JLabel("Navalbattle x.x.x");
		updateTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateTitle.setBounds(80, 19, 333, 14);
		getContentPane().add(updateTitle);
		
		updateText = new JTextPane();
		updateText.setText("There is an update to NavalBattle. It is highly recommended that you update to the latest version." +
				"\n\nNote that RoketGamer leaderboards are disabled until you update to the latest version.");
		updateText.setEditable(false);
		updateText.setBounds(10, 44, 466, 221);
		getContentPane().add(updateText);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextWindow("LoginWindow");
			}
		});
		btnClose.setBounds(382, 271, 94, 30);
		getContentPane().add(btnClose);
		
		JButton moreInfoButton = new JButton("More Info");
		moreInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				URLUtils.openURL(NavalBattle.getBroadcastService().getUpdateUrl());
			}
		});
		moreInfoButton.setBounds(10, 271, 94, 30);
		getContentPane().add(moreInfoButton);
	}
	
	public void setVisible(boolean status) {
		super.setVisible(status);
		
		if(status) {
			updateTitle.setText("NavalBattle " + NavalBattle.getBroadcastService().getVersionReadable());
			updateText.setText(NavalBattle.getBroadcastService().getUpdateText());
		}
	}
}
