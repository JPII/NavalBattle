package com.jpii.navalbattle.game.gui;

import java.awt.Graphics2D;

import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PFrame;

public class EntityBox extends PFrame{
	
	int centerx,centery;
	EntityImage entityImage;
	EntityText location;
	Entity display;
	
	public EntityBox(Control parent, int x, int y) {
		super(parent, x, y,1,1);
		centerx = x;
		centery = y;
		entityImage = new EntityImage(this,width/2,height/2);
		location = new EntityText(this,"",width/2,height-50);
		addControl(entityImage);
		addControl(location);
	}
	
	public void setEntity(Entity e){
		display = e;
		entityImage.setEntity(e);
		changeSize();
		location.setEntity(e);
	}
	
	public void changeSize(){
		setWidth(entityImage.getWidth()+100);
		setHeight(entityImage.getHeight()+50);
		setLocX(centerx - width/2);
		setLocY(centery - height/2);
		location.setCenter(width/2,height-25);
	}
	
	public void paint(Graphics2D g){
		super.paint(g);
	}
	
	public Entity getEntity(){
		return display;
	}
	
	public void setVisible(boolean value){
		if(value != isVisible()){
			super.setVisible(value);
			getParent().setVisible(value);
			for(int k = 0;k<getTotalControls();k++){
				getControl(k).setVisible(value);
			}
		}
	}
}
