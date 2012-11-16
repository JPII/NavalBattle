package com.jpii.navalbattle.gui;

import javax.swing.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.gui.listeners.*;

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
	protected int xloc;
	protected int yloc;
	
	public Window() {
		width = 492;
		height = 340;
		xloc = 1280/2-width/2;
		yloc = 800/2-height/2;
		printDebug("Showing "+parseString());
		setDefaults();
	}
	
	public Window(int x, int y) {
		width = x;
		height = y;
		xloc = 1280/2-width/2;
		yloc = 800/2-height/2;
		printDebug("Showing "+parseString());
	}
	
	public Window(int x, int y,int xloc,int yloc) {
		width = x;
		height = y;
		this.xloc = xloc;
		this.yloc = yloc;
		printDebug("Showing "+parseString());
	}
	
	private String parseString(){
			return getClass().toString().substring((getClass().toString().lastIndexOf(".")+1));
	}
	
	private void printDebug(String msg){
		if(NavalBattle.getDebugWindow()!=null){
			NavalBattle.getDebugWindow().printInfo(msg);
		}
	}
	
	protected void setDefaults(){
		
		this.setIconImage(Helper.GUI_WINDOW_ICON);
		setTitle("NavalBattle");
		setSize(width, height);
		setLocation(xloc,yloc);
		setResizable(false);
		setFocusable(true);
		addKeyListener(new KeyboardListener(this));
		addWindowListener(new WindowCloser());
		setVisible(false);
	}
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	public void nextWindow(String next){
		printDebug("Hiding "+parseString());
		NavalBattle.getWindowHandler().setNewWindow(next);
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
