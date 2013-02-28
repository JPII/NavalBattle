package com.jpii.navalbattle.game.gui;

import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PFrame;

public class EntityBox extends PFrame{
	
	int centerx,centery;
	EntityImage imager;
	
	public EntityBox(Control parent,EntityImage Ei, int x, int y) {
		super(parent, x, y,1,1);
		setVisible(false);
		centerx = x;
		centery = y;
		imager = Ei;
	}
	
	public void changeSize(){
		imager.changeSize();
		setVisible(true);
		setWidth(imager.getWidth()+100);
		setHeight(height = imager.getHeight()+100);
		if(!(imager.getWidth()>10||imager.getHeight()>10)){
			setVisible(false);
			setWidth(1);
			setHeight(1);
		}
		setLocX(centerx - width/2);
		setLocY(centery - height/2);
		
	}

}
