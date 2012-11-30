package com.jpii.navalbattle.gui.listeners;

import java.awt.event.*;
import java.util.ArrayList;

import com.jpii.navalbattle.NavalBattle;

public class WindowCloser extends WindowAdapter {
	
	ArrayList<Object> windows;
	
	public WindowCloser() {
		super();
		windows = new ArrayList<Object>();
	}
	
	public void add(Object classname){
		windows.add(classname);
	}
	
	public void remove(Object classname){
		windows.remove(classname);
	}
	
	@Override
	public void windowClosing(WindowEvent we) {
		close();
	}
	
	public static void close(){
		NavalBattle.getDebugWindow().printInfo("Someone is closing me!");
		System.exit(0);
	}
}
