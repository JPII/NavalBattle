package com.jpii.navalbattle.gui.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseMove implements MouseMotionListener {
	
	ArrayList<Object> windows;
	
	public MouseMove() {
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
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	
}
