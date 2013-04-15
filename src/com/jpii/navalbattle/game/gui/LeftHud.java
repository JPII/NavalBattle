package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class LeftHud {
	GradientPaint ht;
	String[] events;
	
	public LeftHud(int height){
		ht = new GradientPaint(0,0,new Color(169,140,86),0,height,new Color(69,40,6));
		events = new String[5];
	}
	
	public void draw(Graphics2D g){
		drawPlayerBox(g);
	}
	
	private void drawPlayerBox(Graphics2D g){
		
	}
	
	public void addEvent(String s){
		if(events[events.length-1]==null){
			for(int index=0;index<events.length-1;index++){
				if(events[index]!=null){
					events[index]=s;
					return;
				}
			}
		}
		else{
			for(int index=0;index<events.length-2;index++){
				events[index]=events[index+1];
			}
			events[events.length-1]=s;
		}	
	}
}
