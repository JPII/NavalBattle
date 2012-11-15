package com.jpii.navalbattle.gui;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

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
	protected int width;
	protected int height;
	
	public Window() {
		width = 492;
		height = 340;
		setDefaults();
	}
	public Window(int x, int y) {
		width = x;
		height = y;
	}
	
	protected void setDefaults(){
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
			NavalBattle.getDebugWindow().printError("NimbusLookAndFeel was unable to be loaded, unsuported");	
		} catch (ClassNotFoundException e) {
			NavalBattle.getDebugWindow().printError("NimbusLookAndFeel was unable to be loaded, class not found");	
		} catch (InstantiationException e) {
			NavalBattle.getDebugWindow().printError("NimbusLookAndFeel was unable to be loaded, instantiation");	
		} catch (IllegalAccessException e) {
			NavalBattle.getDebugWindow().printError("NimbusLookAndFeel was unable to be loaded, illegalaccess");	
		}
		
		this.setIconImage(Helper.GUI_WINDOW_ICON);
		setTitle("NavalBattle");
		setSize(width, height);
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
