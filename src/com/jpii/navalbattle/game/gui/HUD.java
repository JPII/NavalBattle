package com.jpii.navalbattle.game.gui;

import java.awt.Color;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.Entity;

public class HUD{
	
	Entity display;
	Rand ran;
	
	public HUD(){
		display = null;
		ran = Game.Settings.rand;
	}
	
	public void setEntity(Entity e){
		display = e;
		update();
	}
	
	public void update(){
		if(display != null){
			drawMenu();
		}
	}
	
	private void drawMenu(){
		
	}
	
	private Color getBlue(){
		int r = ran.nextInt(85,115);
		int g = ran.nextInt(0,255/2);
		int b = ran.nextInt((255/2)+g/2,255);
		return new Color(r,g,b);
	}
	
	private Color getBlack(){
		int r = 0;
		int b = ran.nextInt(50,(255/3));
		return new Color(r,r,b);
	}
	
	private Color getBrown(){
		int r = ran.nextInt(85,115);
		int g = ran.nextInt(0,255/2);
		int b = ran.nextInt(0,255/3);
		return new Color(r,g,b);
	}
	
}
