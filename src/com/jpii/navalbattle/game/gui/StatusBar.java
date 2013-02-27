package com.jpii.navalbattle.game.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.jpii.navalbattle.NavalBattle;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.pavo.Game;
import com.jpii.navalbattle.pavo.PavoHelper;
import com.jpii.navalbattle.pavo.gui.GameWindow;
import com.jpii.navalbattle.util.FileUtils;
import com.roketgamer.gravatar.Gravatar;
import com.roketgamer.gravatar.GravatarDefaultImage;
import com.roketgamer.gravatar.GravatarRating;

import java.text.DecimalFormat;

import javax.swing.ImageIcon;

public class StatusBar extends GameWindow {
	BufferedImage icn_mouse;
	int mx, my;
	Game gameThing;
	
	/**
	 * Initialises a new instance of <code>StatusBar</code>
	 */
	public StatusBar(Game game) {
		super();
		gameThing = game;
		icn_mouse = FileUtils.getImage("icons/game/mouse.png");
		setBackgroundColor(Color.gray);
		setTitleVisiblity(false);
		setSize(Game.Settings.currentWidth,25);
		setLoc(0,0);
		render();
	}
	
	public void render() {
		super.render();
		Graphics2D g = PavoHelper.createGraphics(getBuffer());
		g.drawImage(icn_mouse,1,3,null);
		g.setColor(Color.black);
		g.fillRect(19,2,60,20);
		g.setColor(Color.darkGray);
		g.draw3DRect(19,2,60,20,true);
		g.draw3DRect(20,3,58,18,true);
		g.setColor(Color.white);
		String mstr = "("+mx+","+my+")";
		int wd = (60-g.getFontMetrics().stringWidth(mstr))/2;
		g.drawString(mstr, 20+wd, 17);
		
		g.setColor(Color.black);
		g.fillRect(width-111, 2, 100, 20);
		g.setColor(Color.darkGray);
		g.draw3DRect(width-111,2,100,20,true);
		g.draw3DRect(width-110,3,98,18,true);
		g.setColor(Color.white);
		String score = "Score: "+NavalBattle.getGameState().getScore();
		int sd = 2;
		g.drawString(score, width-110+sd, 17);
		
		g.setColor(Color.black);
		g.fillRect(width-221, 2, 100, 20);
		g.setColor(Color.darkGray);
		g.draw3DRect(width-221,2,100,20,true);
		g.draw3DRect(width-220,3,98,18,true);
		g.setColor(Color.white);
		//String secondTime = new DecimalFormat("00").format(gameThing.getWorld().getTimeManager().getCurrentMinutes());
		g.drawString("Time: " + gameThing.getWorld().getTimeManager().getCurrentHour() + ":00"
				, width-220+sd, 17);
		
		g.drawImage(new ImageIcon(NavalBattle.GRAVATAR).getImage(),width-348+sd,5,null);
		
		
		g.setColor(Color.black);
		g.fillRect(width-326, 2, 100, 20);
		g.setColor(Color.darkGray);
		g.draw3DRect(width-326,2,100,20,true);
		g.draw3DRect(width-325,3,98,18,true);
		g.setColor(Color.white);
		if(NavalBattle.getGameState().isOffline())
			g.drawString("Offline Mode", width-325+sd, 17);
		else
			g.drawString("GAMESTATE", width-325+sd, 17);
		g.dispose();
	}

	public void setMouseTileLocation(int x, int y) {
		boolean flag = false;
		if (mx != x || my != y)
			flag = true;
		mx = x;
		my = y;
		if (flag)
			render(); // Don't reRender if the mouse hasn't changed tile locations.
	}
	
	public void setLoc(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
