package com.jpii.navalbattle.gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class LoggingInWindow extends JFrame {
	public LoggingInWindow() {
		setTitle("NavalBattle - Logging In");
		getContentPane().setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(0, 286, 475, 14);
		getContentPane().add(progressBar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoggingInWindow.class.getResource("/com/jpii/navalbattle/res/jpii_title.png")));
		label.setBounds(0, 0, 475, 300);
		getContentPane().add(label);
		
		this.setSize(491, 339);
		this.setVisible(true);
		
		new ImageChanger(label).start();
	}
	
	class ImageChanger extends Thread {
		
		private JLabel label;
		
	    public ImageChanger(JLabel label) {
	        super("ImageChanger");
	        this.label = label;
	    }
	    
	    public void run() {
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) { }
	        
	        label.setIcon(new ImageIcon(LoggingInWindow.class.getResource("/com/jpii/navalbattle/res/roketgamer_title.png")));
	        
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) { }
	        
	        label.setIcon(new ImageIcon(LoggingInWindow.class.getResource("/com/jpii/navalbattle/res/navalbattle_title.png")));
	    }
	}
}
