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
		entityImage = new EntityImage(this,x,y);
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
		setHeight(entityImage.getHeight()+100);
		if(!(entityImage.getWidth()>10||entityImage.getHeight()>10)){
			setVisible(false);
		}
		setLocX(centerx - width/2);
		setLocY(centery - height/2);
		location.setCenter(width/2,height-25);
	}
	
	public void paint(Graphics2D g){
		super.paint(g);
	/*	if(entityImage!=null)
			entityImage.paint(g);
		if(location!=null)
			location.paint(g);*/
	}
	
	public Entity getEntity(){
		return display;
	}
	
	public boolean isVisible(){
		return getParent().isVisible();
	}
	
	public void setVisible(boolean value){
		setChildVisible(value);
		setParentVisible(value);
	}
	
	public void setParentVisible(boolean value){
		getParent().setVisible(value);
	}
	
	private void setChildVisible(boolean value){
		super.setVisible(value);
		for(int k = 0;k<getTotalControls();k++){
			getControl(k).setVisible(value);
		}
	}
}
