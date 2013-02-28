package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PImage;

public class EntityImage extends PImage{
	
	GradientPaint gp;
	
	public EntityImage(Control parent,GradientPaint gp) {
		super(parent);
		this.gp = gp;
	}
	
	public void paint(Graphics2D g){
			if(getParent().isVisible())
				g.setPaint(gp);
			if(!getParent().isVisible())
				g.setPaint(new GradientPaint(0,0,Color.black,0,height,Color.black));
			g.fillRect(0,0,getWidth(),getHeight());
			g.drawImage(retrieveImage(getImageID()),0,0,null);
	}

}
