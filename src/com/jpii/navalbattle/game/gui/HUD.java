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
	
	Entity display;
	Rand ran;
	GradientPaint gp;
	GradientPaint gp2;
	
	public HUD(WindowManager parent,int x, int y, int width, int height){
		super(parent, x, y, width, height);
		setSize(Game.Settings.currentWidth, 200);
		setLoc(0,Game.Settings.currentHeight-this.height);
		display = null;
		ran = Game.Settings.rand;
		gp = new GradientPaint(0,0,getBlue(),0,height,getBlack()); // 0,0,new Color(96,116,190),0,height,new Color(0,0,54));
		gp2 = new GradientPaint(0,0,getBlack(),0,height,getBlack());
		setTitleVisiblity(false);
		setVisible(false);
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		if(isVisible())
			g.setPaint(gp);
		if(!isVisible())
			g.setPaint(gp2);
		g.fillRect(0,0,getWidth(),getHeight());
	}
	
	public void setEntity(Entity e){
		display = e;
		update();
	}
	
	public void update(){
		if(display != null){
			gp = new GradientPaint(0,0,getBlue(),0,height,getBlack());
			setVisible(true);
		}
		else{
			setVisible(false);
			gp2 = new GradientPaint(0,0,getBlack(),0,height,getBlack());
		}
		repaint();
	}
	
	private void drawMenu(){
		
	}
	
	private Color getBlue(){
		int r = ran.nextInt(85,115);
		int g = ran.nextInt(0,255/2);
		int b = ran.nextInt((255/2)+g/2,255);
		System.out.println("Blue: "+new Color(r,g,b).toString());
		return new Color(r,g,b);
	}
	
	private Color getBlack(){
		int r = 0;
		int b = ran.nextInt(50,(255/3));
		System.out.println("Black: "+new Color(r,r,b).toString());
		return new Color(r,r,b);
	}
	
	private Color getBrown(){
		int r = ran.nextInt(85,115);
		int g = ran.nextInt(0,255/2);
		int b = ran.nextInt(0,255/3);
		return new Color(r,g,b);
	}
	
}
