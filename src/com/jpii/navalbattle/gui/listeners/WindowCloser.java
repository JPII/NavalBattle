package com.jpii.navalbattle.gui.listeners;

import java.awt.event.*;

import com.jpii.navalbattle.NavalBattle;

public class WindowCloser extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent we) {
		close();
	}
	
	public static void close(){
		NavalBattle.getDebugWindow().printInfo("Someone is closing me!");
		System.exit(0);
	}
}
