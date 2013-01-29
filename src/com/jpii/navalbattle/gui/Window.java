/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.gui;

import javax.swing.*;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.data.Constants;
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
	
	/**
	 * Default <code>Window</code> constructor.
	 */
	public Window() {
		setsize(492,340);
		setDefaults();
	}
	
	/**
	 * <code>Window</code> constructor which creates a <code>Window</code> with a specific size.
	 * @param x		width
	 * @param y		height
	 */
	public Window(int x, int y) {
		setsize(x,y);
	}
	
	/**
	 * <code>Window</code> constructor which creates a <code>Window</code> at a specific location and size.
	 * @param x			width
	 * @param y			height
	 * @param xloc		x-coordinate
	 * @param yloc		y-coordinate
	 */
	public Window(int x, int y,int xloc,int yloc) {
		setsize(x,y);
		setlocation(xloc,yloc);
	}
	
	/**
	 * Sets the size of the <code>Window</code>
	 * @param width		width
	 * @param height	height
	 */
	public void setsize(int width, int height){
		this.width = width;
		this.height = height;
		xloc = 1280/2-width/2;
		yloc = 800/2-height/2;
		setSize(width, height);
		setLocation(xloc,yloc);
	}
	
	/**
	 * Sets the location of the <code>Window</code>
	 * @param x		x
	 * @param y		y
	 */
	public void setlocation(int x, int y){
		xloc = x;
		yloc = y;
		setLocation(xloc,yloc);
	}
	
	/**
	 * Handle Strings appropriately.
	 * @return
	 */
	private String parseString(){
			return getClass().toString().substring((getClass().toString().lastIndexOf(".")+1));
	}
	
	/**
	 * Print to <code>DebugWindow</code>.
	 * @param msg
	 */
	private void printDebug(String msg){
		if(NavalBattle.getDebugWindow()!=null){
			NavalBattle.getDebugWindow().printInfo(msg);
		}
	}
	
	/**
	 * Set defaults for all <code>Windows</code>.
	 */
	protected void setDefaults(){
		this.setIconImage(Helper.GUI_WINDOW_ICON);
		setTitle("NavalBattle");
		setSize(width, height);
		setLocation(xloc,yloc);
		setResizable(false);
		setFocusable(true);
		addWindowListener(Constants.closer);
		addKeyListener(Constants.keys);
		addMouseListener(Constants.click);
		addMouseMotionListener(Constants.movement);
		addMouseWheelListener(Constants.wheel);
		addFocusListener(new Focus(this));
		setVisible(false);
	}
	
	/**
	 * Set window to visible and log the event.
	 */
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(isVisible()){
			printDebug("Showing " + parseString());
			setLocation(xloc,yloc);
		}
	}
	
	/**
	 * Show next <code>Window</code> based on string supplied.
	 * @param next		Name of <code>Window</code> to open. Do not include <code>.java</code>.
	 */
	public void nextWindow(String next){
		printDebug("Hiding "+parseString());
		NavalBattle.getWindowHandler().setNewWindow(next);
	}
	
	/**
	 * Dispose of <code>Window</code>.
	 */
	public void donewithMe(){
		super.dispose();
		printDebug("Disposing "+parseString());
	}
	
	/**
	 * Get method for <code>Window</code>.
	 * @return Window
	 */
	public JFrame getFrame() {
		return this;
	}
}
