package com.jpii.navalbattle.gui.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseClick implements MouseListener {
	
	ArrayList<Object> windows;
	
	public MouseClick() {
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
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
