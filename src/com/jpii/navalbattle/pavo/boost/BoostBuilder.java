/**
 * 
 */
package com.jpii.navalbattle.pavo.boost;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * @author maximusvladimir
 *
 */
public class BoostBuilder extends JFrame {
	Timer ticker;
	_Boost booster;
	public BoostBuilder() {
		setTitle("Pavo Boost Live Compiler");
		setSize(800,600);
		getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(0, 385, 784, 177);
		textPane.setFont(new Font("Consolas",0,12));
		textPane.setBackground(new Color(220,220,220));
		getContentPane().add(textPane);
		
		booster = new _Boost();
		ActionListener al = new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		};
		ticker = new Timer(150,al);
		ticker.start();
	}
	
	public void paint(Graphics g) {
		BufferedImage buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g2 = buffer.getGraphics();
		super.paint(g2);
		booster.render();
		g2.drawImage(booster.buffer,0,0,null);
		g2.dispose();
		g.drawImage(buffer,0,0,null);
	}
}
