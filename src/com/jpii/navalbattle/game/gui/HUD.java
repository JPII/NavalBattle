package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import maximusvladimir.dagen.Rand;

import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.gui.WindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PFrame;
import com.jpii.navalbattle.pavo.gui.controls.PImage;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import com.jpii.navalbattle.util.FileUtils;

public class HUD extends PWindow{
	
	Entity display;
	Rand ran;
	GradientPaint gp;
	PFrame entityBox;
	PImage entityImage;
	int imageNumber;
	
	public HUD(WindowManager parent,int x, int y, int width, int height){
		super(parent, x, y, width, height);
		display = null;
		ran = Game.Settings.rand;
		gp = new GradientPaint(0,0,new Color(96,116,190),0,height,new Color(0,0,54));
		setTitleVisiblity(false);
		setVisible(false);
		entityBox = new PFrame(this,width-325,(height-100)/2,300,100);
		entityImage = new PImage(this);
		
		addControl(entityBox);
		addControl(entityImage);
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		if(isVisible())
			g.setPaint(gp);
		if(!isVisible())
			g.setPaint(new GradientPaint(0,0,Color.black,0,height,Color.black));
		g.fillRect(0,0,getWidth(),getHeight());
		
		//entityImage.s
	}
	
	public void paintAfter(Graphics2D g){
		super.paintAfter(g);
		
	}
	
	public void setEntity(Entity e){
		display = e;
		imageNumber = -1;
		if(display!=null)
			imageNumber = PImage.registerImage(FileUtils.getImage(display.imgLocation));
		entityImage.setImage(imageNumber);
		update();
	}
	
	public void update(){
		if(display != null){
			setVisible(true);
		}
		else{
			setVisible(false);
		}
		repaint();
	}
	
}
