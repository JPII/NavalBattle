package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PImage;
import com.jpii.navalbattle.util.FileUtils;

public class EntityImage extends PImage{
	
	Entity display;
	GradientPaint gp;
	int imageNumber,centerx,centery;
	
	public EntityImage(Control parent,int x, int y,GradientPaint gp) {
		super(parent,x,y);
		setVisible(false);
		this.gp = gp;
		display = null;
		imageNumber = -1;
		centerx = x;
		centery = y;
	}
	
	public void setEntity(Entity e){
		imageNumber = -1;
		display = e;
		if(display!=null){
			imageNumber = PImage.registerImage(FileUtils.getImage(display.imgLocation));
		}
		setImage(imageNumber);
		changeSize();
	}
	
	public void changeSize(){
		setVisible(true);
		if(retrieveImage(imageNumber)==null){
			setWidth(1);
			setHeight(1);
			return;
		}
		setWidth(retrieveImage(imageNumber).getWidth());
		setHeight(retrieveImage(imageNumber).getHeight());
		setLocX(centerx - width/2);
		setLocY(centery - height/2);
		if(!(getWidth()>10||getHeight()>10)){
			setVisible(false);
		}
	}
	
	public void paint(Graphics2D g){
			if(getParent().isVisible())
				g.setPaint(gp);
			if(!getParent().isVisible())
				g.setPaint(new GradientPaint(0,0,Color.black,0,height,Color.black));
			g.fillRect(0,0,getWidth(),getHeight());
			g.drawImage(retrieveImage(getImageID()),0,0,null);
	}
	
	public Entity getEntity(){
		return display;
	}

}
