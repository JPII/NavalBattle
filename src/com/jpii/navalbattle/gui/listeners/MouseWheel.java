package com.jpii.navalbattle.gui.listeners;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class MouseWheel implements MouseWheelListener {
	
	ArrayList<Object> windows;
	
	public MouseWheel() {
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
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
	}	
}
