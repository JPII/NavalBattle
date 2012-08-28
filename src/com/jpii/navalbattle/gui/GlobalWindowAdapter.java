package com.jpii.navalbattle.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jpii.navalbattle.NavalBattle;

public class GlobalWindowAdapter extends WindowAdapter implements Runnable{

	public void windowClosing(WindowEvent we) {
		run();
	}
	
	@Override
	public void run() {
		NavalBattle.close();
	}

}
