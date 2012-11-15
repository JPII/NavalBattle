package com.jpii.navalbattle.gui;

import java.awt.event.*;
import javax.swing.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.gui.listeners.KeyboardListener;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	/**
	 * Constructor for Window. Superclass for all GUI windows that
	 * handles size, icon, etc. To redefine elements, use custom constructor.
	 * 
	 * Will log opening automatically, but closing (disposing) should be
	 * handled within each subclass.
	 */
	public Window() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		this.setIconImage(Helper.GUI_WINDOW_ICON);
		
		setTitle("NavalBattle");
		setSize(491, 339);
		setLocation(1280/2-getWidth()/2,800/2-getHeight()/2);
		
		setResizable(false);
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				NavalBattle.close();
			}
		});
		
		setVisible(true);
	}
	
	/**
	 * Get method for Window
	 * 
	 * @return Window
	 */
	public JFrame getFrame() {
		return this;
	}
}
