package com.jpii.navalbattle.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;

@SuppressWarnings("serial")
public class LoggingInWindow extends Window {
	
	private ImageChanger imageChanger;
	
	public LoggingInWindow() {
		
		NavalBattle.getDebugWindow().printInfo("LoggingInWindow opened");

		getContentPane().setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setBounds(0, 286, 475, 14);
		getContentPane().add(progressBar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoggingInWindow.class.getResource("/com/jpii/navalbattle/res/jpii_title.png")));
		label.setBounds(0, 0, 475, 300);
		getContentPane().add(label);
		
		addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {
				NavalBattle.getDebugWindow().printInfo("Skipping splash screens");
				NavalBattle.getDebugWindow().printInfo("Disposing LoggingInWindow");
				dispose();
				NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
				new MainMenuWindow();
				
				imageChanger.stop();
			}
		});
		
		imageChanger = new ImageChanger(label);
		imageChanger.start();
	}
	
	class ImageChanger extends Thread {
		
		private JLabel label;
		
	    public ImageChanger(JLabel label) {
	        super("ImageChanger");
	        this.label = label;
	    }
	    
	    public void run() {
	        try {
				Thread.sleep(Constants.SPLASH_DURATION);
			} catch (InterruptedException e) { }
	        
	        label.setIcon(new ImageIcon(LoggingInWindow.class.getResource("/com/jpii/navalbattle/res/roketgamer_title.png")));
	        
	        try {
				Thread.sleep(Constants.SPLASH_DURATION);
			} catch (InterruptedException e) { }
	        
	        label.setIcon(new ImageIcon(LoggingInWindow.class.getResource("/com/jpii/navalbattle/res/navalbattle_title.png")));
	        
	        try {
				Thread.sleep(Constants.SPLASH_DURATION);
			} catch (InterruptedException e) { }
	        
	        NavalBattle.getDebugWindow().printInfo("Disposing LoggingInWindow");
			dispose();
			NavalBattle.getDebugWindow().printInfo("Opening MainMenuWindow");
			new MainMenuWindow();
	    }
	}
}
