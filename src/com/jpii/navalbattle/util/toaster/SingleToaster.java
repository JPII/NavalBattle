/*
 * Copyright (C) 2012 Daniele Piras
 *
 * Modified under license by JPII and contributors.
 */

package com.jpii.navalbattle.util.toaster;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class SingleToaster extends javax.swing.JWindow {
	private static final long serialVersionUID = 1L;

	// Label to store Icon
	public JLabel iconLabel = new JLabel();

	// Text area for the message
	public JTextArea message = new JTextArea();
	
	public ToasterTest t;

	/***
	 * Simple constructor that initialized components...
	 */
	public SingleToaster(ToasterTest t) {
		this.t = t;
		initComponents(t);
	}

	/***
	 * Function to initialized components
	 */
	public void initComponents(ToasterTest t) {

		setSize(t.getToasterWidth(), t.getToasterHeight());
		message.setFont(t.getToasterMessageFont());
		JPanel externalPanel = new JPanel(new BorderLayout(1, 1));
		externalPanel.setBackground(t.getBorderColor());
		
		final ToasterTest finaly = t;
		
		@SuppressWarnings("serial")
		JPanel innerPanel = new JPanel(new BorderLayout(t.getMargin(),
				t.getMargin())) {
			
			
			
			@Override
			public void paint(Graphics g) {
				if (finaly.getBackgroundImage() != null) {
					g.drawImage(finaly.getBackgroundImage(), 0, 0, null);
				}
				super.paint(g);
			}
		};
		if (t.getBackgroundImage() != null) {
			innerPanel.setOpaque(false);
			message.setOpaque(false);
			iconLabel.setOpaque(false);
		}
		innerPanel.setBackground(t.getToasterColor());
		message.setBackground(t.getToasterColor());
		message.setMargin(new Insets(2, 2, 2, 2));
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		EtchedBorder etchedBorder = (EtchedBorder) BorderFactory
				.createEtchedBorder();
		externalPanel.setBorder(etchedBorder);
		externalPanel.add(innerPanel);
		message.setForeground(t.getMessageColor());
		innerPanel.add(iconLabel, BorderLayout.WEST);
		innerPanel.add(message, BorderLayout.CENTER);
		getContentPane().add(externalPanel);
	}

	/***
	 * Start toaster animation...
	 */
	public void animate() {
		(new Animation(this)).start();
	}

}