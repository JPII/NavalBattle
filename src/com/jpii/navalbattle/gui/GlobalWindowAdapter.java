package com.jpii.navalbattle.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import com.jpii.navalbattle.NavalBattle;

public class GlobalWindowAdapter extends WindowAdapter {

	public void windowClosing(WindowEvent we) {
		try {
			SwingUtilities.invokeAndWait(new Runnable(){ public void run() {
				NavalBattle.close();
			}}); 
		}
		catch (Exception e) { }
	}
}
