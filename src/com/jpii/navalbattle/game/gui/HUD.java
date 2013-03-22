package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.game.TurnManager;
import com.jpii.navalbattle.game.entity.MoveableEntity;
import com.jpii.navalbattle.pavo.grid.Entity;
import com.jpii.navalbattle.pavo.gui.NewWindowManager;
import com.jpii.navalbattle.pavo.gui.controls.PImage;
import com.jpii.navalbattle.pavo.gui.controls.PWindow;
import com.jpii.navalbattle.pavo.gui.controls.PButton;
import com.jpii.navalbattle.util.FileUtils;

public class HUD extends PWindow{
	
	TurnManager tm;
	GradientPaint gp;
	int centerx,centery;
	int imgx,imgy;
	BufferedImage entityImg;
	int boxwidth,boxheight,boxx,boxy;
	String location = new String("");
	String health = new String("");
	String movement = new String("");
	Entity display;
	
	PImage missile;
	PImage bullet;
	PImage move;
	PImage diplomacy;
	
	PButton missileB;
	PButton bulletB;
	PButton moveB;
	PButton diplomacyB;
	
	 public HUD(NewWindowManager parent,TurnManager tm,int x, int y, int width, int height){
		super(parent, x, y, width, height);
		this.tm = tm;
		gp = new GradientPaint(0,0,new Color(96,116,190),0,height,new Color(0,0,54));
		setTitleVisiblity(false);
		setVisible(false);
		centerx = getWidth()-210;
		centery = getHeight()/2;
		
		// Buttons
		addControl(missileB = new PButton(this,(getWidth()/2)-60,getHeight()-45,30,30));
		addControl(bulletB = new PButton(this,(getWidth()/2)-20,getHeight()-45,30,30));
		addControl(diplomacyB = new PButton(this,(getWidth()/2)+20,getHeight()-45,30,30));
		addControl(moveB = new PButton(this,(getWidth()/2)+60,getHeight()-45,30,30));
		
		missile = new PImage(this);
		bullet = new PImage(this);
		move = new PImage(this);
		diplomacy = new PImage(this);
		
		missile.setLoc((getWidth()/2)-60,getHeight()-45);
		bullet.setLoc((getWidth()/2)-20,getHeight()-45);
		diplomacy.setLoc((getWidth()/2)+20,getHeight()-45);
		move.setLoc((getWidth()/2)+60,getHeight()-45);
		
		missile.setSize(30,30);
		bullet.setSize(30,30);
		diplomacy.setSize(30,30);
		move.setSize(30,30);
		
		missile.setImage(PImage.registerImage(FileUtils.getImage("drawable-game/Buttons/Missile.png")));
		bullet.setImage(PImage.registerImage(FileUtils.getImage("drawable-game/Buttons/Bullet.png")));
		diplomacy.setImage(PImage.registerImage(FileUtils.getImage("drawable-game/Buttons/Diplomacy.png")));
		move.setImage(PImage.registerImage(FileUtils.getImage("drawable-game/Buttons/Move.png")));
		
		missile.repaint();
		bullet.repaint();
		diplomacy.repaint();
		move.repaint();
		
		addControl(missile);
		addControl(bullet);
		addControl(diplomacy);
		addControl(move);
		
		this.repaint();
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		
		//Background
		g.setPaint(gp);
		g.fillRect(0,0,getWidth(),getHeight());
		
		//Dividers
		g.setColor(Color.green);
		g.drawLine(getWidth()/3, 0, getWidth()/3, getHeight());
		g.drawLine(2*getWidth()/3, 0, 2*getWidth()/3, getHeight());
		
		// Entity Box
		drawFrame(g, boxx, boxy, boxwidth, boxheight);
		g.drawImage(entityImg,boxx+50,boxy+50,null);
		g.setColor(Color.red);
		drawString(g,location, centerx, centery+60);
		drawString(g,health, centerx, centery-35);
		drawString(g,movement, centerx, centery+40);
		
		// Msg System
		
		// Basically gonna be a Queue via regular array of String[]
		
		
	}
	
	public void paintAfter(Graphics2D g){
		super.paintAfter(g);
	}
	
	public void setEntity(Entity e){		
		display = e;
		update();
	}
	
	public void update(){
		if(display != null){
			setVisible(true);
			entityImg = FileUtils.getImage(display.imgLocation);
			int tempwidth = entityImg.getWidth();
			int tempheight = entityImg.getHeight();
			boxx = centerx - (tempwidth/2) - 50;
			boxy = centery - (tempheight/2) - 50;
			boxwidth = tempwidth+100;
			boxheight = tempheight+100;
			location = ("[X:"+display.getLocation().getCol()+" Y:"+display.getLocation().getRow()+"]");
			if(display.getHandle()==1){
				MoveableEntity display = (MoveableEntity)this.display;
				if(tm.getTurn().canmoveEntity(display)){
					move.setVisible(true);
					moveB.setVisible(true);
				}
				else{
					move.setVisible(false);
					moveB.setVisible(false);
				}
				health = ("Health: "+display.getHealth()+"%");
				movement = ("Movement Left: "+(display.getMaxMovement()-display.getMoved())+" out of "+display.getMaxMovement());
			}
			else{
				move.setVisible(false);
				moveB.setVisible(false);
			}
		}
		else{
			setVisible(false);
		}
		repaint();
	}
	
	private void drawFrame(Graphics2D g,int x, int y, int width, int height) { // needs work!
		g.setColor(new Color(126,105,65));
		g.fillRect(x,y,width,height);
		g.setColor(new Color(65,54,33));
		for (int x22 = 8+x; x22 < (x+width)-8; x22 += 8) {
			g.drawLine(x22+x,0+y,x+x22+4,y+8);
		}
		for (int x22 = 8+x; x22 < (x+width)-8; x22 += 8) {
			g.drawLine(x22+4+x,y+height-9,x22+x,y+height);
		}
		for (int y22 = 8+y; y22 < (y+height)-8; y22 += 8) {
			g.drawLine(x,y22+y,8+x,y+y22+4);
		}
		for (int y22 = 8+y; y22 < (y+height)-8; y22 += 8) {
			g.drawLine(x+height-9,y+y22+4,x+width,y+y22);
		}
		g.setColor(new Color(169,140,86));
		g.fillRect(8+x,y+8,width-16,height-16);
		g.setColor(Color.black);
		g.drawRect(x,y,width-1,height-1);
		g.drawRect(8+x,8+y,width-16,height-16);
	}
	
	private int getWidth(Graphics2D g,String s){
		return 4+g.getFontMetrics().stringWidth(s);
	}
	
	private void drawString(Graphics2D g,String s,int x, int y){
		if(s!=null){
			g.drawString(s, x-(getWidth(g,s))/2, y);
		}
	}
	
}
