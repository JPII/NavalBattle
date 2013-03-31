package com.jpii.navalbattle.gui;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.util.URLUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BroadcastWindow extends BaseWindow {
	private static final long serialVersionUID = 1L;
	JLabel announcementTitle;
	JTextPane announcementText;
	
	public BroadcastWindow() {
		getContentPane().setLayout(null);
		
		JLabel lblAnnouncement = new JLabel("Announcement:");
		lblAnnouncement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAnnouncement.setBounds(10, 11, 128, 30);
		getContentPane().add(lblAnnouncement);
		
		announcementTitle = new JLabel("Announcement title");
		announcementTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		announcementTitle.setBounds(143, 19, 333, 14);
		getContentPane().add(announcementTitle);
		
		announcementText = new JTextPane();
		announcementText.setText("Announcement text");
		announcementText.setEditable(false);
		announcementText.setBounds(10, 44, 466, 221);
		getContentPane().add(announcementText);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextWindow("LoginWindow");
			}
		});
		btnClose.setBounds(382, 270, 94, 30);
		getContentPane().add(btnClose);
		
		JButton moreInfoButton = new JButton("More Info");
		moreInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				URLUtils.openURL(NavalBattle.getBroadcastService().getAnnouncementUrl());
			}
		});
		moreInfoButton.setBounds(10, 270, 94, 30);
		getContentPane().add(moreInfoButton);
	}
	
	public void setVisible(boolean status) {
		super.setVisible(status);
		
		if(status) {
			announcementTitle.setText(NavalBattle.getBroadcastService().getAnnouncementTitle());
			announcementText.setText(NavalBattle.getBroadcastService().getAnnouncementText());
		}
	}
}
