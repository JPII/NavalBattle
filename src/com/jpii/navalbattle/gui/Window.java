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
	
	public Window() {
		width = 492;
		height = 340;
		xloc = 1280/2-width/2;
		yloc = 800/2-height/2;
		setDefaults();
	}
	
	public Window(int x, int y) {
		width = x;
		height = y;
		xloc = 1280/2-width/2;
		yloc = 800/2-height/2;
	}
	
	public Window(int x, int y,int xloc,int yloc) {
		width = x;
		height = y;
		this.xloc = xloc;
		this.yloc = yloc;
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
		addWindowListener(Constants.closer);
		addKeyListener(Constants.keys);
		addMouseListener(Constants.click);
		addMouseMotionListener(Constants.movement);
		addMouseWheelListener(Constants.wheel);
		addFocusListener(new Focus(this));
		setVisible(false);
	}
	
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(isVisible()){
			printDebug("Showing "+parseString());
			setLocation(xloc,yloc);
		}
	}
	
	public void nextWindow(String next){
		printDebug("Hiding "+parseString());
		NavalBattle.getWindowHandler().setNewWindow(next);
	}
	
	public void donewithMe(){
		super.dispose();
		printDebug("Disposing "+parseString());
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
