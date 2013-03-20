package com.jpii.navalbattle.gui;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.util.URLUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BroadcastWindow extends Window {
	public BroadcastWindow() {
		getContentPane().setLayout(null);
		
		JLabel lblAnnouncement = new JLabel("Announcement:");
		lblAnnouncement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAnnouncement.setBounds(10, 11, 128, 30);
		getContentPane().add(lblAnnouncement);
		
		JLabel announcementTitle = new JLabel(NavalBattle.getBroadcastService().getAnnouncementTitle());
		announcementTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		announcementTitle.setBounds(143, 19, 333, 14);
		getContentPane().add(announcementTitle);
		
		JTextPane announcementText = new JTextPane();
		announcementText.setText(NavalBattle.getBroadcastService().getAnnouncementText());
		announcementText.setEditable(false);
		announcementText.setBounds(10, 44, 466, 221);
		getContentPane().add(announcementText);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextWindow("MainMenuWindow");
			}
		});
		btnClose.setBounds(387, 278, 89, 23);
		getContentPane().add(btnClose);
		
		JButton moreInfoButton = new JButton("More Info");
		moreInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				URLUtils.openURL(NavalBattle.getBroadcastService().getAnnouncementUrl());
			}
		});
		moreInfoButton.setBounds(10, 278, 89, 23);
		getContentPane().add(moreInfoButton);
	}
}
