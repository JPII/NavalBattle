package com.jpii.navalbattle.game.gui;

import com.jpii.navalbattle.pavo.grid.Entity;

public class HUD{
	
	Entity display;
	
	public HUD(){
		display = null;
	}
	
	public void setEntity(Entity e){
		display = e;
		update();
	}
	
	public void update(){
		if(display != null){
			
		}
	}
}
