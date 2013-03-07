package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PText;

public class EntityText extends PText{

	private int centerx,centery;
	Entity display;
	
	public EntityText(Control parent, String text, int x, int y) {
		super(parent, text, x, y);
		centerx = x; 
		centery = y;
		display = null;
		setForegroundColor(Color.red);
	}
	
	public void setEntity(Entity e){
		display = e;
		if(display!=null)
			setText("[X:"+display.getLocation().getCol()+" Y:"+display.getLocation().getRow()+"]");
		changeSize();
		repaint();
	}
	
	public void changeSize(){
		setLocX(centerx);
		setLocY(centery);
	}
	
	public void paint(Graphics2D g){
		super.paint(g);
		g.setColor(Color.blue);
		g.fillRect(centerx-1,centery-1,3,3);
	}

	public Entity getEntity(){
		return display;
	}
	
	public void setCenter(int x, int y){
		centerx = x;
		centery = y;
	}
	
}
