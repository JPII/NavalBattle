package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.gui.controls.Control;
import com.jpii.navalbattle.pavo.gui.controls.PImage;
import com.jpii.navalbattle.util.FileUtils;

public class EntityImage extends PImage{
	
	Entity display;
	int imageNumber,centerx,centery;
	
	public EntityImage(Control parent,int x, int y) {
		super(parent,x,y);
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
		repaint();
	}
	
	public void changeSize(){
		BufferedImage temp = retrieveImage(imageNumber);
		if(temp==null||!getParent().isVisible()){
			setVisible(false);
			return;
		}
		setWidth(temp.getWidth());
		setHeight(temp.getHeight());
		setLocX(centerx - width/2);
		setLocY(centery - height/2);
	}
	
	public void paint(Graphics2D g){
		g.setColor(new Color(169,140,86));
		g.fillRect(50,50,getWidth(),getHeight());
		g.drawImage(retrieveImage(getImageID()),50,50,null);
	}
	
	public Entity getEntity(){
		return display;
	}	
}
