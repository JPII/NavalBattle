package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.gui.WindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;

public class HUD extends PWindow{
	
	Rand ran;
	GradientPaint gp;
	EntityBox entityBox;
	int extrawidth;
	int extraheight;
	
	public HUD(WindowManager parent,int x, int y, int width, int height){
		super(parent, x, y, width, height);
		extrawidth = width;
		extraheight = height;
		ran = Game.Settings.rand;
		gp = new GradientPaint(0,0,new Color(96,116,190),0,height,new Color(0,0,54));
		setTitleVisiblity(false);
		setVisible(false);
		setWidth(1);
		setHeight(1);
		entityBox = new EntityBox(this,width-200,height/2);
		//x and y passed here are the center of the Frame/Image!!!

		addControl(entityBox);
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		if(isVisible())
			g.setPaint(gp);
		if(!isVisible())
			g.setPaint(new GradientPaint(0,0,Color.black,0,height,Color.black));
		g.fillRect(0,0,getWidth(),getHeight());
	}
	
	public void paintAfter(Graphics2D g){
		super.paintAfter(g);
	}
	
	public void setEntity(Entity e){		
		update(e);
		entityBox.setEntity(e);
	}
	
	public void update(Entity e){
		if(e != null){
			setVisible(true);
			setWidth(extrawidth);
			setHeight(extraheight);
		}
		else{
			setVisible(false);
			setWidth(1);
			setHeight(1);
		}
		repaint();
	}
	
}
